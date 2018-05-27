/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.shpc.controller;

import com.google.common.collect.Lists;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.gbtj.vo.Sha01JsonDataVo;
import com.hisun.saas.zzb.app.console.gbtj.vo.Sha01JsonVo;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01dascqk;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.saas.zzb.app.console.shpc.entity.ShpcAtts;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01Service;
import com.hisun.saas.zzb.app.console.shpc.service.ShpcAttsService;
import com.hisun.saas.zzb.app.console.shpc.service.ShpcService;
import com.hisun.saas.zzb.app.console.shpc.vo.ShpcAttsVo;
import com.hisun.saas.zzb.app.console.shpc.vo.ShpcVo;
import com.hisun.saas.sys.util.BeanTrans;
import com.hisun.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/shpcAtts")
public class ShpcAttsController extends BaseController {
    @Resource
    private ShpcAttsService shpcAttsService;

    @Resource
    private ShpcService shpcService;
    @Autowired
    private SessionFactory sessionFactory;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;
        /**
     * 调转到附件页面
     * @return
     */
    @RequestMapping(value = "/ajax/editAtts")
    public ModelAndView editAtts(@RequestParam(value="shpcId")String shpcId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" shpc.id = :shpcId", "shpcId",shpcId));
//            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));


            List<ShpcAtts> shpcAttss = this.shpcAttsService.list(query, null);
            List<ShpcAttsVo> shpcAttsVos = new ArrayList<ShpcAttsVo>();
            if (shpcAttss != null) {// entity ==> vo
                for (ShpcAtts shpcAtts : shpcAttss) {
                    ShpcAttsVo vo = new ShpcAttsVo();
                    BeanUtils.copyProperties(vo, shpcAtts);
                    shpcAttsVos.add(vo);
                }
            }

            map.put("shpcAttsVos", shpcAttsVos);
            map.put("shpcId", shpcId);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/shpcAtts/addAndEditAtts", map);
    }

    /**
     * 保存json数据
     * @return
     */
    @RequestMapping(value = "/saveAtts")
    public @ResponseBody Map<String, Object> saveAtts(@RequestParam(value="pcId")String pcId, @ModelAttribute ShpcAttsVo vo, HttpServletRequest req) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            map.put("message", "保存出错");
        }
        return map;
    }
    @RequestMapping(value="/ajax/uploadAtt")
    public @ResponseBody
    Map<String,Object> uploadAtt(String shpcId, String token, @RequestParam(value="attachFile",required=false) MultipartFile file, HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
                String fileDir = uploadAbsolutePath + ShpcAttsService.ATTS_PATH;
                File _fileDir = new File(fileDir);
                if (_fileDir.exists() == false) {
                    _fileDir.mkdirs();
                }
                String savePath = fileDir + UUIDUtil.getUUID()+"_"+fileName;

                try {
                    FileOutputStream fos = new FileOutputStream(new File(savePath));
                    fos.write(file.getBytes());
                    fos.flush();
                    fos.close();


                    //PDF路径
                    String pdfPath = ShpcAttsService.ATTS_PATH+UUIDUtil.getUUID()+".pdf";
                    String pdfRealPath = uploadAbsolutePath+pdfPath;
                    WordConvertUtil.newInstance().convert(savePath,pdfRealPath,WordConvertUtil.PDF);
                    FileUtils.deleteQuietly(new File(savePath));

                    Shpc shpc = this.shpcService.getByPK(shpcId);
                    ShpcAtts shpcAtts = new ShpcAtts();

                    shpcAtts.setFilename(fileName.substring(0,fileName.indexOf(".")));
                    shpcAtts.setFilepath(pdfPath);
                    shpcAtts.setShpc(shpc);
                    shpcAtts.setTenant(userLoginDetails.getTenant());
                    BeanTrans.setBaseProperties(shpc, userLoginDetails, "save");
                    this.shpcAttsService.save(shpcAtts);

                    map.put("code", 1);
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
            e.printStackTrace();
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
    @RequestMapping(value = "/delete/{id}")
    public @ResponseBody Map<String, Object> delete(@PathVariable("id")String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            ShpcAtts shpcAtts = this.shpcAttsService.getByPK(id);
            if(shpcAtts != null){
                this.shpcAttsService.delete(shpcAtts);
                File attFile = new File(uploadAbsolutePath+shpcAtts.getFilepath());
                if (attFile.exists() == true) {
                    attFile.delete();
                }
            }
            map.put("success", true);
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "删除失败！");
            throw new GenericException(e);
        }
        return map;

    }
    @RequestMapping(value="/ajax/down")
    public void AttDown(String id,HttpServletRequest req, HttpServletResponse resp) throws Exception{
        ShpcAtts shpcAtts = this.shpcAttsService.getByPK(id);
        if(shpcAtts!=null){
            resp.setContentType("multipart/form-data");
            //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
            resp.setHeader("Content-Disposition", "attachment;fileName="+encode(shpcAtts.getFilename()+".pdf"));
            OutputStream output=resp.getOutputStream();
            byte[] b= FileUtils.readFileToByteArray(new File(uploadAbsolutePath+shpcAtts.getFilepath()));
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
