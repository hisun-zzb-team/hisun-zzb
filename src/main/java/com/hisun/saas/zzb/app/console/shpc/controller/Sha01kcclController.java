package com.hisun.saas.zzb.app.console.shpc.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01gbrmspb;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01grzdsx;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01kccl;
import com.hisun.saas.zzb.app.console.shpc.service.*;
import com.hisun.util.*;
import org.apache.commons.io.FileUtils;
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
@RequestMapping("/zzb/app/Sha01/kccl")
public class Sha01kcclController extends BaseController {
    @Autowired
    private Sha01Service sha01Service;

    @Autowired
    private Sha01kcclService sha01kcclService;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    @RequestMapping(value="/ajax/uploadFile")
    public @ResponseBody
    Map<String,Object> upload(String sha01Id, @RequestParam(value="kcclFile",required=false) MultipartFile file, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String,Object> map = new HashMap<String,Object>();
        if(file==null || file.isEmpty()){
            map.put("code", -1);
            map.put("message", "文件没有内容");
            return map;
        }

        try{
            String fileName = file.getOriginalFilename();
            if(fileName.endsWith(".doc") ||fileName.endsWith(".DOC") ||fileName.endsWith(".docx") ||fileName.endsWith(".DOCX") ){
                String fileDir = uploadAbsolutePath + Sha01kcclService.ATTS_PATH;
                File _fileDir = new File(fileDir);
                if (_fileDir.exists() == false) {
                    _fileDir.mkdirs();
                }
                String savePath = Sha01kcclService.ATTS_PATH  + UUIDUtil.getUUID()+"_"+fileName;
                String saveRealPath =  uploadAbsolutePath+savePath;
                try {
                    FileOutputStream fos = new FileOutputStream(new File(saveRealPath));
                    fos.write(file.getBytes());
                    fos.flush();
                    fos.close();

                    //处理PDF
                    String pdfPath = Sha01kcclService.ATTS_PATH+ UUIDUtil.getUUID()+".pdf";
                    String pdfRealPath = uploadAbsolutePath+pdfPath;
                    WordConvertUtil.newInstance().convert(saveRealPath,pdfRealPath,WordConvertUtil.PDF);
                    Sha01 sha01 = this.sha01Service.getByPK(sha01Id);
                    if(sha01.getKccls()!=null &&sha01.getKccls().size()>0){//修改
                        Sha01kccl sha01kccl = sha01.getKccls().get(0);
                        sha01kccl.setPath(savePath);
                        sha01kccl.setSha01(sha01);
                        sha01kccl.setFile2imgPath(pdfPath);
                        this.sha01kcclService.update(sha01kccl);
                    }else{//创建
                        Sha01kccl sha01kccl = new Sha01kccl();
                        sha01kccl.setPath(savePath);
                        sha01kccl.setSha01(sha01);
                        sha01kccl.setFile2imgPath(pdfPath);
                        this.sha01kcclService.save(sha01kccl);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    throw new GenericException(e);
                }
            }else{
                map.put("code", -1);
                map.put("message", "请上传word");
                return map;
            }
        }catch(Exception e){
            map.put("code", -1);
            map.put("message", "读取文件错误，请检查word是否能正确打开");
            return map;
        }
        try{

        }catch(GenericException e){
            logger.error(e, e);
            map.put("code", -1);
            map.put("message", e.getMessage());
            return map;
        }catch(Exception e){
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
     * @param uploadMatchingMode  //批量上传匹配方式 0按序号匹配 1按姓名匹配
     * @param file
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/ajax/batch/upload")
    public @ResponseBody
    Map<String,Object> batchUpload(String shpcId, String uploadMatchingMode,String split,@RequestParam(value="kcclFile",required=false) MultipartFile file,
                                   HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String,Object> map = new HashMap<String,Object>();
        if(file==null || file.isEmpty()){
            map.put("code", -1);
            map.put("message", "文件没有内容");
            return map;

        }
        try{
            String fileName = file.getOriginalFilename();
            if(fileName.toLowerCase().endsWith(".zip")){
                String kcclAttsPath = uploadAbsolutePath+Sha01kcclService.ATTS_PATH;
                File _fileDir = new File(kcclAttsPath);
                if (_fileDir.exists() == false) {
                    _fileDir.mkdirs();
                }
                //原zip存储路径
                String zipFilePath = kcclAttsPath+ UUIDUtil.getUUID()+".zip";
                File zipFile = new File(zipFilePath);
                FileOutputStream fos = new FileOutputStream(zipFile);
                fos.write(file.getBytes());
                fos.flush();
                fos.close();

                String tmpFilePath =  kcclAttsPath+UUIDUtil.getUUID()+File.separator;
                //解压到临时目录
                CompressUtil.unzip(zipFilePath,tmpFilePath);
                //循环目录下的文件,如果在当前批次下找到对应名字的干部,则附加到当前干部下
                File tempFiles = new File(tmpFilePath);
                if(tempFiles!=null){
                    for(File f : tempFiles.listFiles()){
                        if(f.isDirectory()) continue;//如果是目录则跳过
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
                        List<Sha01> sha01s = this.sha01Service.list(query,null);
                        if(sha01s!=null && sha01s.size()>0){
                            String ext = f.getName().substring(f.getName().lastIndexOf("."));
                            String savePath = Sha01kcclService.ATTS_PATH+UUIDUtil.getUUID()+ext;
                            String saveRealPath = uploadAbsolutePath+savePath;
                            File desFile = new File(saveRealPath);
                            FileUtils.copyFile(f,desFile);
                            //处理
                            String pdfPath = Sha01kcclService.ATTS_PATH + UUIDUtil.getUUID()+".pdf";
                            String pdfRealPath = uploadAbsolutePath+pdfPath;
                            WordConvertUtil.newInstance().convert(saveRealPath,pdfRealPath,WordConvertUtil.PDF);
                            Sha01 sha01 = sha01s.get(0);
                            if(sha01.getKccls()!=null &&sha01.getKccls().size()>0){//修改
                                Sha01kccl sha01kccl = sha01.getKccls().get(0);
                                sha01kccl.setPath(savePath);
                                sha01kccl.setSha01(sha01);
                                sha01kccl.setFile2imgPath(pdfPath);
                                this.sha01kcclService.update(sha01kccl);
                            }else{//创建
                                Sha01kccl sha01kccl = new Sha01kccl();
                                sha01kccl.setPath(savePath);
                                sha01kccl.setSha01(sha01);
                                sha01kccl.setFile2imgPath(pdfPath);
                                this.sha01kcclService.save(sha01kccl);
                            }
                        }
                    }
                }
                //删除临时文件目录
                FileUtils.deleteQuietly(tempFiles);
                //删除zip文件
                FileUtils.forceDelete(zipFile);
            }else{
                map.put("code", -1);
                map.put("message", "请上传ZIP!");
                return map;
            }
        }catch(Exception e){
            e.printStackTrace();

            map.put("code", -1);
            map.put("message", "读取文件错误!");
            return map;
        }
        try{

        }catch(GenericException e){
            logger.error(e, e);
            map.put("code", -1);
            map.put("message", e.getMessage());
            return map;
        }catch(Exception e){
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
                        String savePath = Sha01kcclService.ATTS_PATH + UUIDUtil.getUUID() +"."+ext;
                        String saveRealPath = uploadAbsolutePath + savePath;
                        File desFile = new File(saveRealPath);
                        FileUtils.copyFile(f, desFile);
                        //处理
                        String pdfPath = Sha01kcclService.ATTS_PATH+ UUIDUtil.getUUID()+".pdf";
                        String pdfRealPath = uploadAbsolutePath+pdfPath;
                        WordConvertUtil.newInstance().convert(saveRealPath,pdfRealPath,WordConvertUtil.PDF);
                        Sha01 sha01 = sha01s.get(0);
                        if(sha01.getKccls()!=null &&sha01.getKccls().size()>0){//修改
                            Sha01kccl sha01kccl = sha01.getKccls().get(0);
                            sha01kccl.setPath(savePath);
                            sha01kccl.setSha01(sha01);
                            sha01kccl.setFile2imgPath(pdfPath);
                            this.sha01kcclService.update(sha01kccl);
                        }else{//创建
                            Sha01kccl sha01kccl = new Sha01kccl();
                            sha01kccl.setPath(savePath);
                            sha01kccl.setSha01(sha01);
                            sha01kccl.setFile2imgPath(pdfPath);
                            this.sha01kcclService.save(sha01kccl);
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
            uploadMatchingMode, String split, @RequestParam(value = "kcclFile", required = false) MultipartFile file,
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
        String attsPath = uploadAbsolutePath + Sha01kcclService.ATTS_PATH;
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
                map.put("urlValue","kccl");
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





    @RequestMapping(value="/ajax/down")
    public void templateDown(String sha01Id,HttpServletRequest req, HttpServletResponse resp) throws Exception{
        Sha01 sha01 = this.sha01Service.getByPK(sha01Id);
        if(sha01.getKccls()!=null &&sha01.getKccls().size()>0){//修改
            Sha01kccl sha01kccl = sha01.getKccls().get(0);
            resp.setContentType("multipart/form-data");
            //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
            resp.setHeader("Content-Disposition", "attachment;fileName="+encode(sha01kccl.getPath().substring(sha01kccl.getPath().lastIndexOf(File.separator)+1)));
            OutputStream output=resp.getOutputStream();
            byte[] b= FileUtils.readFileToByteArray(new File(uploadAbsolutePath+ sha01kccl.getPath()));
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
