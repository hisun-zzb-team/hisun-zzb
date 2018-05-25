/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gendata.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.ErrorMsgShowException;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.util.BeanTrans;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMc;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcService;
import com.hisun.saas.zzb.app.console.gbmc.vo.GbMcVo;
import com.hisun.saas.zzb.app.console.gbtj.entity.Gbtj;
import com.hisun.saas.zzb.app.console.gbtj.service.GbtjService;
import com.hisun.saas.zzb.app.console.gbtj.vo.GbtjVo;
import com.hisun.saas.zzb.app.console.gendata.entity.DataPacketContent;
import com.hisun.saas.zzb.app.console.gendata.entity.Gendata;
import com.hisun.saas.zzb.app.console.gendata.service.DataPacketContentService;
import com.hisun.saas.zzb.app.console.gendata.service.GendataService;
import com.hisun.saas.zzb.app.console.gendata.vo.GendataVo;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01gbrmspb;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01Service;
import com.hisun.saas.zzb.app.console.shpc.service.ShpcService;
import com.hisun.saas.zzb.app.console.shpc.vo.ShpcVo;

import com.hisun.util.DateUtil;
import com.hisun.util.WebUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/gendata")
public class GendataController extends BaseController{

    @Autowired
    private GendataService gendataService;

    @Resource(name="resourcesProperties")
    private Properties resourcesProperties;
    @Resource
    private GbtjService gbtjService;
    @Autowired
    private GbMcService gbMcService;
    @Autowired
    private ShpcService shpcService;
    @Autowired
    private DataPacketContentService dataPacketContentService;


    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    @RequestMapping(value = "/")
    public ModelAndView list(HttpServletRequest req, String pId,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            // query.add(CommonRestrictions.and(" shlx = :shlx", "shlx", Gendata.SHLX_BWH));
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.desc("isCurrentPacket"));
            orderBy.add(CommonOrder.desc("createDate"));
            Long total = this.gendataService.count(query);
            List<Gendata> gendatas = this.gendataService.list(query, orderBy, pageNum,
                    pageSize);
            List<GendataVo> gendataVos = new ArrayList<GendataVo>();

