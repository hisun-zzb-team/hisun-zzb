package com.hisun.saas.zzb.app.console.gbmc.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01gbrmspb;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcA01Service;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcA01gbrmspbService;
import com.hisun.util.CompressUtil;
import com.hisun.util.UUIDUtil;
import com.hisun.util.WebUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/8.
 */
@Controller
@RequestMapping("/zzb/app/console/GbMca01/gbrmspb")
public class GbMcA01gbrmspbController extends BaseController {
    @Autowired
    private GbMcA01Service gbMcA01Service;

    @Autowired
    private GbMcA01gbrmspbService gbMcA01gbrmspbService;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;



    @RequestMapping(value="/ajax/uploadFile")
    public @ResponseBody
    Map<String,Object> upload(String gbMcA01Id, @RequestParam(value="gbrmspbFile",required=false) MultipartFile file,
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
            if(fileName.endsWith(".doc") ||fileName.endsWith(".DOC") ||fileName.endsWith(".docx") ||fileName.endsWith(".DOCX") ){
                String fileDir = uploadAbsolutePath+GbMcA01gbrmspbService.ATTS_PATH;
                File _fileDir = new File( fileDir);
                if (_fileDir.exists() == false) {
                    _fileDir.mkdirs();
                }
                //原附件存储路径
                String savePath =  GbMcA01gbrmspbService.ATTS_PATH+ UUIDUtil.getUUID()+"_"+fileName;
                String saveRealPath = uploadAbsolutePath+savePath;
                //模板路径
                String wordTemplatePath = fileDir + "gbrmspb.docx";
                try {
                    FileOutputStream fos = new FileOutputStream(new File(saveRealPath));
                    fos.write(file.getBytes());
                    fos.flush();
                    fos.close();

                    GbMcA01 gbMcA01 = this.gbMcA01Service.getByPK(gbMcA01Id);
                    GbMcA01gbrmspb gbMcA01gbrmspb = new GbMcA01gbrmspb();
                    gbMcA01gbrmspb.setFilepath(savePath);
                    gbMcA01gbrmspb.setGbMcA01(gbMcA01);
                    this.gbMcA01gbrmspbService.saveFromWord(gbMcA01gbrmspb ,saveRealPath,wordTemplatePath);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new GenericException(e);
                }
            }else{
                map.put("code", -1);
                map.put("message", "请上传word!");
                return map;
            }
        }catch(Exception e){
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



    @RequestMapping(value="/ajax/batch/upload")
    public @ResponseBody
    Map<String,Object> batchUpload(String mcb01id, @RequestParam(value="moreAttFile",required=false) MultipartFile file,
                                   HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String,Object> map = new HashMap<String,Object>();
        if(file==null || file.isEmpty()){
            map.put("code", -1);
            map.put("message", "文件没有内容");
            return map;

        }
        //模板路径
        String gbrmsbpAttsPath = uploadAbsolutePath+ GbMcA01gbrmspbService.ATTS_PATH;
        String wordTemplatePath = gbrmsbpAttsPath + "gbrmspb.docx";
        try{
            String fileName = file.getOriginalFilename();
            if(fileName.toLowerCase().endsWith(".zip")){
                File _fileDir = new File(gbrmsbpAttsPath);
                if (_fileDir.exists() == false) {
                    _fileDir.mkdirs();
                }
                //原zip存储路径

                String zipFilePath = gbrmsbpAttsPath + UUIDUtil.getUUID()+".zip";
                File zipFile = new File(zipFilePath);
                FileOutputStream fos = new FileOutputStream(zipFile);
                fos.write(file.getBytes());
                fos.flush();
                fos.close();

                String tmpFilePath =  gbrmsbpAttsPath+UUIDUtil.getUUID()+File.separator;
                //解压到临时目录
                CompressUtil.unzip(zipFilePath,tmpFilePath);
                //循环目录下的文件,如果在当前批次下找到对应名字的干部,则附加到当前干部下
                File tempFiles = new File(tmpFilePath);
                if(tempFiles!=null){
                    for(File f : tempFiles.listFiles()){
                        if(f.isDirectory()) continue;//如果是目录则跳过
                        String fname = f.getName();
                        String xm = fname.substring(0,fname.lastIndexOf("."));
                        CommonConditionQuery query = new CommonConditionQuery();
                        query.add(CommonRestrictions.and(" GbMcA01.xm like :xm ", "xm", "%"+xm+"%"));
                        query.add(CommonRestrictions.and(" GbMcA01.gbMcB01.id = :mcb01id ", "mcb01id", mcb01id));
                        query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
                        List<GbMcA01> gbMcA01s = this.gbMcA01Service.list(query,null);
                        if(gbMcA01s!=null && gbMcA01s.size()>0){
                            String ext = f.getName().substring(f.getName().lastIndexOf("."));
                            String savePath = GbMcA01gbrmspbService.ATTS_PATH+UUIDUtil.getUUID()+ext;
                            String saveRealPath = uploadAbsolutePath+savePath;
                            File desFile = new File(saveRealPath);
                            FileUtils.copyFile(f,desFile);
                            GbMcA01gbrmspb gbMcA01gbrmspb = new GbMcA01gbrmspb();
                            gbMcA01gbrmspb.setFilepath(savePath);
                            gbMcA01gbrmspb.setGbMcA01(gbMcA01s.get(0));
                            this.gbMcA01gbrmspbService.saveFromWord(gbMcA01gbrmspb,saveRealPath,wordTemplatePath);
                        }
                    }
                }
                FileUtils.deleteDirectory(tempFiles);
                FileUtils.deleteQuietly(zipFile);
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




    @RequestMapping(value="/ajax/down")
    public void templateDown(String gbMcA01Id,HttpServletRequest req, HttpServletResponse resp) throws Exception{
        GbMcA01 gbMcA01 = this.gbMcA01Service.getByPK(gbMcA01Id);
        if(gbMcA01.getGbMca01gbrmspbs()!=null &&gbMcA01.getGbMca01gbrmspbs().size()>0){//修改
            GbMcA01gbrmspb gbMcA01gbrmspb = gbMcA01.getGbMca01gbrmspbs().get(0);
            resp.setContentType("multipart/form-data");
            //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
            resp.setHeader("Content-Disposition", "attachment;fileName="+encode(gbMcA01gbrmspb.getFilepath().substring(gbMcA01gbrmspb.getFilepath().lastIndexOf(File.separator)+1)));
            OutputStream output=resp.getOutputStream();
            byte[] b= FileUtils.readFileToByteArray(new File(uploadAbsolutePath+gbMcA01gbrmspb.getFilepath()));
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
