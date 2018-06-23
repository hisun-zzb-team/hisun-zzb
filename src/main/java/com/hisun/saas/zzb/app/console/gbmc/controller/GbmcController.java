/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbmc.controller;

import com.aspose.words.*;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.gbmc.entity.*;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcA01Service;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcA01gbrmspbService;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcB01Service;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcService;
import com.hisun.saas.zzb.app.console.gbmc.vo.GbMcVo;
import com.hisun.saas.sys.util.BeanTrans;
import com.hisun.util.CompressUtil;
import com.hisun.util.UUIDUtil;
import com.hisun.util.WordUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.util.List;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/gbmc")
public class GbmcController extends BaseController{


    @Autowired
    private GbMcService gbMcService;
    @Autowired
    private GbMcA01Service gbMcA01Service;

    @Resource
    private GbMcB01Service gbMcB01Service;

    @Autowired
    private GbMcA01gbrmspbService gbMcA01gbrmspbService;

    @Value("${sys.upload.absolute.path}")
    private String uploadAbsolutePath;

    @RequestMapping("/")
    public ModelAndView list(HttpServletRequest req, String mcQuery,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            if(mcQuery!=null && !mcQuery.equals("")){
                query.add(CommonRestrictions.and(" mc like:mcQuery", "mcQuery", "%"+ mcQuery+ "%"));
            }
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("px"));


            Long total = this.gbMcService.count(query);
            List<GbMc> gbmcs = this.gbMcService.list(query, orderBy, pageNum,pageSize);
            List<GbMcVo> gbMcVos = new ArrayList<GbMcVo>();
            if (gbmcs != null) {// entity ==> vo
                for (GbMc gbMc : gbmcs) {
                    GbMcVo vo = new GbMcVo();
                    BeanUtils.copyProperties(vo, gbMc);
                    vo.setA01Count(this.gbMcService.getA01Count(gbMc.getId()));
//                    if(gbMc.getIsMl()==GbMc.WML ){
//                        if(gbMc.getGbMcB01s()!=null && gbMc.getGbMcB01s().size()>0) {
//                            vo.setMcb01id(gbMc.getGbMcB01s().get(0).getId());
//                        }
//                    }
                    //vo.setA01Count(gbMc.getGbMcA01s().size());
                    gbMcVos.add(vo);
                }
            }
            PagerVo<GbMcVo> pager = new PagerVo<GbMcVo>(gbMcVos, total.intValue(),
                    pageNum, pageSize);
            map.put("pager", pager);
            map.put("mcQuery", mcQuery);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/gbmc/list", map);
    }
    //=============================演示使用
    @RequestMapping("/ys")
    public ModelAndView ysList(HttpServletRequest req, String mcQuery,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            if(mcQuery!=null && !mcQuery.equals("")){
                query.add(CommonRestrictions.and(" mc like:mcQuery", "mcQuery", "%"+ mcQuery+ "%"));
            }
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("px"));


            Long total = this.gbMcService.count(query);
            List<GbMc> gbmcs = this.gbMcService.list(query, orderBy, pageNum,pageSize);
            List<GbMcVo> gbMcVos = new ArrayList<GbMcVo>();
            if (gbmcs != null) {// entity ==> vo
                for (GbMc gbMc : gbmcs) {
                    GbMcVo vo = new GbMcVo();
                    BeanUtils.copyProperties(vo, gbMc);
                    vo.setA01Count(this.gbMcService.getA01Count(gbMc.getId()));
//                    if(gbMc.getIsMl()==GbMc.WML ){
//                        if(gbMc.getGbMcB01s()!=null && gbMc.getGbMcB01s().size()>0) {
//                            vo.setMcb01id(gbMc.getGbMcB01s().get(0).getId());
//                        }
//                    }
                    //vo.setA01Count(gbMc.getGbMcA01s().size());
                    gbMcVos.add(vo);
                }
            }
            PagerVo<GbMcVo> pager = new PagerVo<GbMcVo>(gbMcVos, total.intValue(),
                    pageNum, pageSize);
            map.put("pager", pager);
            map.put("mcQuery", mcQuery);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/gbmc/list", map);
    }
    @RequestMapping(value = "/add")
    public ModelAndView add() {
        GbMcVo vo = new GbMcVo();
        vo.setPx(99);
        return new ModelAndView("/saas/zzb/app/console/gbmc/add","gbMc",vo);

    }


    @RequestMapping(value = "/edit")
    public ModelAndView edit(@RequestParam(value="id")String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            GbMc gbMc = this.gbMcService.getByPK(id);
            GbMcVo gbMcVo = new GbMcVo();
            if (gbMc == null) {
                logger.error("数据不存在");
                throw new GenericException("数据不存在");
            }
            BeanUtils.copyProperties(gbMcVo, gbMc);
            map.put("gbMc", gbMcVo);
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "修改失败！");
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/gbmc/edit", map);
    }


    /**
     * 调转到修改页面
     * @return
     */
