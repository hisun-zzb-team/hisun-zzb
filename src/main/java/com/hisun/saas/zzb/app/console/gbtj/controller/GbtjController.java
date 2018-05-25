/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.gbtj.controller;

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
import com.hisun.saas.zzb.app.console.gbtj.entity.Gbtj;
import com.hisun.saas.zzb.app.console.gbtj.service.GbtjService;
import com.hisun.saas.zzb.app.console.gbtj.vo.GbtjVo;
import com.hisun.saas.zzb.app.console.gbtj.vo.Sha01JsonDataVo;
import com.hisun.saas.zzb.app.console.gbtj.vo.Sha01JsonSavaVo;
import com.hisun.saas.zzb.app.console.gbtj.vo.Sha01JsonVo;
import com.hisun.saas.zzb.app.console.shpc.vo.Sha01dascqktipsVo;
import com.hisun.saas.sys.util.BeanTrans;
import com.hisun.util.JacksonUtil;
import com.hisun.util.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.LobHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/gbtj")
public class GbtjController extends BaseController {
    @Resource
    private GbtjService gbtjService;

    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping("/")
    public ModelAndView list(HttpServletRequest req, String pId,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("px"));

            Long total = this.gbtjService.count(query);
            List<Gbtj> gbtjs = this.gbtjService.list(query, orderBy, pageNum,
                    pageSize);
            List<GbtjVo> gbtjVos = new ArrayList<GbtjVo>();
            if (gbtjs != null) {
                for (Gbtj gbtj : gbtjs) {
                    GbtjVo vo = new GbtjVo();
                    BeanUtils.copyProperties(vo, gbtj);
                    gbtjVos.add(vo);
                }
            }
            PagerVo<GbtjVo> pager = new PagerVo<GbtjVo>(gbtjVos, total.intValue(),
                    pageNum, pageSize);
            map.put("pager", pager);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/gbtj/list", map);
    }


    @RequestMapping(value = "/add")
    public ModelAndView add() {
        GbtjVo vo = new GbtjVo();
        vo.setPx(99);
        return new ModelAndView("/saas/zzb/app/console/gbtj/add","gbtj",vo);

    }


    @RequestMapping(value = "/edit")
    public ModelAndView edit(@RequestParam(value="id")String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Gbtj gbtj = this.gbtjService.getByPK(id);
            GbtjVo gbtjVo = new GbtjVo();
            if (gbtj == null) {
                logger.error("数据不存在");
                throw new GenericException("数据不存在");
            }
            BeanUtils.copyProperties(gbtjVo, gbtj);
            map.put("gbtj", gbtjVo);
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "修改失败！");
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/gbtj/edit", map);
    }


    /**
     * 调转到修改页面
     * @return
     */
