/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.shpc.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.apiregister.entity.ApiRegister;
import com.hisun.saas.zzb.app.console.apiregister.service.ApiRegisterService;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01gbrmspb;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01Service;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01gbrmspbService;
import com.hisun.saas.zzb.app.console.shpc.service.ShpcService;
import com.hisun.saas.zzb.app.console.shpc.vo.ShpcVo;
import com.hisun.saas.sys.util.BeanTrans;
import com.hisun.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by zhouying on 2017/9/8.
 */
@Controller
@RequestMapping("/zzb/app/console/bwh")
public class ShpcController extends BaseController {
    @Resource
    private ShpcService shpcService;
    @Resource
    private ApiRegisterService apiRegisterService;

    @Autowired
    private Sha01Service sha01Service;

    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;
    @RequestMapping("/")
    public ModelAndView list(HttpServletRequest req, String pId,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize" ,required=false) String pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Session session = SecurityUtils.getSubject().getSession();
            if(pageSize==null){
                if(session.getAttribute("bwhpageSize")!=null){
                    pageSize = session.getAttribute("bwhpageSize").toString();
                }else{
                    pageSize = "20";
                }
            }
            session.setAttribute("bwhpageSize",pageSize);
            //临时处理,将当前服务IP,端口,context写入ApiRegister
            List<ApiRegister> apiRegisters = this.apiRegisterService.list();
            if(apiRegisters!=null && apiRegisters.size()>0){
                for(ApiRegister apiRegister : apiRegisters){
                    apiRegister.setIp(req.getServerName());
                    apiRegister.setPort(""+req.getServerPort());
                    apiRegister.setContext(req.getContextPath());
                    this.apiRegisterService.update(apiRegister);
                }
            }
            CommonConditionQuery query = new CommonConditionQuery();
           // query.add(CommonRestrictions.and(" shlx = :shlx", "shlx", Shpc.SHLX_BWH));
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("px"));

            Long total = this.shpcService.count(query);
            List<Shpc> shpcs = this.shpcService.list(query, orderBy, pageNum,
                    Integer.parseInt(pageSize));
            List<ShpcVo> shpcVos = new ArrayList<ShpcVo>();
            if (shpcs != null) {// entity ==> vo
                for (Shpc shpc : shpcs) {
                    ShpcVo vo = new ShpcVo();
                    BeanUtils.copyProperties(vo, shpc);
                    vo.setPcsjValue(DateUtil.formatDateByFormat(shpc.getPcsj(),"yyyyMMdd"));
                    vo.setA01Count(shpc.getSha01s().size());
                    shpcVos.add(vo);
                }
            }
            PagerVo<ShpcVo> pager = new PagerVo<ShpcVo>(shpcVos, total.intValue(),
                    pageNum, Integer.parseInt(pageSize));
            map.put("pager", pager);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/bwh/list", map);
    }
    /**
     * 调转到新增页面
     * @return
     */
    @RequestMapping(value = "/add")
    public ModelAndView add(@RequestParam(value = "shpcPageNum", defaultValue = "1") int shpcPageNum) throws Exception{
        ShpcVo vo = new ShpcVo();
        Integer maxPx = shpcService.getMaxPx();
        if(maxPx != null){
            vo.setPx(maxPx+1);
        }else{
            vo.setPx(1);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("vo", vo);
        map.put("shpcPageNum", shpcPageNum);
        return new ModelAndView("/saas/zzb/app/console/bwh/add",map);
    }

    /**
     * 调转到修改页面
     * @return
     */
//    @RequiresPermissions("admin-assetStatus:edit")
    @RequestMapping(value = "/edit")
    public ModelAndView edit(@RequestParam(value="id")String id,@RequestParam(value = "shpcPageNum", defaultValue = "1") int shpcPageNum) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Shpc shpc = this.shpcService.getByPK(id);
            ShpcVo shpcVo = new ShpcVo();
            if (shpc == null) {
                logger.error("数据不存在");
                throw new GenericException("数据不存在");
            }
            BeanUtils.copyProperties(shpcVo, shpc);
            shpcVo.setPcsjValue(DateUtil.formatDateByFormat(shpc.getPcsj(), "yyyyMMdd"));
            map.put("shpc", shpcVo);
            map.put("shpcPageNum", shpcPageNum);
        }catch(Exception e){
            map.put("success", false);
            map.put("shpcPageNum", shpcPageNum);
            map.put("msg", "修改失败！");
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/bwh/edit", map);
    }


    /**
     * 调转到修改页面
     * @return
     */