//    @RequiresPermissions("admin-assetStatus:delete")
    @RequestMapping(value = "/delete/{id}")
    public @ResponseBody
    Map<String, Object> delete(@PathVariable("id")String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            GbMc gbMc = this.gbMcService.getByPK(id);
            if(gbMc != null){
                this.gbMcService.delete(gbMc);
            }
            map.put("success", true);
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "删除失败！");
            throw new GenericException(e);
        }
        return map;

    }

    /**
     * 保存名册信息
     * @return
     */
    @RequestMapping(value = "/save")
    public @ResponseBody Map<String, Object> save(@ModelAttribute GbMcVo gbMcVo, HttpServletRequest req
            ,@RequestParam(value="gbmcFile",required = false) MultipartFile gbmcFile
            ,@RequestParam(value="a01File",required = false) MultipartFile a01File
            ,@RequestParam(value="zpFile",required = false) MultipartFile zpFile) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        GbMc gbMc = null;
        try {
            if (gbMcVo != null) {
                String id = gbMcVo.getId();
                if (com.hisun.util.StringUtils.isEmpty(id)==false) {//修改
                    gbMc = this.gbMcService.getByPK(id);
                } else {//新增
                    gbMc = new GbMc();
                }
                UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
                BeanUtils.copyProperties(gbMc, gbMcVo);
                gbMc.setTenant(userLoginDetails.getTenant());
                if (com.hisun.util.StringUtils.isEmpty(id)==false) {
                    BeanTrans.setBaseProperties(gbMc, userLoginDetails, "update");
                    this.gbMcService.update(gbMc);
                } else {
                    BeanTrans.setBaseProperties(gbMc, userLoginDetails, "save");
                    String gbmcDir = uploadAbsolutePath + GbMcService.ATTS_PATH;//名册
                    String gbrmspbDir = uploadAbsolutePath + GbMcA01gbrmspbService.ATTS_PATH;//干部任免审批表
                    String zpDir = uploadAbsolutePath + GbMcA01Service.IMG_PATH;//照片
                    if (gbmcFile != null && !gbmcFile.isEmpty()) {
                        //处理名册数据
                        String fileName = gbmcFile.getOriginalFilename();
                        if (fileName.endsWith(".doc") || fileName.endsWith(".DOC")
                                || fileName.endsWith(".docx") || fileName.endsWith(".DOCX")) {
                            File gbmcDirFile = new File(gbmcDir);
                            if (gbmcDirFile.exists() == false) {
                                gbmcDirFile.mkdirs();
                            }
                            String gbmcWordPath = gbmcDir + UUIDUtil.getUUID() + "_" + fileName;
                            File gbmcWordFile = new File(gbmcWordPath);
                            FileOutputStream fos = new FileOutputStream(gbmcWordFile);
                            fos.write(gbmcFile.getBytes());
                            fos.flush();
                            fos.close();

                            //读取模板
                            WordUtil wordUtil = WordUtil.newInstance();
                            String wordPathTemplate = uploadAbsolutePath + GbMcA01Service.IMPORT_DOC_TEMPLATE;
                            List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
                            Document templateDoc = wordUtil.read(wordPathTemplate);
                            Map<String, Integer> templateMap = wordUtil.generateTemplateMap(templateDoc);

                            //解析word
                            Document document = wordUtil.read(gbmcWordPath);
                            NodeCollection collection = document.getChildNodes(NodeType.TABLE, true);
                            for (Iterator<Table> tables = collection.iterator(); tables.hasNext(); ) {
                                Table table = tables.next();
                                NodeCollection cells = table.getChildNodes(NodeType.CELL, true);
                                dataList.add(wordUtil.convertMapByTemplate(cells, templateMap));
                            }
                            this.gbMcService.saveFromWordDataMap(gbMc,dataList);
                            FileUtils.deleteQuietly(gbmcWordFile);
                            //处理干部信息及照片
                            if (a01File != null && !a01File.isEmpty()) {
                                if (a01File.getOriginalFilename().toLowerCase().endsWith(".zip")) {
                                    File gbrmspbDirFile = new File(gbrmspbDir);
                                    if (gbrmspbDirFile.exists() == false) {
                                        gbrmspbDirFile.mkdirs();
                                    }
                                    String gbrmspbZipFilePath = gbrmspbDir + UUIDUtil.getUUID() + ".zip";
                                    File gbrmspbZipFile = new File(gbrmspbZipFilePath);
                                    FileOutputStream fosGbrmspbZip = new FileOutputStream(gbrmspbZipFilePath);
                                    fosGbrmspbZip.write(a01File.getBytes());
                                    fosGbrmspbZip.flush();
                                    fosGbrmspbZip.close();
                                    String gbrmspbUnzipFileTempPath =  gbrmspbDir + UUIDUtil.getUUID()+File.separator;
                                    CompressUtil.unzip(gbrmspbZipFilePath, gbrmspbUnzipFileTempPath);
                                    //处理照片
                                    File zpDirFile = new File(zpDir);
                                    if (zpDirFile.exists() == false) {
                                        zpDirFile.mkdirs();
                                    }
                                    String zpZipFilePath = zpDir + UUIDUtil.getUUID() + ".zip";
                                    File zpZipFile = new File(zpZipFilePath);
                                    FileOutputStream foszpZip = new FileOutputStream(zpZipFile);
                                    foszpZip.write(zpFile.getBytes());
                                    foszpZip.flush();
                                    foszpZip.close();
                                    String zpUnzipFileTempPath = zpDir+UUIDUtil.getUUID()+File.separator;
                                    CompressUtil.unzip(zpZipFilePath, zpUnzipFileTempPath);
                                    this.gbMcA01Service.updateA01FromYwJson(gbMc.getId(), gbrmspbUnzipFileTempPath, zpUnzipFileTempPath);
                                    FileUtils.deleteQuietly(gbrmspbZipFile);
                                    FileUtils.deleteQuietly(zpZipFile);
                                    FileUtils.deleteDirectory(new File(gbrmspbUnzipFileTempPath));
                                    FileUtils.deleteDirectory(new File(zpUnzipFileTempPath));
                                }
                            }
                        }

                    }
                }
            }
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            map.put("message", "修改失败");
            throw new GenericException(e);
        }
        return map;
    }



}