//    @RequiresPermissions("admin-assetStatus:delete")
    @RequestMapping(value = "/delete/{id}")
    public @ResponseBody
    Map<String, Object> delete(@PathVariable("id")String AssetStatusId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            Gbtj gbtj = this.gbtjService.getByPK(AssetStatusId);
            if(gbtj != null){
                this.gbtjService.delete(gbtj);
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
     * 保存部务会信息
     * @return
     */
    @RequestMapping(value = "/save")
    public @ResponseBody Map<String, Object> save(@ModelAttribute GbtjVo gbtjVo, HttpServletRequest req) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        Gbtj gbtj = null;
        try {
            if (gbtjVo != null) {
                String id = gbtjVo.getId();
                if (id != null && id.length() > 0) {//修改
                    gbtj = this.gbtjService.getByPK(id);
                } else {//新增
                    gbtj = new Gbtj();
                }
                UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
                BeanUtils.copyProperties(gbtj, gbtjVo);
                gbtj.setTenant(userLoginDetails.getTenant());
                if (id != null && id.length() > 0) {
                    BeanTrans.setBaseProperties(gbtj, userLoginDetails, "update");
                    this.gbtjService.update(gbtj);
                } else {
                    BeanTrans.setBaseProperties(gbtj, userLoginDetails, "save");
                    this.gbtjService.save(gbtj);
                }
                map.put("success", true);
            }
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return map;
    }
    /**
     * 调转到数据编辑页面
     * @return
     */
    @RequestMapping(value = "/ajax/editJsonData")
    public ModelAndView editJsonData(@RequestParam(value="id")String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        Gbtj gbtj = this.gbtjService.getByPK(id);
        Sha01JsonVo jsonMap = new Sha01JsonVo();
        if(gbtj != null){
            String content = gbtj.getTjjsondata();
            if(content!=null && !("").equals(content)) {
                jsonMap = JacksonUtil.nonDefaultMapper().fromJson(content, Sha01JsonVo.class);
                map.put("data", jsonMap.getData());
            }else{
                List<Sha01JsonDataVo> data = new ArrayList<Sha01JsonDataVo>();
                map.put("data",data);
            }
        }

        map.put("id",id);
        return new ModelAndView("/saas/zzb/app/console/gbtj/addAndEditJsonData", map);
    }

    /**
     * 保存json数据
     * @return
     */
    @RequestMapping(value = "/saveJsonData")
    public @ResponseBody Map<String, Object> saveJsonData(@RequestParam(value="id")String id, @ModelAttribute Sha01JsonSavaVo vo, HttpServletRequest req) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        Gbtj gbtj = this.gbtjService.getByPK(id);
        try {
            Sha01JsonVo sha01JsonVo = new Sha01JsonVo();
            List<Sha01JsonDataVo> jsonDataVos = Lists.newArrayList();
            sha01JsonVo.setTitle("年龄结构");
            //去掉name为空的情况
            if(vo != null && vo.getJsonDataVos() != null && vo.getJsonDataVos().size() > 0){
                for (Sha01JsonDataVo sha01JsonDataVo : vo.getJsonDataVos()){
                    if(StringUtils.isNoneBlank(sha01JsonDataVo.getName())){
                        jsonDataVos.add(sha01JsonDataVo);
                    }
                }
                sha01JsonVo.setData(jsonDataVos);
                String tjjsondataValue = JacksonUtil.nonDefaultMapper().toJson(sha01JsonVo);
                gbtj.setTjjsondata(tjjsondataValue);
                this.gbtjService.update(gbtj);
            }
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            map.put("message", "保存出错");
        }
        return map;
    }

    // 饼状图预览
    @RequestMapping(value = "/ajax/btView")
    public ModelAndView btView(@RequestParam(value="id")String id) {
        Map<String, Object> model = new HashMap<String, Object>();
        try {
            Gbtj gbtj = this.gbtjService.getByPK(id);
            Sha01JsonVo jsonMap = new Sha01JsonVo();
            int textSize = 0;
            if(gbtj != null){
                String content = gbtj.getTjjsondata();
                if(content!=null && !("").equals(content)) {
                    jsonMap = JacksonUtil.nonDefaultMapper().fromJson(content, Sha01JsonVo.class);
                    textSize = jsonMap.getData().size();
                }
            }


            List<Map<String, Object>> pieList = new ArrayList<Map<String, Object>>();
            Map<String, Object> pie_hitMap = new HashMap<String, Object>();
            String[] text = new String[textSize];

            int i = 0;
            if(jsonMap.getData()!=null) {
                for (Sha01JsonDataVo sha01JsonDataVo : jsonMap.getData()) {
                    pie_hitMap = new HashMap<String, Object>();
                    pie_hitMap.put("name", sha01JsonDataVo.getName() + "(" + sha01JsonDataVo.getValue() + ")");
                    pie_hitMap.put("value", sha01JsonDataVo.getValue());
                    pieList.add(pie_hitMap);

                    text[i] = sha01JsonDataVo.getName() + "(" + sha01JsonDataVo.getValue() + ")";
                    i++;
                }
            }

            model.put("pieData", JacksonUtil.nonDefaultMapper().toJson(pieList));
            model.put("name","干部年龄结构");
            model.put("text", JacksonUtil.nonDefaultMapper().toJson(text));


        } catch (Exception e) {
            throw new GenericException(e);
        }

        ModelAndView modelAndView = new ModelAndView(
                "/saas/zzb/app/console/gbtj/btView", model);
        return modelAndView;
    }
}
