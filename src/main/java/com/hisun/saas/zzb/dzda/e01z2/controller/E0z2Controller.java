/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.e01z2.controller;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.entity.TombstoneEntity;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.sys.admin.dictionary.entity.DictionaryItem;
import com.hisun.saas.sys.admin.dictionary.service.DictionaryItemService;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.log.LogOperateType;
import com.hisun.saas.sys.log.RequiresLog;
import com.hisun.saas.sys.tenant.tenant.entity.Tenant2ResourcePrivilege;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a38.controller.A38Controller;
import com.hisun.saas.zzb.dzda.a38.vo.A38ExcelVo;
import com.hisun.saas.zzb.dzda.a38.vo.WrongExcelColumn;
import com.hisun.saas.zzb.dzda.e01z2.Constants;
import com.hisun.saas.zzb.dzda.e01z2.exchange.CljsExcelExchange;
import com.hisun.saas.zzb.dzda.e01z2.entity.E01Z2;
import com.hisun.saas.zzb.dzda.e01z2.service.E01z2Service;
import com.hisun.saas.zzb.dzda.e01z2.vo.E01z2Vo;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.util.DaUtils;
import com.hisun.util.StringUtils;
import com.hisun.util.URLEncoderUtil;
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
@RequestMapping("/zzb/dzda/e01z2")
public class E0z2Controller extends BaseController {
    @Resource
    private E01z2Service e01z2Service;
    @Resource
    private A38Service a38Service;
    @Resource
    private DictionaryItemService dictionaryItemService;

    @Resource
    CljsExcelExchange cljsExcelExchange;

    @Value("${sys.upload.absolute.path}")
    private String uploadBasePath;