//    @RequiresPermissions("admin-assetStatus:delete")
    @RequestMapping(value = "/delete/")
    public @ResponseBody Map<String, Object> delete(@RequestParam("id")String id,@RequestParam(value = "shpcPageNum", defaultValue = "1") int shpcPageNum) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            Shpc shpc = this.shpcService.getByPK(id);
            if(shpc != null){
                this.shpcService.delete(shpc);
            }
            map.put("shpcPageNum", shpcPageNum);
            map.put("success", true);
        }catch(Exception e){
            map.put("shpcPageNum", shpcPageNum);
            map.put("success", false);
            map.put("msg", "删除失败！");
            throw new GenericException(e);
        }
        return map;

    }

    /**
     * 保存部务会信息
     * @return
     */
    @RequestMapping(value = "/save")
    public @ResponseBody Map<String, Object> save(@ModelAttribute ShpcVo shpcVo, HttpServletRequest req,@RequestParam(value="clFile",required = false) MultipartFile clFile) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        Shpc shpc = null;
        int newPx = shpcVo.getPx();
        int oldPx = 0;
        Integer oldPxInteger = this.shpcService.getMaxPx();
        if(oldPxInteger != null){
            oldPx = oldPxInteger.intValue();
        }else{
            oldPx = 1;
        }

        try {
            if (shpcVo != null) {
                String id = shpcVo.getId();
                if (id != null && id.length() > 0) {//修改
                    shpc = this.shpcService.getByPK(id);
                    oldPx = shpc.getPx();
                } else {//新增
                    shpc = new Shpc();
                }
                UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
                BeanUtils.copyProperties(shpc, shpcVo);
                if (shpcVo.getPcsjValue() != null && !shpcVo.getPcsjValue().equals("")) {
                    Date pcsjDate = DateUtil.parseDate(shpcVo.getPcsjValue().toString(), "yyyyMMdd");
                    shpc.setPcsj(pcsjDate);
                }
                shpc.setTenant(userLoginDetails.getTenant());
                if (clFile != null && !clFile.isEmpty()) {
                    if (shpcVo.getSjlx().equals(Shpc.SJLX_CL)) {//材料文件上传
                        String fileName = clFile.getOriginalFilename();
                        if (fileName.endsWith(".doc") || fileName.endsWith(".DOC")
                                || fileName.endsWith(".docx") || fileName.endsWith(".DOCX")) {
                            String fileDir = uploadAbsolutePath + ShpcService.ATTS_PATH;
                            File _fileDir = new File(fileDir);
                            if (_fileDir.exists() == false) {
                                _fileDir.mkdirs();
                            }
                            //附件存储路径
                            String savePath = fileDir + UUIDUtil.getUUID() +"."+ FileUtil.getExtend(fileName);
                            try {
                                FileOutputStream fos = new FileOutputStream(new File(savePath));
                                fos.write(clFile.getBytes());
                                fos.flush();
                                fos.close();

                                //PDF路径
                                String pdfPath = ShpcService.ATTS_PATH + UUIDUtil.getUUID() + ".pdf";
                                String pdfRealPath = uploadAbsolutePath + pdfPath;
                                WordConvertUtil.newInstance().convert(savePath, pdfRealPath, WordConvertUtil.PDF);
                                FileUtils.deleteQuietly(new File(savePath));
                                shpc.setFilePath(pdfPath);
                            } catch (Exception e) {
                                e.printStackTrace();
                                throw new GenericException(e);
                            }
                        }
                    }
                }
                if (shpcVo.getSjlx().equals(Shpc.SJLX_GB)) {
                    shpc.setFilePath("");
                }
                String shpcId = id;
                if (oldPx != newPx) {
                    this.shpcService.updatePx(oldPx, newPx);
                }
                if (id != null && id.length() > 0) {
                    BeanTrans.setBaseProperties(shpc, userLoginDetails, "update");
                    this.shpcService.update(shpc);
                } else {
                    BeanTrans.setBaseProperties(shpc, userLoginDetails, "save");
                    shpcId = this.shpcService.save(shpc);
                }

                if (clFile != null && !clFile.isEmpty()) {
                    if (shpcVo.getSjlx().equals(Shpc.SJLX_GB)) {
                        String fileName = clFile.getOriginalFilename();
                        if(fileName.endsWith(".doc") ||fileName.endsWith(".DOC") ||fileName.endsWith(".docx") ||fileName.endsWith(".DOCX") ) {
                            String fileDir = uploadAbsolutePath + Sha01Service.ATTS_PATH;
                            File _fileDir = new File(fileDir);
                            if (_fileDir.exists() == false) {
                                _fileDir.mkdirs();
                            }
                            String savePath = fileDir + UUIDUtil.getUUID() +"."+ FileUtil.getExtend(fileName);;

                            try {
                                FileOutputStream fos = new FileOutputStream(new File(savePath));
                                fos.write(clFile.getBytes());
                                fos.flush();
                                fos.close();

                                //处理上传文件
                                //先将word转成Map
                                String tmplateWordPath = uploadAbsolutePath+Sha01Service.IMPORT_DOC_TEMPLATE;
                                WordUtil wordUtil = WordUtil.newInstance();
                                Map<String, String> dataMap = wordUtil.convertMapByTemplate(savePath, tmplateWordPath, "");
                                sha01Service.saveFromWordDataMap(userLoginDetails.getTenant(), dataMap, shpcId);
                                FileUtils.deleteQuietly(new File(savePath));
                            } catch (Exception e) {
                                e.printStackTrace();
                                throw new GenericException(e);
                            }
                        }
                    }
                }
                map.put("success", true);
            }
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return map;
    }
    /**
     * 调转到修改页面
     * @return
     */
    @RequestMapping(value ="/changeShZt/{id}")
    public @ResponseBody Map<String, Object> changeShZt(@PathVariable("id")String id) {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            String shZtValue = "";
            Shpc shpc = this.shpcService.getByPK(id);
            if(shpc.getShZt()==0){
                shpc.setShZt(1);
                shZtValue = "已上会";
            }else{
                shpc.setShZt(0);
                shZtValue = "未上会";
            }
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            BeanTrans.setBaseProperties(shpc, userLoginDetails, "update");
            this.shpcService.update(shpc);
            map.put("success", true);
            map.put("shZtValue", shZtValue);
        } catch (Exception e) {
            map.put("success", false);
            throw new GenericException(e);
        }
        return map;
    }

    @RequestMapping(value="/ajax/down")
    public void templateDown(String id,HttpServletRequest req, HttpServletResponse resp) throws Exception{
        Shpc shpc = this.shpcService.getByPK(id);
        if(shpc.getFilePath()!=null &&!shpc.getFilePath().equals("")){
           String fileRealPath =uploadAbsolutePath+shpc.getFilePath();
            resp.setContentType("multipart/form-data");
            //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
            resp.setHeader("Content-Disposition", "attachment;fileName="
                    +encode(fileRealPath.substring(fileRealPath.lastIndexOf(File.separator)+1)));
            OutputStream output=resp.getOutputStream();
            byte[] b= FileUtils.readFileToByteArray(new File(fileRealPath));
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
