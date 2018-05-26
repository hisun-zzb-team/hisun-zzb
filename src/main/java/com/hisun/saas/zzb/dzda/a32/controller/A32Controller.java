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
import com.hisun.util.StringUtils;
import com.hisun.util.URLEncoderUtil;
import com.hisun.util.UUIDUtil;
import com.hisun.util.WebUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.asc("px"));
        List<A32> resultList = a32Service.list(query,orderBy,pageNum,pageSize);
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
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.asc("px"));
        List<A32> resultList = a32Service.list(query,orderBy);
        A32Vo vo;
        List<A32Vo> a32Vos=new ArrayList<>();
        String filePath = "";
        try {
            for(A32 a32:resultList){
                vo = new A32Vo();
                BeanUtils.copyProperties(vo,a32);
                a32Vos.add(vo);
            }
            File storePathFile = new File(Constants.GZBD_STORE_PATH);
            if(!storePathFile.exists()) storePathFile.mkdirs();
            filePath = uploadBasePath+Constants.GZBD_STORE_PATH+ UUIDUtil.getUUID()+".xlsx";
            gzbdExcelExchange.toExcelByManyPojo(a32Vos, uploadBasePath+Constants.GZBDMB_STORE_PATH,filePath);
            resp.setContentType("multipart/form-data");
            resp.setHeader("Content-Disposition", "attachment;fileName="+ URLEncoderUtil.encode("gzbd.xlsx"));
            OutputStream output = resp.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(new File(filePath));
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            output.flush();
            output.close();
            fileInputStream.close();
            FileUtils.deleteQuietly(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
        }finally {
            File file = new File(filePath);
            if(file.exists()){
                file.delete();
            }
        }
    }

    @RequiresPermissions("a38:*")
    @RequestMapping("/uploadFile")
    public void uploadFile (String a38Id ,@RequestParam(value="gzbdFile",required = false) MultipartFile gzbdFile , HttpServletResponse resp) throws IOException {
        String filePath = "";
        File storePathFile = new File(Constants.GZBD_STORE_PATH);
        if(!storePathFile.exists()) storePathFile.mkdirs();
        filePath = uploadBasePath+Constants.GZBD_STORE_PATH+ UUIDUtil.getUUID()+".xlsx";
        File file = new File(filePath);
        InputStream inputStream = null;
        OutputStream output = null;
        try {
            inputStream = gzbdFile.getInputStream();
            output = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null){
                inputStream.close();
            }
            if(output!=null){
                output.close();
            }
        }
        String tempFile = uploadBasePath+Constants.GZBDMB_STORE_PATH;
        List<Object> a32Vos=new ArrayList<>();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        try {
            a32Vos = gzbdExcelExchange.fromExcel2ManyPojo(A32Vo.class,tempFile,filePath);
            Integer oldPxInteger=a32Service.getMaxSort(a38Id);
            boolean flag = false;//判断是否存在非法数据
            if(a32Vos.size()>0){
                for(int i=0;i<a32Vos.size();i++){
                    A32 a32 = new A32();
                    A32Vo a32Vo = (A32Vo) a32Vos.get(i);

                    if(StringUtils.isEmpty(a32Vo.getGzbm())){
                        flag = true;
                    }
                    if(isNotDate(a32Vo.getA3207())){
                        flag = true;
                    }

                    if(flag){
                        continue;
                    }

                    BeanUtils.copyProperties(a32,a32Vo);
                    A38 a38 = this.a38Service.getByPK(a38Id);
                    a32.setA38(a38);
                    a32.setPx(oldPxInteger+i);
                    EntityWrapper.wrapperSaveBaseProperties(a32,details);
                    a32Service.save(a32);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            file.delete();
        }
    }

    public boolean isNotDate(String dateStr){
        if(StringUtils.isNotEmpty(dateStr)) {
            int lengh = dateStr.length();
            if (lengh == 4 || lengh == 6 || lengh == 8) {
                if (StringUtils.isNumeric(dateStr)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