    List<WrongExcelColumn> wrongExcelColumns;

    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/ajax/list")
    public ModelAndView list(@RequestParam(value="pageNum",defaultValue = "1")int pageNum, @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                             @RequestParam(value = "a38Id",required = true) String a38Id) throws UnsupportedEncodingException {
        Map<String,Object> model = new HashMap<String,Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" a38_id = :a38Id ", "a38Id", a38Id));
        Long total = e01z2Service.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.asc("e01z214"));
        List<E01Z2> resultList = e01z2Service.list(query,orderBy,pageNum,pageSize);
        PagerVo<E01Z2> pager = new PagerVo<E01Z2>(resultList, total.intValue(), pageNum, pageSize);
        model.put("pager",pager);
        model.put("a38Id",a38Id);
        return new ModelAndView("saas/zzb/dzda/e01z2/list",model);
    }

    @RequestMapping(value = "/ajax/addCljs")
    public ModelAndView addCljs(String a38Id){
        Map map = Maps.newHashMap();
        int sort = e01z2Service.getMaxSort(a38Id);
        map.put("a38Id",a38Id);
        map.put("sort",sort);
        return new ModelAndView("saas/zzb/dzda/e01z2/addCljs",map);
    }

    @RequestMapping(value = "/ajax/editCljs")
    public ModelAndView editcljs(String a38Id,String id){
        Map map = Maps.newHashMap();
        E01Z2 e01z2 = e01z2Service.getByPK(id);
        map.put("a38Id",a38Id);
        map.put("e01z2",e01z2);
        return new ModelAndView("saas/zzb/dzda/e01z2/editCljs",map);
    }

    @RequiresLog(operateType = LogOperateType.UPDATE,description = "更新材料接收:${vo.e01Z204A}")
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/update")
    public @ResponseBody
    Map<String, Object> update(E01z2Vo vo){
        Map<String,Object> map = Maps.newHashMap();
        try {
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            E01Z2 e01z2 = e01z2Service.getByPK(vo.getId());
            int newPx = vo.getE01Z214();
            int oldPx = e01z2.getE01Z214();
            if (oldPx != newPx) {
                this.e01z2Service.updatePx(oldPx, newPx,vo.getA38Id());
            }
            A38 a38 = this.a38Service.getByPK(vo.getA38Id());
            e01z2.setA38(a38);
            BeanUtils.copyProperties(e01z2,vo);
            EntityWrapper.wrapperUpdateBaseProperties(e01z2,details);
            e01z2Service.update(e01z2);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            map.put("success", false);
        }
        return map;
    }
    @RequiresLog(operateType = LogOperateType.SAVE,description = "增加材料接收:${vo.e01Z204A}")
    @RequiresPermissions("a38:*")
    @RequestMapping(value = "/save")
    public @ResponseBody Map<String, Object> save(E01z2Vo vo){
        Map<String,Object> map = Maps.newHashMap();
        try {
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            int newPx = vo.getE01Z214();
            int oldPx=0;
            Integer oldPxInteger=e01z2Service.getMaxSort(vo.getA38Id());
            if(oldPxInteger != null){
                oldPx = oldPxInteger.intValue();
            }else{
                oldPx = 1;
            }
            if (oldPx != newPx) {
                this.e01z2Service.updatePx(oldPx, newPx,vo.getA38Id());
            }
            E01Z2 e01z2 = new E01Z2();
            A38 a38 = this.a38Service.getByPK(vo.getA38Id());
            e01z2.setA38(a38);
            BeanUtils.copyProperties(e01z2,vo);
            EntityWrapper.wrapperSaveBaseProperties(e01z2,details);
            e01z2Service.save(e01z2);
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
            e01z2Service.deleteByPK(id);
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
        List<E01Z2> resultList = e01z2Service.list(query,null);
        E01z2Vo vo;
        List<E01z2Vo> e01z2Vos=new ArrayList<>();
        String filePath="";
        try {
            for(E01Z2 e01z2:resultList){
                vo = new E01z2Vo();
                BeanUtils.copyProperties(vo,e01z2);
                e01z2Vos.add(vo);
            }
            File storePathFile = new File(Constants.CLJS_STORE_PATH);
            if(!storePathFile.exists()){
                storePathFile.mkdirs();
            }

            filePath = uploadBasePath+Constants.CLJS_STORE_PATH+ UUIDUtil.getUUID()+".xlsx";
            cljsExcelExchange.toExcelByManyPojo(e01z2Vos, uploadBasePath+Constants.CLJSMB_STORE_PATH,filePath);
            resp.setContentType("multipart/form-data");
            resp.setHeader("Content-Disposition", "attachment;fileName="+ URLEncoderUtil.encode("材料接收表.xlsx"));
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
    public @ResponseBody Map<String,Object> uploadFile (String a38Id , @RequestParam(value="cljsFile",required = false) MultipartFile cljsFile , HttpServletResponse resp) throws IOException {
        Map<String,Object> map = new HashMap<>();
        boolean isRight = false;
        String filePath = "";
        File storePathFile = new File(Constants.CLJS_STORE_PATH);
        if(!storePathFile.exists()) storePathFile.mkdirs();
        filePath = uploadBasePath+Constants.CLJS_STORE_PATH+ UUIDUtil.getUUID()+".xlsx";
        File file = new File(filePath);
        InputStream inputStream = null;
        OutputStream output = null;
        try {
            inputStream = cljsFile.getInputStream();
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
        String tempFile = uploadBasePath+Constants.CLJSMB_STORE_PATH;
        List<Object> e01z2Vos=new ArrayList<>();
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        List<WrongExcelColumn> wrongExcelColumns = new ArrayList<>();
        try {
            e01z2Vos = cljsExcelExchange.fromExcel2ManyPojoWithLines(E01z2Vo.class,tempFile,filePath);
            boolean flag = false;//判断是否存在非法数据
            boolean flag1 = false;//判断是否存在非法数据
            WrongExcelColumn wrongExcelColumn;
            if(e01z2Vos.size()>0){
                for(int i=0;i<e01z2Vos.size();i++) {
                    flag = false;//判断是否存在非法数据
                    flag1 = false;//判断是否存在非法数据
                    int sum = 0;
                    E01z2Vo e01z2Vo = (E01z2Vo) e01z2Vos.get(i);
                    if (StringUtils.isEmpty(e01z2Vo.getE01Z204A())) {
                        wrongExcelColumn = new WrongExcelColumn();
                        wrongExcelColumn.setLines("A" + e01z2Vo.getRow());
                        wrongExcelColumn.setReason("来件单位不能为空");
                        wrongExcelColumn.setWrongExcel("材料接收表");
                        wrongExcelColumns.add(wrongExcelColumn);
                        flag = true;
                        sum++;
                    }
                    if (StringUtils.isEmpty(e01z2Vo.getE01Z221A())) {
                        wrongExcelColumn = new WrongExcelColumn();
                        wrongExcelColumn.setLines("E" + e01z2Vo.getRow());
                        wrongExcelColumn.setReason("材料名称不能为空");
                        wrongExcelColumn.setWrongExcel("材料接收表");
                        wrongExcelColumns.add(wrongExcelColumn);
                        flag = true;
                        sum++;
                    }
                    if (DaUtils.isNotDate(e01z2Vo.getE01Z201())) {
                        wrongExcelColumn = new WrongExcelColumn();
                        wrongExcelColumn.setLines("B" + e01z2Vo.getRow());
                        wrongExcelColumn.setReason("收件日期格式错误");
                        wrongExcelColumn.setWrongExcel("材料接收表");
                        wrongExcelColumns.add(wrongExcelColumn);
                        flag = true;
                        sum++;
                    }
                    if (DaUtils.isNotDate(e01z2Vo.getE01Z227())) {
                        wrongExcelColumn = new WrongExcelColumn();
                        wrongExcelColumn.setLines("G" + e01z2Vo.getRow());
                        wrongExcelColumn.setReason("材料制成日期格式错误");
                        wrongExcelColumn.setWrongExcel("材料接收表");
                        wrongExcelColumns.add(wrongExcelColumn);
                        flag = true;
                        sum++;
                    }

                    if (StringUtils.isEmpty(e01z2Vo.getE01Z204A()) && StringUtils.isEmpty(e01z2Vo.getE01Z221A())
                            && StringUtils.isEmpty(e01z2Vo.getE01Z201()) && StringUtils.isEmpty(e01z2Vo.getE01Z227())) {
                        flag1 = true;
                    }

                    if (flag) {
                        if (flag1) {
                            for (int j = 0; j < sum; j++) {
                                wrongExcelColumns.remove(wrongExcelColumns.size() - 1);
                            }
                        }
                        isRight = true;
                        continue;
                    }
                }
                if(isRight){
                    for(int i=0;i<e01z2Vos.size();i++){
                        Integer oldPxInteger = e01z2Service.getMaxSort(a38Id);
                        E01Z2 e01z2 = new E01Z2();
                        E01z2Vo e01z2Vo = (E01z2Vo) e01z2Vos.get(i);
                        String e01Z237Content = e01z2Vo.getE01Z237Content();
                        e01z2Vo.setE01Z237(getDictionaryItem(e01Z237Content,"CLCLBS-2018"));
                        String e01Z244Content = e01z2Vo.getE01Z244Content();
                        e01z2Vo.setE01Z244(getDictionaryItem(e01Z244Content,"SFBS-2018"));

                        BeanUtils.copyProperties(e01z2,e01z2Vo);
                        A38 a38 = this.a38Service.getByPK(a38Id);
                        e01z2.setA38(a38);
                        e01z2.setE01Z214(oldPxInteger);
                        EntityWrapper.wrapperSaveBaseProperties(e01z2,details);
                        e01z2Service.save(e01z2);
                    }
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

    /**
     * 反向查询获取字典项
     * @param name
     * @return
     */
    public String getDictionaryItem(String name,String Code){
        List<DictionaryItem> dictionaryItems;
        if(StringUtils.isEmpty(name)){
            return "";
        }
        CommonConditionQuery query = new CommonConditionQuery();
        query.add(CommonRestrictions.and(" dictionaryType.code=:typeCode ", "typeCode", Code));
        query.add(CommonRestrictions.and(" tombstone=:tombstone ", "tombstone", TombstoneEntity.TOMBSTONE_FALSE));
        query.add(CommonRestrictions.and(" display=:display ", "display", DictionaryItem.DISPLAY));
        CommonOrderBy orderBy = new CommonOrderBy();
        orderBy.add(CommonOrder.asc("sort"));
        dictionaryItems = dictionaryItemService.list(query, orderBy);
        for(DictionaryItem dictionaryItem:dictionaryItems){
            if(name.equals(dictionaryItem.getName())){
                return dictionaryItem.getCode();
            }
        }
        return "";
    }

    @RequestMapping(value = "/ajax/cwjl")
    public ModelAndView loadGjcx(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("datas",this.wrongExcelColumns);
        return new ModelAndView("saas/zzb/dzda/a32/wrongList",map);
    }
}
