/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a32.controller;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.log.LogOperateType;
import com.hisun.saas.sys.log.RequiresLog;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a32.Constants;
import com.hisun.saas.zzb.dzda.a32.entity.A32;
import com.hisun.saas.zzb.dzda.a32.exchange.GzbdExcelExchange;
import com.hisun.saas.zzb.dzda.a32.service.A32Service;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.a32.entity.A32;
import com.hisun.saas.zzb.dzda.a32.service.A32Service;
import com.hisun.saas.zzb.dzda.a32.vo.A32Vo;
import com.hisun.util.UUIDUtil;
import com.hisun.util.WebUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Marco {854476391@qq.com}
 */
@Controller
@RequestMapping("/zzb/dzda/a32")
public class A32Controller extends BaseController {
    @Resource
    private A32Service a32Service;
    @Resource
    private A38Service a38Service;
    @Resource
    GzbdExcelExchange gzbdExcelExchange;

    @Value("${sys.upload.absolute.path}")
    private String uploadBasePath;

    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/ajax/list")
    public ModelAndView list(@RequestParam(value="pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                             @RequestParam(value = "a38Id",required = true) String a38Id) throws UnsupportedEncodingException {
        Map<String,Object> model = new HashMap<String,Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" a38_id = :a38Id ", "a38Id", a38Id));
        Long total = a32Service.count(query);
        //CommonOrderBy orderBy = new CommonOrderBy();
        //orderBy.add(CommonOrder.asc("px"));
        List<A32> resultList = a32Service.list(query,null,pageNum,pageSize);
        PagerVo<A32> pager = new PagerVo<A32>(resultList, total.intValue(), pageNum, pageSize);
        model.put("pager",pager);
        model.put("a38Id",a38Id);
        return new ModelAndView("saas/zzb/dzda/a32/list",model);
    }

    @RequestMapping(value = "/ajax/addGzbd")
    public ModelAndView addGzbd(String a38Id){
        Map map = Maps.newHashMap();
        int sort = a32Service.getMaxSort(a38Id);
        map.put("a38Id",a38Id);
        map.put("sort",sort);
        return new ModelAndView("saas/zzb/dzda/a32/addGzbd",map);
    }

    @RequestMapping(value = "/ajax/editGzbd")
    public ModelAndView editGzbd(String a38Id,String id){
        Map map = Maps.newHashMap();
        A32 a32 = a32Service.getByPK(id);
        map.put("a38Id",a38Id);
        map.put("a32",a32);
        return new ModelAndView("saas/zzb/dzda/a32/editGzbd",map);
    }

    @RequiresLog(operateType = LogOperateType.UPDATE,description = "更新工资变动:${vo.gzbm}")
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/update")
    public @ResponseBody
    Map<String, Object> update(A32Vo vo){
        Map<String,Object> map = Maps.newHashMap();
        try {
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            A32 a32 = a32Service.getByPK(vo.getId());
            int newPx = vo.getPx();
            int oldPx = a32.getPx();
            if (oldPx != newPx) {
                this.a32Service.updatePx(oldPx, newPx,vo.getA38Id());
            }
            A38 a38 = this.a38Service.getByPK(vo.getA38Id());
            a32.setA38(a38);
            BeanUtils.copyProperties(a32,vo);
            EntityWrapper.wrapperUpdateBaseProperties(a32,details);
            a32Service.update(a32);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            map.put("success", false);
        }
        return map;
    }
    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加工资变动:${vo.gzbm}")
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/save")
    public @ResponseBody Map<String, Object> save(A32Vo vo){
        Map<String,Object> map = Maps.newHashMap();
        try {
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            int newPx = vo.getPx();
            int oldPx=0;
            Integer oldPxInteger=a32Service.getMaxSort(vo.getA38Id());
            if(oldPxInteger != null){
                oldPx = oldPxInteger.intValue();
            }else{
                oldPx = 1;
            }
            if (oldPx != newPx) {
                this.a32Service.updatePx(oldPx, newPx,vo.getA38Id());
            }
            A32 a32 = new A32();
            A38 a38 = this.a38Service.getByPK(vo.getA38Id());
            a32.setA38(a38);
            BeanUtils.copyProperties(a32,vo);
            EntityWrapper.wrapperSaveBaseProperties(a32,details);
            a32Service.save(a32);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            map.put("success", false);
        }
        return map;
    }
    @RequiresPermissions("a38:*")
    @RequestMapping("/delete/{id}")
    public @ResponseBody Map<String,Object> delete(@PathVariable("id") String id) throws GenericException {
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            a32Service.deleteByPK(id);
            returnMap.put("code",1);
        }catch (Exception e){
            logger.error(e,e);
            throw new GenericException(e.getMessage());
        }

        return returnMap;
    }

    @RequiresPermissions("a38:*")
    @RequestMapping("/download/{a38Id}")
    public void download(@PathVariable("a38Id") String a38Id, HttpServletResponse resp){
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" a38_id = :a38Id ", "a38Id", a38Id));
        //CommonOrderBy orderBy = new CommonOrderBy();
        //orderBy.add(CommonOrder.asc("px"));
        List<A32> resultList = a32Service.list(query,null);
        A32Vo vo;
        List<A32Vo> a32Vos=new ArrayList<>();
        try {
            for(A32 a32:resultList){
                vo = new A32Vo();
                BeanUtils.copyProperties(vo,a32);
                a32Vos.add(vo);
            }
            File storePathFile = new File(Constants.GZBD_STORE_PATH);
            if(!storePathFile.exists()) storePathFile.mkdirs();
            String fielePath = uploadBasePath+Constants.GZBD_STORE_PATH+ UUIDUtil.getUUID()+".xlsx";
            gzbdExcelExchange.toOneExcelByManyPojo(a32Vos, uploadBasePath+Constants.GZBDMB_STORE_PATH,fielePath);
            resp.setContentType("multipart/form-data");
            resp.setHeader("Content-Disposition", "attachment;fileName="+encode("AA.xlsx"));
            OutputStream output = resp.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(new File(fielePath));
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String encode(String filename) throws UnsupportedEncodingException {
        if (WebUtil.getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            filename = URLEncoder.encode(filename, "UTF-8");
        } else {
            filename = new String(filename.getBytes("UTF-8"), "GBK");
        }
        return filename;
    }
}
