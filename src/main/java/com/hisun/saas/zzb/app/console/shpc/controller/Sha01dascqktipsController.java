/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.app.console.shpc.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.entity.TombstoneEntity;
import com.hisun.base.exception.GenericException;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01dascqktips;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01Service;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01dascqktipsService;
import com.hisun.saas.zzb.app.console.shpc.vo.Sha01dascqktipsVo;
import com.hisun.saas.sys.util.BeanTrans;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzj on 2017/10/11.
 */
@Controller
@RequestMapping("/zzb/app/Sha01/dascqk/dascqktips")
public class Sha01dascqktipsController extends BaseController {
    @Autowired
    private Sha01Service sha01Service;

    @Autowired
    private Sha01dascqktipsService sha01dascqktipsService;

    /**
     * 调转到新增页面
     * @return
     */
    @RequestMapping(value = "/ajax/add")
    public ModelAndView add() {
        return new ModelAndView("/saas/zzb/app/console/Sha01/dascqktips/addAndEdit");
    }
    /**
     * 调转到修改页面
     * @return
     */
    @RequestMapping(value = "/ajax/edit")
    public ModelAndView edit(@RequestParam(value="sha01Id")String sha01Id) {
        Map<String, Object> map = new HashMap<String, Object>();
        Sha01 sha01 = this.sha01Service.getByPK(sha01Id);
        if(sha01 == null || sha01.getDascqks().size()==0 ||sha01.getDascqks().get(0).getSha01dascqktips().size()==0 ){
            logger.error("数据不存在");
            throw new GenericException("数据不存在");
        }
        map.put("vo", sha01.getDascqks().get(0).getSha01dascqktips().get(0));
        return new ModelAndView("/saas/zzb/app/console/Sha01/dascqktips/addAndEdit", map);
    }


    /**
     * 删除
     * @return
     */
    @RequestMapping(value = "/delete/{sha01Id}")
    public @ResponseBody Map<String, Object> delete(@PathVariable("sha01Id")String sha01Id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            Sha01 sha01 = this.sha01Service.getByPK(sha01Id);
            if(sha01 != null && sha01.getDascqks().size()>0 &&sha01.getDascqks().get(0).getSha01dascqktips().size()>0 ){

                Sha01dascqktips sha01dascqktips = sha01.getDascqks().get(0).getSha01dascqktips().get(0);
                this.sha01dascqktipsService.deleteBySql(sha01dascqktips.getId());
                 map.put("success", true);
            }
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "删除失败！");
            throw new GenericException(e);
        }
        return map;

    }

    /**
     * 保存档案备注
     * @return
     */
    @RequestMapping(value = "/save")
    public @ResponseBody Map<String, Object> save(@ModelAttribute Sha01dascqktipsVo sha01dascqktipsVo, HttpServletRequest req) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        Sha01dascqktips sha01dascqktips = null;

        try {
            if (sha01dascqktipsVo != null) {
                String id = sha01dascqktipsVo.getId();
                Sha01 sha01 = this.sha01Service.getByPK(sha01dascqktipsVo.getSha01Id());
                if (id != null && id.length() > 0) {//修改
                    sha01dascqktips = this.sha01dascqktipsService.getByPK(id);
                } else {//新增
                    sha01dascqktips = new Sha01dascqktips();
                }
                sha01dascqktips.setSha01dascqk(sha01.getDascqks().get(0));
                UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
                BeanUtils.copyProperties(sha01dascqktips, sha01dascqktipsVo);

                if (id != null && id.length() > 0) {
                    BeanTrans.setBaseProperties(sha01dascqktips, userLoginDetails, "update");
                    this.sha01dascqktipsService.update(sha01dascqktips);
                } else {
                    BeanTrans.setBaseProperties(sha01dascqktips, userLoginDetails, "save");
                    this.sha01dascqktipsService.save(sha01dascqktips);
                }


            }
            map.put("success", true);
            map.put("tip", sha01dascqktipsVo.getTip());
        } catch (Exception e) {
            map.put("success", false);
            map.put("message", "保存出错");
        }
        return map;
    }
}