            if (gendatas != null) {// entity ==> vo
                for (Gendata gendata : gendatas) {
                    GendataVo vo = new GendataVo();
                    BeanUtils.copyProperties(vo, gendata);
                    if(gendata.getCreateDate()!=null && !gendata.getCreateDate().equals("")) {
                        vo.setCreateTimeValue(DateUtil.formatDateByFormat(gendata.getCreateDate(), "yyyy.MM.dd HH:mm:ss "));
                    }
                    gendataVos.add(vo);
                }
            }
            PagerVo<GendataVo> pager = new PagerVo<GendataVo>(gendataVos, total.intValue(),
                    pageNum, pageSize);
            map.put("pager", pager);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("saas/zzb/app/console/gendata/list", map);
    }


    @RequestMapping(value = "/loadGenerator")
    public ModelAndView loadGenerator(){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //获取会议研究列表数据
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            query.add(CommonRestrictions.and(" shZt = :shZt", "shZt", 0));
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("px"));
            Long total = this.shpcService.count(query);
            List<Shpc> shpcs = this.shpcService.list(query, orderBy);
            List<ShpcVo> shpcVos = new ArrayList<ShpcVo>();
            if (shpcs != null) {// entity ==> vo
                for (Shpc shpc : shpcs) {
                    ShpcVo vo = new ShpcVo();
                    BeanUtils.copyProperties(vo, shpc);
                    vo.setPcsjValue(DateUtil.formatDateByFormat(shpc.getPcsj(), "yyyyMMdd"));
                    vo.setA01Count(shpc.getSha01s().size());
                    shpcVos.add(vo);
                }
            }

            //获取干部名册数据
            query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            total = this.shpcService.count(query);
            List<GbMc> gbmcs = this.gbMcService.list(query, null);
            List<GbMcVo> gbmcVos = new ArrayList<GbMcVo>();
            if (gbmcs != null) {// entity ==> vo
                for (GbMc gbMc : gbmcs) {
                    GbMcVo vo = new GbMcVo();
                    BeanUtils.copyProperties(vo, gbMc);
                    gbmcVos.add(vo);
                }
            }

            //获取队伍统计数据
            query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            total = this.shpcService.count(query);
            List<Gbtj> gbtjs = this.gbtjService.list(query, null);
            List<GbtjVo> gbtjVos = new ArrayList<GbtjVo>();
            if (gbmcs != null) {// entity ==> vo
                for (Gbtj gbmc : gbtjs) {
                    GbtjVo vo = new GbtjVo();
                    BeanUtils.copyProperties(vo, gbmc);
                    gbtjVos.add(vo);
                }
            }
            String packetName = DateUtil.formatDateByFormat(new Date(), "yyyyMMdd HH:mm:ss")+"数据包";
            map.put("packetName", packetName);
            map.put("shpcVos", shpcVos);
            map.put("gbmcVos", gbmcVos);
            map.put("gbtjVos", gbtjVos);
        }catch (Exception e) {
            throw new GenericException(e);
        }

        return new ModelAndView("saas/zzb/app/console/gendata/generator",map);
    }

    //基于原有数据包
    @RequestMapping(value = "/loadGeneratorByoldPacket")
    public ModelAndView loadGeneratorByoldPacket(@RequestParam(value = "oldPacketId") String oldPacketId){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String oldPacketName = "";
            //=======================加载原有数据包数据源
            CommonConditionQuery query = new CommonConditionQuery();
            Gendata gendata = this.gendataService.getByPK(oldPacketId);
            oldPacketName = gendata.getPacketName();

            query.add(CommonRestrictions.and(" gendata.id = :oldPacketId", "oldPacketId",oldPacketId));
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("dataType"));
            orderBy.add(CommonOrder.asc("sort"));
            List<DataPacketContent> dataPacketContents = this.dataPacketContentService.list(query, orderBy);
            List<ShpcVo> oldShpcVos = new ArrayList<ShpcVo>();
            List<GbMcVo> oldGbmcVos = new ArrayList<GbMcVo>();
            List<GbtjVo> oldGbtjVos = new ArrayList<GbtjVo>();
            String isLoadGbcxData = "false";
            String isLoadZsxcData = "false";
            for(DataPacketContent dataPacketContent : dataPacketContents)            {
                if(dataPacketContent.getDataType()==DataPacketContent.SHPC_DATA){
                    ShpcVo vo = new ShpcVo();
                    vo.setId(dataPacketContent.getId());
                    vo.setPcmc(dataPacketContent.getName());
                    oldShpcVos.add(vo);
                }else if(dataPacketContent.getDataType()==DataPacketContent.GBMC_DATA){
                    GbMcVo vo = new GbMcVo();
                    vo.setId(dataPacketContent.getId());
                    vo.setMc(dataPacketContent.getName());
                    oldGbmcVos.add(vo);
                }else if(dataPacketContent.getDataType()==DataPacketContent.GBTJ_DATA){
                    GbtjVo vo = new GbtjVo();
                    vo.setId(dataPacketContent.getId());
                    vo.setTjmc(dataPacketContent.getName());
                    oldGbtjVos.add(vo);
                }else if(dataPacketContent.getDataType()==DataPacketContent.GBCX_DATA){
                    isLoadGbcxData = "true";
                }else if(dataPacketContent.getDataType()==DataPacketContent.ZSCX_DATA){
                    isLoadZsxcData = "true";
                }
            }
            //==============================加载新的数据源
            //获取会议研究列表数据
            query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            query.add(CommonRestrictions.and(" shZt = :shZt", "shZt", 0));
            orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("px"));
            List<Shpc> shpcs = this.shpcService.list(query, orderBy);
            List<ShpcVo> shpcVos = new ArrayList<ShpcVo>();
            if (shpcs != null) {// entity ==> vo
                for (Shpc shpc : shpcs) {
                    ShpcVo vo = new ShpcVo();
                    BeanUtils.copyProperties(vo, shpc);
                    vo.setPcsjValue(DateUtil.formatDateByFormat(shpc.getPcsj(), "yyyyMMdd"));
                    vo.setA01Count(shpc.getSha01s().size());
                    shpcVos.add(vo);
                }
            }

            //获取干部名册数据
            query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            List<GbMc> gbmcs = this.gbMcService.list(query, null);
            List<GbMcVo> gbmcVos = new ArrayList<GbMcVo>();
            if (gbmcs != null) {// entity ==> vo
                for (GbMc gbMc : gbmcs) {
                    GbMcVo vo = new GbMcVo();
                    BeanUtils.copyProperties(vo, gbMc);
                    gbmcVos.add(vo);
                }
            }

            //获取队伍统计数据
            query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            List<Gbtj> gbtjs = this.gbtjService.list(query, null);
            List<GbtjVo> gbtjVos = new ArrayList<GbtjVo>();
            if (gbmcs != null) {// entity ==> vo
                for (Gbtj gbmc : gbtjs) {
                    GbtjVo vo = new GbtjVo();
                    BeanUtils.copyProperties(vo, gbmc);
                    gbtjVos.add(vo);
                }
            }
            String packetName = DateUtil.formatDateByFormat(new Date(),  "yyyyMMdd HH:mm:ss")+"数据包";
            map.put("packetName", packetName);

            map.put("oldPacketName", oldPacketName);
            map.put("oldShpcVos", oldShpcVos);
            map.put("oldGbmcVos", oldGbmcVos);
            map.put("oldGbtjVos", oldGbtjVos);
            map.put("isLoadGbcxData", isLoadGbcxData);
            map.put("isLoadZsxcData", isLoadZsxcData);
            map.put("oldPacketId", oldPacketId);

            map.put("shpcVos", shpcVos);
            map.put("gbmcVos", gbmcVos);
            map.put("gbtjVos", gbtjVos);



        }catch (Exception e) {
            throw new GenericException(e);
        }

        return new ModelAndView("saas/zzb/app/console/gendata/generatorByoldPacket",map);
    }

    @RequestMapping(value = "/generator")
    public @ResponseBody Map<String,Object> generator(HttpServletResponse response, HttpServletRequest request) throws Exception{
        Map<String,Object> rsmap = new HashMap<String,Object>();
        try{
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            String checkBoxTypeValues = request.getParameter("checkBoxTypeValues")==null?"":request.getParameter("checkBoxTypeValues").toString();//选择需要导出的类型
            String[] generatorTypeValues= checkBoxTypeValues.split(",");

            String pcs = request.getParameter("checkHyyjValues")==null?"":request.getParameter("checkHyyjValues").toString();//会议研究ID
            String gbmcs = request.getParameter("checkGbmcValues")==null?"":request.getParameter("checkGbmcValues").toString();//干部名册ID
            String tjs = request.getParameter("checkGbtjValues")==null?"":request.getParameter("checkGbtjValues").toString();//干部统计ID
            String packetName = request.getParameter("packetName")==null?"":request.getParameter("packetName").toString();//packetName


            String appDataPath=resourcesProperties.getProperty("upload.absolute.path")+GendataService.DATA_PATH;
            File appDataDir = new File(appDataPath);
            if(appDataDir.exists()==false){
                appDataDir.mkdirs();
            }
            Map<String,String> map = new HashMap<String,String>();
            if(pcs!=null &&pcs.length()>0){
                map.put(GendataVo.SHPC_DATA,pcs);
            }

            if(gbmcs!=null &&gbmcs.length()>0){
                map.put(GendataVo.GBMC_DATA,gbmcs);
            }

            if(tjs!=null &&tjs.length()>0){
                map.put(GendataVo.GBTJ_DATA,tjs);
            }
            if(!checkBoxTypeValues.equals("")){
                for (String generatorTypeValue : generatorTypeValues) {
                    if(generatorTypeValue.equals(GendataVo.GBCX_DATA)){
                        map.put(GendataVo.GBCX_DATA,"TRUE");
                    }
//                    else if(generatorTypeValue.equals(GendataVo.ZSCX_DATA)){
//                        map.put(GendataVo.ZSCX_DATA,"TRUE");
//                    }
                }
            }
            Gendata gendata = new Gendata();
            gendata.setTenant(userLoginDetails.getTenant());
            gendata.setPacketName(packetName);


            String id = this.gendataService.saveAppData(gendata,map);
            rsmap.put("gendataId", id);
        }catch(Exception e){
            logger.error(e, e);
            rsmap.put("success", false);
            rsmap.put("message", "系统错误，请联系管理员!");
            return rsmap;
        }
        rsmap.put("success", true);

        return rsmap;
    }

    @RequestMapping(value = "/generatorByoldPacket")
    public @ResponseBody Map<String,Object> generatorByoldPacket(HttpServletResponse response, HttpServletRequest request) throws Exception{
        Map<String,Object> rsmap = new HashMap<String,Object>();
        try{
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            String oldcheckBoxTypeValues = request.getParameter("oldcheckBoxTypeValues")==null?"":request.getParameter("oldcheckBoxTypeValues").toString();//选择需要导出的类型
            String[] oldgeneratorTypeValues= oldcheckBoxTypeValues.split(",");

            String oldpcs = request.getParameter("oldcheckHyyjValues")==null?"":request.getParameter("oldcheckHyyjValues").toString();//会议研究ID
            String oldgbmcs = request.getParameter("oldcheckGbmcValues")==null?"":request.getParameter("oldcheckGbmcValues").toString();//干部名册ID
            String oldtjs = request.getParameter("oldcheckGbtjValues")==null?"":request.getParameter("oldcheckGbtjValues").toString();//干部统计ID
            String oldPacketId = request.getParameter("oldPacketId")==null?"":request.getParameter("oldPacketId").toString();//干部统计ID


            String checkBoxTypeValues = request.getParameter("checkBoxTypeValues")==null?"":request.getParameter("checkBoxTypeValues").toString();//选择需要导出的类型
            String[] generatorTypeValues= checkBoxTypeValues.split(",");

            String pcs = request.getParameter("checkHyyjValues")==null?"":request.getParameter("checkHyyjValues").toString();//会议研究ID
            String gbmcs = request.getParameter("checkGbmcValues")==null?"":request.getParameter("checkGbmcValues").toString();//干部名册ID
            String tjs = request.getParameter("checkGbtjValues")==null?"":request.getParameter("checkGbtjValues").toString();//干部统计ID
            String packetName = request.getParameter("packetName")==null?"":request.getParameter("packetName").toString();//packetName


            String appDataPath=resourcesProperties.getProperty("upload.absolute.path")+GendataService.DATA_PATH;
            File appDataDir = new File(appDataPath);
            if(appDataDir.exists()==false){
                appDataDir.mkdirs();
            }
            Map<String,String> oldMap = new HashMap<String,String>();
            if(oldpcs!=null &&oldpcs.length()>0){
                oldMap.put(GendataVo.SHPC_DATA,oldpcs);
            }

            if(oldgbmcs!=null &&oldgbmcs.length()>0){
                oldMap.put(GendataVo.GBMC_DATA,oldgbmcs);
            }

            if(oldtjs!=null &&oldtjs.length()>0){
                oldMap.put(GendataVo.GBTJ_DATA,oldtjs);
            }
            if(!oldcheckBoxTypeValues.equals("")){
                for (String generatorTypeValue : oldgeneratorTypeValues) {
                    if(generatorTypeValue.equals(GendataVo.GBCX_DATA)){
                        oldMap.put(GendataVo.GBCX_DATA,"TRUE");
                    }
//                    else if(generatorTypeValue.equals(GendataVo.ZSCX_DATA)){
//                        oldMap.put(GendataVo.ZSCX_DATA,"TRUE");
//                    }
                }
            }

            Map<String,String> map = new HashMap<String,String>();
            if(pcs!=null &&pcs.length()>0){
                map.put(GendataVo.SHPC_DATA,pcs);
            }

            if(gbmcs!=null &&gbmcs.length()>0){
                map.put(GendataVo.GBMC_DATA,gbmcs);
            }

            if(tjs!=null &&tjs.length()>0){
                map.put(GendataVo.GBTJ_DATA,tjs);
            }
            if(!checkBoxTypeValues.equals("")){
                for (String generatorTypeValue : generatorTypeValues) {
                    if(generatorTypeValue.equals(GendataVo.GBCX_DATA)){
                        map.put(GendataVo.GBCX_DATA,"TRUE");
                    }
//                    else if(generatorTypeValue.equals(GendataVo.ZSCX_DATA)){
//                        map.put(GendataVo.ZSCX_DATA,"TRUE");
//                    }
                }
            }
            Gendata gendata = new Gendata();
            gendata.setTenant(userLoginDetails.getTenant());
            gendata.setPacketName(packetName);

            Gendata oldGendata = this.gendataService.getByPK(oldPacketId);

            String id = this.gendataService.saveAppDataFromAnotherAppData(gendata,map,oldGendata,oldMap);
            rsmap.put("gendataId", id);
        }catch(Exception e){
            logger.error(e, e);
            rsmap.put("success", false);
            rsmap.put("message", "系统错误，请联系管理员!");
            return rsmap;
        }
        rsmap.put("success", true);

        return rsmap;
    }

    @RequestMapping(value = "/generator/init")
    public @ResponseBody Map<String,Object> generatorInitData (HttpServletResponse response,
                                                              HttpServletRequest request) throws Exception{
        Map<String,Object> rsmap = new HashMap<String,Object>();
        try{
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();

            String appDataPath=resourcesProperties.getProperty("upload.absolute.path")+GendataService.DATA_PATH;
            File appDataDir = new File(appDataPath);
            if(appDataDir.exists()==false){
                appDataDir.mkdirs();
            }
            Gendata gendata = new Gendata();
            gendata.setTenant(userLoginDetails.getTenant());
            gendata.setPacketName("初始化数据包");
            String id = this.gendataService.saveAppInitData(gendata);
            rsmap.put("gendataId", id);
        }catch(Exception e){
            logger.error(e, e);
            rsmap.put("success", false);
            rsmap.put("message", "系统错误，请联系管理员!");
            return rsmap;
        }
        rsmap.put("success", true);

        return rsmap;
    }

    @RequestMapping(value="/zip/down")
    public void zipDown(String id,HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String zipPath = this.gendataService.getByPK(id).getPath();
        resp.setContentType("multipart/form-data");
        resp.setHeader("Content-Disposition", "attachment;fileName="+encode(GendataService.DATA_PACKET_NAME+".zip"));
        OutputStream output=resp.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(new File(uploadAbsolutePath+zipPath));
        byte[] buffer = new byte[8192];
        int length;
        while ((length = fileInputStream.read(buffer)) != -1) {
            output.write(buffer, 0, length);
        }
        output.flush();
        output.close();

    }


    private String encode(String filename) throws UnsupportedEncodingException {
        if (WebUtil.getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            filename = URLEncoder.encode(filename, "UTF-8");
        } else {
            filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
        }
        return filename;
    }

    /**
     * 调转到修改页面
     * @return
     */
    @RequestMapping(value ="/changePacketZt/{id}")
    public @ResponseBody Map<String, Object> changePacketZt(@PathVariable("id")String id) {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            query.add(CommonRestrictions.and(" isCurrentPacket = :isCurrentPacket", "isCurrentPacket", 1));
            List<Gendata> gendatas = this.gendataService.list(query, null);
            for(Gendata oldCurrentPacketgendata : gendatas ){
                oldCurrentPacketgendata.setIsCurrentPacket(0);
                UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
                BeanTrans.setBaseProperties(oldCurrentPacketgendata, userLoginDetails, "update");
                this.gendataService.update(oldCurrentPacketgendata);
            }
            Gendata gendata = this.gendataService.getByPK(id);
            gendata.setIsCurrentPacket(1);
            UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
            BeanTrans.setBaseProperties(gendata, userLoginDetails, "update");
            this.gendataService.update(gendata);


            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            throw new GenericException(e);
        }
        return map;
    }

    @RequestMapping(value = "/delete/{id}")
    public @ResponseBody Map<String, Object> delete(@PathVariable("id")String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            String[] ids = id.split(",");
            for(int i=0;i<ids.length;i++){
                Gendata gendata = this.gendataService.getByPK(ids[i]);
                if(gendata != null){
                    this.gendataService.delete(gendata);
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
}
