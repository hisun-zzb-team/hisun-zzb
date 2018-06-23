/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.shpc.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.shpc.entity.*;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01Service;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01dascqkService;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01grzdsxService;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01kcclService;
import com.hisun.saas.sys.util.BeanTrans;
import com.hisun.util.*;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by zhouying on 2017/9/8.
 */
@Controller
@RequestMapping("/zzb/app/Sha01/grzdsx")
public class Sha01grzdsxController extends BaseController {
    @Autowired
    private Sha01Service sha01Service;

    @Autowired
    private Sha01grzdsxService sha01grzdsxService;

    @Value("${sys.upload.absolute.path}")
    private String uploadAbsolutePath;

    @RequestMapping(value = "/ajax/uploadFile")
    public
    @ResponseBody
    Map<String, Object> upload(String sha01Id, @RequestParam(value = "grzdsxFile", required = false) MultipartFile file,
                                    HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String, Object> map = new HashMap<String, Object>();
        if (file == null || file.isEmpty()) {
            map.put("code", -1);
            map.put("message", "文件没有内容");
            return map;
        }

        try {
            String fileName = file.getOriginalFilename();
            if (fileName.endsWith(".doc") || fileName.endsWith(".DOC") || fileName.endsWith(".docx") || fileName.endsWith(".DOCX")) {

                String fileDir = uploadAbsolutePath + Sha01grzdsxService.ATTS_PATH;
                File _fileDir = new File(fileDir);
                if (_fileDir.exists() == false) {
                    _fileDir.mkdirs();
                }
                String savePath = Sha01grzdsxService.ATTS_PATH + UUIDUtil.getUUID() + "_" + fileName;
                String saveRealPath = uploadAbsolutePath+savePath;
                try {
                    FileOutputStream fos = new FileOutputStream(new File(saveRealPath));
                    fos.write(file.getBytes());
                    fos.flush();
                    fos.close();

                    //处理
                    String pdfPath = Sha01grzdsxService.ATTS_PATH + UUIDUtil.getUUID() + ".pdf";
                    String pdfRealPath = uploadAbsolutePath + pdfPath;
                   // String imgPath = fileDir + UUIDUtil.getUUID() + ".jpg";
                    //先将其转PDF
                    WordConvertUtil.newInstance().convert(saveRealPath, pdfRealPath, WordConvertUtil.PDF);
                    //再将其转成图片
                   // Pdf2ImgUtil.toImg(pdfPath, imgPath);

                    Sha01 sha01 = this.sha01Service.getByPK(sha01Id);
                    if (sha01.getGrzdsxes() != null && sha01.getGrzdsxes().size() > 0) {//修改
                        Sha01grzdsx sha01grzdsx = sha01.getGrzdsxes().get(0);
                        sha01grzdsx.setPath(savePath);
                        sha01grzdsx.setSha01(sha01);
                        sha01grzdsx.setFile2imgPath(pdfPath);
                        this.sha01grzdsxService.update(sha01grzdsx);
                    } else {
                        Sha01grzdsx sha01grzdsx = new Sha01grzdsx();
                        sha01grzdsx.setPath(savePath);
                        sha01grzdsx.setSha01(sha01);
                        sha01grzdsx.setFile2imgPath(pdfPath);
                        this.sha01grzdsxService.save(sha01grzdsx);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    throw new GenericException(e);
                }
            } else {
                map.put("code", -1);
                map.put("message", "请上传word");
                return map;
            }
        } catch (Exception e) {
            map.put("code", -1);
            map.put("message", "读取文件错误，请检查word是否能正确打开");
            return map;
        }
        try {

        } catch (GenericException e) {
            logger.error(e, e);
            map.put("code", -1);
            map.put("message", e.getMessage());
            return map;
        } catch (Exception e) {
            logger.error(e, e);
            map.put("code", -1);
            map.put("message", "系统错误，请联系管理员");
            return map;
        }
        map.put("code", 1);
        return map;
    }

    /**
     *
     * @param shpcId
     * @param uploadMatchingMode //批量上传匹配方式 0按序号匹配 1按姓名匹配
     * @param file
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/ajax/batch/upload")
    public
    @ResponseBody
    Map<String, Object> batchUpload(String shpcId,String uploadMatchingMode,String split,@RequestParam(value = "grzdsxFile", required = false) MultipartFile file,
                                    HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String, Object> map = new HashMap<String, Object>();
        if (file == null || file.isEmpty()) {
            map.put("code", -1);
            map.put("message", "文件没有内容");
            return map;

        }
        try {
            String fileName = file.getOriginalFilename();
            String grzdsxAttsPath = uploadAbsolutePath + Sha01grzdsxService.ATTS_PATH;
            if (fileName.toLowerCase().endsWith(".zip")) {
                File _fileDir = new File(grzdsxAttsPath);
                if (_fileDir.exists() == false) {
                    _fileDir.mkdirs();
                }
                //原zip存储路径
                String zipFilePath = grzdsxAttsPath + UUIDUtil.getUUID()+".zip";
                File zipFile = new File(zipFilePath);
                FileOutputStream fos = new FileOutputStream(zipFile);
                fos.write(file.getBytes());
                fos.flush();
                fos.close();

                String tmpFilePath = grzdsxAttsPath + UUIDUtil.getUUID() + File.separator;
                //解压到临时目录
                CompressUtil.unzip(zipFilePath, tmpFilePath);
                //循环目录下的文件,如果在当前批次下找到对应名字的干部,则附加到当前干部下
                File tempFiles = new File(tmpFilePath);
                if (tempFiles != null) {
                    for (File f : tempFiles.listFiles()) {
                        if (f.isDirectory()) continue;//如果是目录则跳过
                        String fname = f.getName();
                        CommonConditionQuery query = new CommonConditionQuery();
                        //按姓名匹配
                        if(uploadMatchingMode!=null && uploadMatchingMode.equals("1")) {
                            query.add(CommonRestrictions.and(" instr( :fname , Sha01.xm) >0", "fname",
                                    fname.replace(".","")));
                        }else{
                            //按序号匹配
                            int px =-1;
                            if(fname.indexOf(".")>0){
                                try {
                                    px = Integer.parseInt(fname.substring(0, fname.indexOf(".")));
                                }catch(Exception e){}
                            }
                            if(px==-1&& fname.indexOf("、")>0){
                                try {
                                    px = Integer.parseInt(fname.substring(0, fname.indexOf("、")));
                                }catch(Exception e){}
                            }
                            query.add(CommonRestrictions.and(" px = :px", "px", px));
                        }
                        query.add(CommonRestrictions.and(" Sha01.shpc.id = :shpc ", "shpc", shpcId));
                        query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
                        List<Sha01> sha01s = this.sha01Service.list(query, null);
                        if (sha01s != null && sha01s.size() > 0) {
                            String ext = f.getName().substring(f.getName().lastIndexOf("."));
                            String savePath = Sha01grzdsxService.ATTS_PATH+UUIDUtil.getUUID()+ext;
                            String saveRealPath = uploadAbsolutePath+savePath;
                            File desFile = new File(saveRealPath);
                            FileUtils.copyFile(f, desFile);
                            //处理
                            String pdfPath = Sha01grzdsxService.ATTS_PATH+ UUIDUtil.getUUID()+".pdf";
                            String pdfRealPath = uploadAbsolutePath+pdfPath;
                            WordConvertUtil.newInstance().convert(saveRealPath, pdfRealPath, WordConvertUtil.PDF);
                            Sha01 sha01 = sha01s.get(0);
                            if (sha01.getGrzdsxes() != null && sha01.getGrzdsxes().size() > 0) {//修改
                                Sha01grzdsx sha01grzdsx = sha01.getGrzdsxes().get(0);
                                sha01grzdsx.setPath(savePath);
                                sha01grzdsx.setSha01(sha01);
                                sha01grzdsx.setFile2imgPath(pdfPath);
                                this.sha01grzdsxService.update(sha01grzdsx);
                            } else {//创建
                                Sha01grzdsx sha01grzdsx = new Sha01grzdsx();
                                sha01grzdsx.setPath(savePath);
                                sha01grzdsx.setSha01(sha01);
                                sha01grzdsx.setFile2imgPath(pdfPath);
                                this.sha01grzdsxService.save(sha01grzdsx);
                            }
                        }
                    }
                }
                FileUtils.deleteQuietly(tempFiles);
                FileUtils.forceDelete(zipFile);
            } else {
                map.put("code", -1);
                map.put("message", "请上传ZIP!");
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();

            map.put("code", -1);
            map.put("message", "读取文件错误!");
            return map;
        }
        try {

        } catch (GenericException e) {
            logger.error(e, e);
            map.put("code", -1);
            map.put("message", e.getMessage());
            return map;
        } catch (Exception e) {
            logger.error(e, e);
            map.put("code", -1);
            map.put("message", "系统错误，请联系管理员");
            return map;
        }
        map.put("code", 1);
        return map;
    }




    @RequestMapping(value = "/ajax/batch/match/save")
    public
    @ResponseBody
    Map<String, Object> matchSave(String shpcId, String uploadMatchingMode,String split, String tmpFilePath,
                                  HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String, Object> map = new HashMap<String, Object>();
        if (tmpFilePath == null || tmpFilePath.isEmpty()) {
            map.put("code", -1);
            map.put("message", "文件没有内容");
            return map;

        }
        try {
            //循环目录下的文件,如果在当前批次下找到对应名字的干部,则附加到当前干部下
            File tempFiles = new File(tmpFilePath);
            if (tempFiles != null) {
                for (File f : tempFiles.listFiles()) {
                    if (f.isDirectory()) continue;//如果是目录则跳过
                    String filename = f.getName();
                    CommonConditionQuery query = new CommonConditionQuery();
                    //按姓名匹配
                    this.sha01Service.matchQueryCondition(query, uploadMatchingMode, split, filename);
                    query.add(CommonRestrictions.and(" Sha01.shpc.id = :shpc ", "shpc", shpcId));
                    query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
                    List<Sha01> sha01s = this.sha01Service.list(query, null);
                    if (sha01s != null && sha01s.size() > 0) {
                        String ext = FileUtil.getExtend(f.getName());
                        String savePath = Sha01grzdsxService.ATTS_PATH + UUIDUtil.getUUID() +"."+ext;
                        String saveRealPath = uploadAbsolutePath + savePath;
                        File desFile = new File(saveRealPath);
                        FileUtils.copyFile(f, desFile);
                        //处理
                        String pdfPath = Sha01grzdsxService.ATTS_PATH+ UUIDUtil.getUUID()+".pdf";
                        String pdfRealPath = uploadAbsolutePath+pdfPath;
                        WordConvertUtil.newInstance().convert(saveRealPath,pdfRealPath,WordConvertUtil.PDF);
                        Sha01 sha01 = sha01s.get(0);
                        if (sha01.getGrzdsxes() != null && sha01.getGrzdsxes().size() > 0) {//修改
                            Sha01grzdsx sha01grzdsx = sha01.getGrzdsxes().get(0);
                            sha01grzdsx.setPath(savePath);
                            sha01grzdsx.setSha01(sha01);
                            sha01grzdsx.setFile2imgPath(pdfPath);
                            this.sha01grzdsxService.update(sha01grzdsx);
                        } else {//创建
                            Sha01grzdsx sha01grzdsx = new Sha01grzdsx();
                            sha01grzdsx.setPath(savePath);
                            sha01grzdsx.setSha01(sha01);
                            sha01grzdsx.setFile2imgPath(pdfPath);
                            this.sha01grzdsxService.save(sha01grzdsx);
                        }
                    }
                }
            }
            FileUtils.deleteDirectory(tempFiles);
        } catch (Exception e) {
            e.printStackTrace();

            map.put("code", -1);
            map.put("message", "读取文件错误!");
            return map;
        }
        map.put("code", 1);
        return map;
    }


    @RequestMapping(value = "/ajax/batch/match")
    public
    @ResponseBody
    ModelAndView batchMatch(String shpcId, String
            uploadMatchingMode, String split, @RequestParam(value = "grzdsxFile", required = false) MultipartFile file,
                            HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String, String> matchMap = new LinkedHashMap<>();
        List<String> noMatchFilenames = new ArrayList<String>();
        Map<String, Object> map = new HashMap<String, Object>();
        if (file == null || file.isEmpty()) {
            map.put("code", -1);
            map.put("message", "文件没有内容");
            return null;

        }
        //模板路径
        String attsPath = uploadAbsolutePath + Sha01grzdsxService.ATTS_PATH;
        try {
            String fileName = file.getOriginalFilename();
            if (fileName.toLowerCase().endsWith(".zip")) {
                File _fileDir = new File(attsPath);
                if (_fileDir.exists() == false) {
                    _fileDir.mkdirs();
                }
                //原zip存储路径
                String zipFilePath = attsPath + UUIDUtil.getUUID() + ".zip";
                File zipFile = new File(zipFilePath);
                FileOutputStream fos = new FileOutputStream(zipFile);
                fos.write(file.getBytes());
                fos.flush();
                fos.close();

                String tmpFilePath = attsPath + UUIDUtil.getUUID() + File.separator;
                //解压到临时目录
                CompressUtil.unzip(zipFilePath, tmpFilePath);
                //循环目录下的文件,如果在当前批次下找到对应名字的干部,则附加到当前干部下
                File tempFile = new File(tmpFilePath);
                int filecount = 0;
                if (tempFile != null) {
                    List<File> files = FileUtil.listFilesOrderByName(tempFile);
                    for (File f : files) {
                        if (f.isDirectory()) continue;//如果是目录则跳过
                        String filename = f.getName();
                        CommonConditionQuery query = new CommonConditionQuery();
                        //按姓名匹配
                        this.sha01Service.matchQueryCondition(query, uploadMatchingMode, split, filename);
                        query.add(CommonRestrictions.and(" Sha01.shpc.id = :shpc ", "shpc", shpcId));
                        query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
                        List<Sha01> sha01s = this.sha01Service.list(query, null);
                        if (sha01s != null && sha01s.size() > 0) {
                            matchMap.put(sha01s.get(0).getXm(), filename);
                        }else{
                            noMatchFilenames.add(filename);
                        }
                        filecount++;
                    }
                }

                map.put("shpcId", shpcId);
                map.put("uploadMatchingMode", uploadMatchingMode);
                map.put("split", split);

                map.put("tmpFilePath", tmpFilePath);
                map.put("fileCount", filecount);
                map.put("matchCount", matchMap.size());
                map.put("nomatchCount", filecount-matchMap.size());
                map.put("matchResult", matchMap);
                map.put("noMatchFilenames",noMatchFilenames);
                map.put("urlValue","grzdsx");
                FileUtils.deleteQuietly(zipFile);
            } else {
                map.put("code", -1);
                map.put("message", "请上传ZIP!");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();

            map.put("code", -1);
            map.put("message", "读取文件错误!");
            return null;
        }
        try {

        } catch (GenericException e) {
            logger.error(e, e);
            map.put("code", -1);
            map.put("message", e.getMessage());
            return null;
        } catch (Exception e) {
            logger.error(e, e);
            map.put("code", -1);
            map.put("message", "系统错误，请联系管理员");
            return null;
        }
        map.put("code", 1);
        return new ModelAndView("/saas/zzb/app/console/Sha01/matchResult", map);
    }



    @RequestMapping(value = "/ajax/down")
    public void templateDown(String sha01Id, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Sha01 sha01 = this.sha01Service.getByPK(sha01Id);
        if (sha01.getGrzdsxes() != null && sha01.getGrzdsxes().size() > 0) {//修改
            Sha01grzdsx sha01grzdsx = sha01.getGrzdsxes().get(0);
            resp.setContentType("multipart/form-data");
            //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
            resp.setHeader("Content-Disposition", "attachment;fileName=" + encode(sha01grzdsx.getPath().substring(sha01grzdsx.getPath().lastIndexOf(File.separator) + 1)));
            OutputStream output = resp.getOutputStream();
            byte[] b = FileUtils.readFileToByteArray(new File(uploadAbsolutePath+sha01grzdsx.getPath()));
            output.write(b);
            output.flush();
            output.close();
        }

    }

    private String encode(String filename) throws UnsupportedEncodingException {
        if (WebUtil.getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            filename = URLEncoder.encode(filename, "UTF-8");
        } else {
            filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
        }
        return filename;
    }
}
