/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a52.controller;

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
import com.hisun.saas.zzb.dzda.a38.controller.A38Controller;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.a38.vo.A38Vo;
import com.hisun.saas.zzb.dzda.a38.vo.WrongExcelColumn;
import com.hisun.saas.zzb.dzda.a52.entity.A52;
import com.hisun.saas.zzb.dzda.a52.exchange.ZwbdExcelExchange;
import com.hisun.saas.zzb.dzda.a52.service.A52Service;
import com.hisun.saas.zzb.dzda.a52.vo.A52Vo;
import com.hisun.saas.zzb.dzda.a52.Constants;
import com.hisun.util.StringUtils;
import com.hisun.util.URLEncoderUtil;
import com.hisun.util.UUIDUtil;
import com.hisun.util.WebUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * @author Marco {854476391@qq.com}
 */
@Controller
@RequestMapping("/zzb/dzda/a52")
public class A52Controller extends BaseController {
    @Resource
    private A52Service a52Service;
    @Resource
    private A38Service a38Service;

    @Resource
    ZwbdExcelExchange zwbdExcelExchange;

    List<WrongExcelColumn> wrongExcelColumns;

    @Value("${sys.upload.absolute.path}")
    private String uploadBasePath;

    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/ajax/list")
    public ModelAndView list(@RequestParam(value="pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
        @RequestParam(value = "a38Id",required = true) String a38Id) throws UnsupportedEncodingException {
        Map<String,Object> model = new HashMap<String,Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" a38_id = :a38Id ", "a38Id", a38Id));
        Long total = a52Service.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.asc("px"));
        List<A52> resultList = a52Service.list(query,orderBy,pageNum,pageSize);
        PagerVo<A52> pager = new PagerVo<A52>(resultList, total.intValue(), pageNum, pageSize);
        model.put("pager",pager);
        model.put("a38Id",a38Id);
        return new ModelAndView("saas/zzb/dzda/a52/list",model);
    }

    @RequestMapping(value = "/ajax/addZwbd")
    public ModelAndView addZwbd(String a38Id){
        Map map = Maps.newHashMap();
        int sort = a52Service.getMaxSort(a38Id);
        map.put("a38Id",a38Id);
        map.put("sort",sort);
        return new ModelAndView("saas/zzb/dzda/a52/addZwbd",map);
    }

    @RequestMapping(value = "/ajax/editZwbd")
    public ModelAndView editZwbd(String a38Id,String id){
        Map map = Maps.newHashMap();
        A52 a52 = a52Service.getByPK(id);
        map.put("a38Id",a38Id);
        map.put("a52",a52);
        return new ModelAndView("saas/zzb/dzda/a52/editZwbd",map);
    }

    @RequiresLog(operateType = LogOperateType.UPDATE,description = "更新职务变动:${vo.a5204}")
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/update")
    public @ResponseBody Map<String, Object> update(A52Vo vo){
        Map<String,Object> map = Maps.newHashMap();
        try {
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            A52 a52 = a52Service.getByPK(vo.getId());
            int newPx = vo.getPx();
            int oldPx = a52.getPx();
            if (oldPx != newPx) {
                this.a52Service.updatePx(oldPx, newPx,vo.getA38Id());
            }
            A38 a38 = this.a38Service.getByPK(vo.getA38Id());
            a52.setA38(a38);
            BeanUtils.copyProperties(a52,vo);
            EntityWrapper.wrapperUpdateBaseProperties(a52,details);
            a52Service.update(a52);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            map.put("success", false);
        }
        return map;
    }
    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加职务变动:${vo.a5204}")
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/save")
    public @ResponseBody Map<String, Object> save(A52Vo vo){
        Map<String,Object> map = Maps.newHashMap();
        try {
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            int newPx = vo.getPx();
            int oldPx=0;
            Integer oldPxInteger=a52Service.getMaxSort(vo.getA38Id());
            if(oldPxInteger != null){
                oldPx = oldPxInteger.intValue();
            }else{
                oldPx = 1;
            }
            if (oldPx != newPx) {
                this.a52Service.updatePx(oldPx, newPx,vo.getA38Id());
            }
            A52 a52 = new A52();
            A38 a38 = this.a38Service.getByPK(vo.getA38Id());
            a52.setA38(a38);
            BeanUtils.copyProperties(a52,vo);
            EntityWrapper.wrapperSaveBaseProperties(a52,details);
            a52Service.save(a52);
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
            a52Service.deleteByPK(id);
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
        List<A52> resultList = a52Service.list(query,null);
        A52Vo vo;
        A38Vo a38Vo = new A38Vo();
        List<A52Vo> a52Vos=new ArrayList<>();
        String filePath="";
        try {

            for(A52 a52:resultList){
                vo = new A52Vo();
                BeanUtils.copyProperties(vo,a52);
                a52Vos.add(vo);
            }
            if(resultList!=null && resultList.size()>0){
                A52 a52=resultList.get(0);
                a38Vo.setA0101(a52.getA38().getA0101());
                a38Vo.setA0104Content(a52.getA38().getA0104Content());
                a38Vo.setA0107(a52.getA38().getA0107());
                a38Vo.setA0111A(a52.getA38().getA0111A());
                a38Vo.setA52Vos(a52Vos);
            }

            File storePathFile = new File(Constants.ZWBD_STORE_PATH);
            if(!storePathFile.exists()){
                storePathFile.mkdirs();
            }

            filePath = uploadBasePath+Constants.ZWBD_STORE_PATH+ UUIDUtil.getUUID()+".xlsx";
            zwbdExcelExchange.toExcel(a38Vo, uploadBasePath+Constants.ZWBDMB_STORE_PATH,filePath);
            resp.setContentType("multipart/form-data");
            resp.setHeader("Content-Disposition", "attachment;fileName="+ URLEncoderUtil.encode("干部职务变动登记表.xlsx"));
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
    public @ResponseBody Map<String,Object> uploadFile (String a38Id , @RequestParam(value="zwbdFile",required = false) MultipartFile zwbdFile , HttpServletResponse resp) throws IOException {
        Map<String,Object> map = new HashMap<>();
        boolean isRight = false;
        List<WrongExcelColumn> wrongExcelColumns = new ArrayList<>();
        String filePath = "";
        File storePathFile = new File(Constants.ZWBD_STORE_PATH);
        if(!storePathFile.exists()) storePathFile.mkdirs();
        filePath = uploadBasePath+Constants.ZWBD_STORE_PATH+ UUIDUtil.getUUID()+".xlsx";
        File file = new File(filePath);
        InputStream inputStream = null;
        OutputStream output = null;
        try {
            inputStream = zwbdFile.getInputStream();
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
        String tempFile = uploadBasePath+Constants.ZWBDMB_STORE_PATH;
        A38Vo a38Vo = new A38Vo();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        try {
            a38Vo = (A38Vo) zwbdExcelExchange.fromExcelWithLines(A38Vo.class,tempFile,filePath);
            WrongExcelColumn wrongExcelColumn;
            if(a38Vo!=null&&a38Vo.getA52Vos().size()>0){
                List<A52Vo> a52Vos = a38Vo.getA52Vos();
                for(int i=0;i<a52Vos.size();i++){
                    int sum = 0;
                    Integer oldPxInteger=a52Service.getMaxSort(a38Id);
                    boolean flag = false;//判断是否存在非法数据
                    boolean flag1 = false;//判断必填数据是否全为空
                    A52 a52 = new A52();
                    A52Vo a52Vo = a52Vos.get(i);
                    if(StringUtils.isEmpty(a52Vo.getA5204())){
                        wrongExcelColumn = new WrongExcelColumn();
                        wrongExcelColumn.setLines("C"+a52Vo.getRow());
                        wrongExcelColumn.setReason("部门名称不能为空");
                        wrongExcelColumn.setWrongExcel("职务变动登记表");
                        wrongExcelColumns.add(wrongExcelColumn);
                        flag = true;
                        sum++;

                    }
                    if(A38Controller.isNotDate(a52Vo.getA5227In())){
                        wrongExcelColumn = new WrongExcelColumn();
                        wrongExcelColumn.setLines("A"+a52Vo.getRow());
                        wrongExcelColumn.setReason("任职时间格式错误");
                        wrongExcelColumn.setWrongExcel("职务变动登记表");
                        wrongExcelColumns.add(wrongExcelColumn);
                        flag = true;
                        sum++;
                    }
                    if(A38Controller.isNotDate(a52Vo.getA5227Out())){
                        wrongExcelColumn = new WrongExcelColumn();
                        wrongExcelColumn.setLines("B"+a52Vo.getRow());
                        wrongExcelColumn.setReason("免职时间格式错误");
                        wrongExcelColumn.setWrongExcel("职务变动登记表");
                        wrongExcelColumns.add(wrongExcelColumn);
                        flag = true;
                        sum++;
                    }
                    if(StringUtils.isEmpty(a52Vo.getA5204())&&StringUtils.isEmpty(a52Vo.getA5227In())&&StringUtils.isEmpty(a52Vo.getA5227Out())){
                        flag1 = true;
                    }

                    if(flag){
                        if(flag1){
                            for(int j=0;j<sum;j++){
                                wrongExcelColumns.remove(wrongExcelColumns.size()-1);
                            }
                        }
                        isRight = true;
                        continue;
                    }

                    BeanUtils.copyProperties(a52,a52Vo);
                    A38 a38 = this.a38Service.getByPK(a38Id);
                    a52.setA38(a38);
                    a52.setPx(oldPxInteger);
                    EntityWrapper.wrapperSaveBaseProperties(a52,details);
//                    a52Service.save(a52);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            file.delete();
        }
        map.put("success",true);
        if(isRight){
            this.wrongExcelColumns = wrongExcelColumns;
            map.put("isWrong",true);
        }else {
            map.put("isWrong",false);
        }
        return map;
    }

    @RequestMapping(value = "/ajax/cwjl")
    public ModelAndView loadGjcx(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("datas",this.wrongExcelColumns);
        return new ModelAndView("saas/zzb/dzda/a32/a32WrongList",map);
    }

}
