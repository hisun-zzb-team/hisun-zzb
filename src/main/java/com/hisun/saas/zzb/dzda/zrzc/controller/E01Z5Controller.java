/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.zrzc.controller;

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
import com.hisun.saas.sys.admin.dzda.entity.ECatalogTypeInfo;
import com.hisun.saas.sys.admin.dzda.service.ECatalogTypeService;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a32.entity.A32;
import com.hisun.saas.zzb.dzda.a32.service.A32Service;
import com.hisun.saas.zzb.dzda.a32.service.impl.A32ServiceImpl;
import com.hisun.saas.zzb.dzda.a32.vo.A32Vo;
import com.hisun.saas.zzb.dzda.a38.controller.A38Controller;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.exchange.A38ExcelExchange;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.a38.service.impl.A38ServiceImpl;
import com.hisun.saas.zzb.dzda.a38.vo.A38ExcelVo;
import com.hisun.saas.zzb.dzda.a38.vo.A38Vo;
import com.hisun.saas.zzb.dzda.a38.vo.WrongExcelColumn;
import com.hisun.saas.zzb.dzda.a52.entity.A52;
import com.hisun.saas.zzb.dzda.a52.service.A52Service;
import com.hisun.saas.zzb.dzda.a52.service.impl.A52ServiceImpl;
import com.hisun.saas.zzb.dzda.a52.vo.A52Vo;
import com.hisun.saas.zzb.dzda.e01z2.entity.E01Z2;
import com.hisun.saas.zzb.dzda.e01z2.service.E01z2Service;
import com.hisun.saas.zzb.dzda.e01z2.service.impl.E01z2ServiceImpl;
import com.hisun.saas.zzb.dzda.e01z2.vo.E01z2Vo;
import com.hisun.saas.zzb.dzda.mlcl.entity.E01Z1;
import com.hisun.saas.zzb.dzda.mlcl.service.E01Z1Service;
import com.hisun.saas.zzb.dzda.mlcl.vo.E01Z1ExcelVo;
import com.hisun.saas.zzb.dzda.mlcl.vo.E01Z1Vo;
import com.hisun.saas.zzb.dzda.util.DaUtils;
import com.hisun.saas.zzb.dzda.zrzc.Constants;
import com.hisun.saas.zzb.dzda.zrzc.entity.E01Z5;
import com.hisun.saas.zzb.dzda.zrzc.exchange.DacdExcelExchange;
import com.hisun.saas.zzb.dzda.zrzc.service.E01Z5Service;
import com.hisun.saas.zzb.dzda.zrzc.vo.E01Z5ResVo;
import com.hisun.saas.zzb.dzda.zrzc.vo.E01Z5Vo;
import com.hisun.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.io.FileUtils;
import com.hisun.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author liuzj {279421824@qq.com}
 */
@Controller
@RequestMapping("/zzb/dzda/dajs")
public class E01Z5Controller extends BaseController {

    @Resource
    private E01Z5Service e01z5Service;

    @Value("${sys.upload.absolute.path}")
    private String uploadAbsolutePath;

    @Resource
    private A38Service a38Service;

    @Resource
    private E01Z1Service e01Z1Service;

    List<WrongExcelColumn> wrongExcelColumns;

    @Resource
    private A32Service a32Service;

    @Resource
    private A52Service a52Service;

    @Resource
    private E01z2Service e01z2Service;

    @Resource
    private ECatalogTypeService eCatalogTypeService;

    @Resource
    private DictionaryItemService dictionaryItemService;

    @Resource
    DacdExcelExchange dacdExcelExchange;

    @Resource
    A38ExcelExchange a38ExcelExchange;

    @Value("${sys.upload.absolute.path}")
    private String uploadBasePath;

    @RequiresPermissions("dajs:*")
    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value="pageNum",defaultValue = "1")int pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "10")int pageSize,
                             @RequestParam(value="name",required = false)String name,
                             @RequestParam(value="e01Z507A",required = false)String e01Z507A,
                             @RequestParam(value="starTime",required = false)String starTime,
                             @RequestParam(value="endTime",required = false)String endTime,
                             @RequestParam(value="e01Z517",required = false)String e01Z517,
                             @RequestParam(value="e01Z527",required = false)String e01Z527
    ) throws UnsupportedEncodingException, InvocationTargetException, IllegalAccessException {
        Map<String,Object> model = new HashMap<String,Object>();
        CommonConditionQuery query = new CommonConditionQuery();
        if(StringUtils.isNotBlank(name)){
            query.add(CommonRestrictions.and(" name = :name ", "name", name));
        }
        if(StringUtils.isNotBlank(e01Z507A)){
            query.add(CommonRestrictions.and(" e01Z507A = :e01Z507A ", "e01Z507A", e01Z507A));
        }
        if(StringUtils.isNotBlank(starTime)){
            query.add(CommonRestrictions.and(" e01Z501 >= :starTime ", "starTime", new DateTime(starTime).toDate()));
        }
        if(StringUtils.isNotBlank(endTime)){
            query.add(CommonRestrictions.and(" e01Z501 <= :endTime ", "endTime", new DateTime(endTime).toDate()));
        }
        if(StringUtils.isNotBlank(e01Z517)){
            query.add(CommonRestrictions.and(" e01Z517 = :e01Z517 ", "e01Z517", e01Z517));
        }
        if(StringUtils.isNotBlank(e01Z527)){
            query.add(CommonRestrictions.and(" e01Z527 = :e01Z527 ", "e01Z527", e01Z527));
        }

//        query.add(CommonRestrictions.and(" pId = :pId ", "pId", pId));
        Long total = e01z5Service.count(query);
        CommonOrderBy orderBy = new CommonOrderBy();
//        orderBy.add(CommonOrder.asc("sort"));
        List<E01Z5> resultList = e01z5Service.list(query,orderBy,pageNum,pageSize);
        List<E01Z5ResVo> e01z5Vos = new ArrayList<E01Z5ResVo>();
        E01Z5ResVo vo = new E01Z5ResVo();
        for(E01Z5 entity : resultList){
            vo = new E01Z5ResVo();
            BeanUtils.copyProperties(vo,entity);
            e01z5Vos.add(vo);
        }
        PagerVo<E01Z5ResVo> pager = new PagerVo<E01Z5ResVo>(e01z5Vos, total.intValue(), pageNum, pageSize);
        model.put("pager",pager);
        model.put("name",name);
        model.put("e01Z507A",e01Z507A);
        model.put("starTime",starTime);
        model.put("endTime",endTime);
        model.put("e01Z517",e01Z517);
        model.put("e01Z527",e01Z527);

        return new ModelAndView("saas/zzb/dzda/zrzc/e01z5/list",model);
    }

    @RequiresPermissions("dajs:*")
    @RequestMapping("/add")
    public ModelAndView add(){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        returnMap.put("","");
        return new ModelAndView("saas/zzb/dzda/zrzc/e01z5/add",returnMap);
    }

    // @RequiresPermissions("dajs:*")
    @RequestMapping("/save")
    public @ResponseBody Map<String,Object> save(E01Z5Vo vo,HttpServletRequest req,
                                                 @RequestParam(value = "clFile", required = false) MultipartFile clFile){
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Map<String,String> vMap = ValidateUtil.validAll(vo);
        if(vMap.size()>0){
            returnMap.put("message","数据验证不通过");
            returnMap.put("code",-1);
            return returnMap;
        }
        try{
            String fileName = "";
            String filePath = "";
            if (clFile != null && !clFile.isEmpty()) {
                File storePathFile = new File(Constants.DAJS_STORE_PATH);
                if(!storePathFile.exists()) storePathFile.mkdirs();
                filePath = uploadBasePath+ Constants.DAJS_STORE_PATH+ UUIDUtil.getUUID() +".xlsx";
                File file = new File(filePath);
                InputStream inputStream = null;
                OutputStream output = null;
                try {
                    inputStream = clFile.getInputStream();
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
            }
            Map<String,Object>  map = saveFile(filePath);
            if((boolean) map.get("isWrong")){
                returnMap.put("code",1);
                returnMap.putAll(map);
                return returnMap;
            }
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            E01Z5 entity = new  E01Z5();
            ConvertUtils.register(new DateConverter(null), java.util.Date.class);
            BeanUtils.copyProperties(entity, vo);
            entity.setE01Z501(com.hisun.util.StringUtils.isNotBlank(vo.getE01Z501())? DateUtil.parseDefaultDate(vo.getE01Z501()) :null );
            entity.setE01Z524(com.hisun.util.StringUtils.isNotBlank(vo.getE01Z524())? DateUtil.parseDefaultDate(vo.getE01Z524()) :null );
            entity.setE01Z531(com.hisun.util.StringUtils.isNotBlank(vo.getE01Z531())? DateUtil.parseDefaultDate(vo.getE01Z531()) :null );
            entity.setE01Z534(com.hisun.util.StringUtils.isNotBlank(vo.getE01Z534())? DateUtil.parseDefaultDate(vo.getE01Z534()) :null );
            entity.setA38(a38Service.getByPK((String) map.get("a38Id")));
            entity.setFileName((String) map.get("fileName"));
            entity.setFilePath(filePath);
            entity.setId(null);
            entity.setE01Z517(details.getRealname());
            EntityWrapper.wrapperSaveBaseProperties(entity,details);
            e01z5Service.save(entity);
            returnMap.put("code",1);
            returnMap.putAll(map);
        }catch (GenericException e){
            returnMap.put("code",-1);
            returnMap.put("message", e.getMessage());
        }catch (Exception e){
            logger.error(e,e);
            returnMap.put("code",-1);
            returnMap.put("message", "系统错误，请联系管理员");
        }

        return returnMap;
    }

    @RequiresPermissions("dajs:*")
    @RequestMapping("/ajax/edit/{id}")
    public ModelAndView edit(@PathVariable("id")String id){
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("hzrq",DateUtil.formatDateByFormat(e01z5Service.getByPK(id).getE01Z531(),"yyyyMMdd"));
        model.put("id", id);
        return new ModelAndView("saas/zzb/dzda/zrzc/e01z5/edit", model);
    }

    @RequiresPermissions("dajs:*")
    @RequestMapping("/view/{id}")
    public ModelAndView view(@PathVariable("id") String id){
        Map<String,Object> model = new HashMap<String,Object>();
        try{
            E01Z5 e01Z5 = e01z5Service.getByPK(id);
            E01Z5Vo vo = new E01Z5Vo();
            ConvertUtils.register(new DateConverter(null), java.util.Date.class);
            BeanUtils.copyProperties(vo,e01Z5);
            vo.setE01Z501(e01Z5.getE01Z501()==null?null:DateUtil.formatDefaultDate(e01Z5.getE01Z501()));
            vo.setE01Z524(e01Z5.getE01Z524()==null?null:DateUtil.formatDefaultDate(e01Z5.getE01Z524()));
            vo.setE01Z531(e01Z5.getE01Z531()==null?null:DateUtil.formatDefaultDate(e01Z5.getE01Z531()));
            vo.setE01Z534(e01Z5.getE01Z534()==null?null:DateUtil.formatDefaultDate(e01Z5.getE01Z534()));
            model.put("vo",vo);
        }catch (Exception e){
            logger.error(e);
        }

        return new ModelAndView("saas/zzb/dzda/zrzc/e01z5/view", model);
    }
    @RequiresPermissions("dajs:*")
    @RequestMapping("/updateHzrq")
    public @ResponseBody Map<String,Object> updateHzrq(@RequestParam(value = "id",required = true) String id,
                                                       @RequestParam(value = "hzrq",required = true) String hzrq){
        Map<String,Object> model = new HashMap<String,Object>();
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date date = sdf.parse(hzrq);
            E01Z5 e01Z5 = e01z5Service.getByPK(id);
            e01Z5.setE01Z531(sdf.parse(hzrq));
            e01z5Service.update(e01Z5);
            model.put("code","1");
        }catch (Exception e){
            model.put("code","0");
            logger.error(e);
        }
        return model;
    }


    @RequestMapping(value = "/down")
    @RequiresPermissions("cysq:*")
    public void templateDown(String id, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        E01Z5 entity = e01z5Service.getByPK(id);
        try {
            resp.setContentType("multipart/form-data");
            resp.setHeader("Content-Disposition", "attachment;fileName="+URLEncoderUtil.encode(entity.getFileName()+".xlsx"));
            OutputStream output = resp.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(new File(entity.getFilePath()));
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            output.flush();
            output.close();
            fileInputStream.close();
        }catch (Exception e) {
            logger.error(e, e);
        }
    }

    @RequiresPermissions("dajs:*")
    @RequestMapping("/update")
    public @ResponseBody Map<String,Object> update(@ModelAttribute E01Z5Vo vo, HttpServletRequest request) throws GenericException{
        Map<String,Object> returnMap = new HashMap<String,Object>();
        Map<String,String> vMap = ValidateUtil.validAll(vo);
        if(StringUtils.isBlank(vo.getName()) || vo.getName().length()<1 || vo.getName().length()>15){
            returnMap.put("message","数据验证不通过");
            returnMap.put("code",-1);
            return returnMap;
        }
        E01Z5 entity = e01z5Service.getByPK(vo.getId());
        if(entity==null){
            returnMap.put("message","数据不存在");
            returnMap.put("code",-1);
            return returnMap;
        }
        try{
            UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
            BeanUtils.copyProperties(vo, entity);
            EntityWrapper.wrapperUpdateBaseProperties(entity,details);
            e01z5Service.update(entity);
            returnMap.put("code",1);
        }catch (Exception e){
            logger.error(e,e);
            returnMap.put("code",-1);
        }
        return returnMap;
    }

    /**
     * 租户注销
     * @param id
     * @return
     */
    @RequiresPermissions("dajs:*")
    @RequestMapping("/delete/{id}")
    public @ResponseBody Map<String,Object> delete(@PathVariable("id") String id) throws GenericException{
        Map<String,Object> returnMap = new HashMap<String,Object>();
        try{
            e01z5Service.deleteByPK(id);
            returnMap.put("code",1);
        }catch (Exception e){
            logger.error(e,e);
            returnMap.put("code",-1);
        }

        return returnMap;
    }

    private Map<String,Object> saveFile(String filePath){
        List<WrongExcelColumn> wrongExcelColumns = new ArrayList<>();
        boolean isRight;
        Map<String,Object> returnMap = new HashMap<>();
        String tempFile = uploadBasePath+Constants.DAJSMB_STORE_PATH;
        A38ExcelVo a38ExcelVo;
        UserLoginDetails details = UserLoginDetailsUtil.getUserLoginDetails();
        try {
            a38ExcelVo = (A38ExcelVo) a38ExcelExchange.fromExcel(A38ExcelVo.class,tempFile,filePath);
            returnMap=checkA38ExcelData(a38ExcelVo,returnMap);
            isRight= (boolean) returnMap.get("isRight");
            if(!isRight){
                saveA38ExcelData(a38ExcelVo,details);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        isRight= (boolean) returnMap.get("isRight");
        wrongExcelColumns = (List<WrongExcelColumn>) returnMap.get("wrongExcelColumns");
        returnMap.put("success",true);
        if(isRight){
            this.wrongExcelColumns = wrongExcelColumns;
            returnMap.put("isWrong",true);
        }else {
            returnMap.put("isWrong",false);
        }
        return returnMap;
    }

    public Map<String,Object> checkA38ExcelData(A38ExcelVo a38ExcelVo,Map<String,Object> returnMap){
        boolean isRight = false;
        WrongExcelColumn wrongExcelColumn;
        List<WrongExcelColumn> wrongExcelColumns=new ArrayList<>();
        if(a38ExcelVo!=null){
            int sum = 0;
            boolean flag;//判断是否存在非法数据
            boolean flag1;//判断必填数据是否全为空
            //新增档案
            A38Vo jbxxA38Vo = a38ExcelVo.getJbxxA38Vo();

            if(StringUtils.isEmpty(jbxxA38Vo.getA0101())){
                wrongExcelColumn = new WrongExcelColumn();
                wrongExcelColumn.setLines("C"+jbxxA38Vo.getRow());
                wrongExcelColumn.setReason("姓名不能为空");
                wrongExcelColumn.setWrongExcel("基本信息");
                wrongExcelColumns.add(wrongExcelColumn);
                sum++;
            }

            if (DaUtils.isNotDate(jbxxA38Vo.getA0107())) {
                wrongExcelColumn = new WrongExcelColumn();
                wrongExcelColumn.setLines("F"+jbxxA38Vo.getRow());
                wrongExcelColumn.setReason("生日日期格式错误");
                wrongExcelColumn.setWrongExcel("基本信息");
                wrongExcelColumns.add(wrongExcelColumn);
                sum++;
            }

            //新增职务变动
            A38Vo a38VoForA52 = a38ExcelVo.getZwbdA38Vo();
            if(a38VoForA52!=null&&a38VoForA52.getA52Vos().size()>0){
                List<A52Vo> a52Vos = a38VoForA52.getA52Vos();
                for(int i=0;i<a52Vos.size();i++){
                    flag = false;//判断是否存在非法数据
                    flag1 = false;//判断必填数据是否全为空
                    sum=0;
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
                    if(DaUtils.isNotDate(a52Vo.getA5227In())){
                        wrongExcelColumn = new WrongExcelColumn();
                        wrongExcelColumn.setLines("A"+a52Vo.getRow());
                        wrongExcelColumn.setReason("任职时间格式错误");
                        wrongExcelColumn.setWrongExcel("职务变动登记表");
                        wrongExcelColumns.add(wrongExcelColumn);
                        flag = true;
                        sum++;
                    }
                    if(DaUtils.isNotDate(a52Vo.getA5227Out())){
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
                }
            }

            //添加工资变动记录
            List<A32Vo> gzbdList = a38ExcelVo.getGzbdList();
            if(gzbdList.size()>0){
                for(int i=0;i<gzbdList.size();i++){
                    flag = false;//判断是否存在非法数据
                    flag1 = false;//判断必填数据是否全为空
                    sum=0;
                    A32Vo a32Vo = gzbdList.get(i);

                    if(StringUtils.isEmpty(a32Vo.getGzbm())){
                        wrongExcelColumn = new WrongExcelColumn();
                        wrongExcelColumn.setLines("A"+a32Vo.getRow());
                        wrongExcelColumn.setReason("工作部门不能为空");
                        wrongExcelColumn.setWrongExcel("工资变动登记表");
                        wrongExcelColumns.add(wrongExcelColumn);
                        flag = true;
                        sum++;
                    }
                    if(DaUtils.isNotDate(a32Vo.getA3207())){
                        wrongExcelColumn = new WrongExcelColumn();
                        wrongExcelColumn.setLines("E"+a32Vo.getRow());
                        wrongExcelColumn.setReason("日期格式错误");
                        wrongExcelColumn.setWrongExcel("工资变动登记表");
                        wrongExcelColumns.add(wrongExcelColumn);
                        flag = true;
                        sum++;
                    }

                    if(StringUtils.isEmpty(a32Vo.getGzbm())){
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
                }
            }

            //添加材料接收记录
            List<E01z2Vo> e01z2Vos = a38ExcelVo.getE01z2Vos();
            if(e01z2Vos.size()>0){
                for(int i=0;i<e01z2Vos.size();i++){
                    flag = false;//判断是否存在非法数据
                    flag1 = false;//判断必填数据是否全为空
                    sum=0;
                    E01z2Vo e01z2Vo = e01z2Vos.get(i);

                    if(StringUtils.isEmpty(e01z2Vo.getE01Z204A())){
                        wrongExcelColumn = new WrongExcelColumn();
                        wrongExcelColumn.setLines("A"+e01z2Vo.getRow());
                        wrongExcelColumn.setReason("来件单位不能为空");
                        wrongExcelColumn.setWrongExcel("材料接收表");
                        wrongExcelColumns.add(wrongExcelColumn);
                        flag = true;
                        sum++;
                    }
                    if(StringUtils.isEmpty(e01z2Vo.getE01Z221A())){
                        wrongExcelColumn = new WrongExcelColumn();
                        wrongExcelColumn.setLines("E"+e01z2Vo.getRow());
                        wrongExcelColumn.setReason("材料名称不能为空");
                        wrongExcelColumn.setWrongExcel("材料接收表");
                        wrongExcelColumns.add(wrongExcelColumn);
                        flag = true;
                        sum++;
                    }
                    if(DaUtils.isNotDate(e01z2Vo.getE01Z201())){
                        wrongExcelColumn = new WrongExcelColumn();
                        wrongExcelColumn.setLines("B"+e01z2Vo.getRow());
                        wrongExcelColumn.setReason("收件日期格式错误");
                        wrongExcelColumn.setWrongExcel("材料接收表");
                        wrongExcelColumns.add(wrongExcelColumn);
                        flag = true;
                        sum++;
                    }
                    if(DaUtils.isNotDate(e01z2Vo.getE01Z227())){
                        wrongExcelColumn = new WrongExcelColumn();
                        wrongExcelColumn.setLines("G"+e01z2Vo.getRow());
                        wrongExcelColumn.setReason("材料制成日期格式错误");
                        wrongExcelColumn.setWrongExcel("材料接收表");
                        wrongExcelColumns.add(wrongExcelColumn);
                        flag = true;
                        sum++;
                    }

                    if(StringUtils.isEmpty(e01z2Vo.getE01Z204A())&&StringUtils.isEmpty(e01z2Vo.getE01Z221A())
                            &&StringUtils.isEmpty(e01z2Vo.getE01Z201())&&StringUtils.isEmpty(e01z2Vo.getE01Z227())){
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
                }
            }

            returnMap.put("isRight",isRight);
            returnMap.put("wrongExcelColumns",wrongExcelColumns);
            //添加目录信息及材料
            E01Z1ExcelVo e01Z1ExcelVo = a38ExcelVo.getE01Z1ExcelVo();
            if(e01Z1ExcelVo!=null){
                List<E01Z1Vo> e01Z1Vos = new ArrayList<>();
                e01Z1Vos.addAll(e01Z1ExcelVo.getJlcl());
                e01Z1Vos.addAll(e01Z1ExcelVo.getZzcl());
                e01Z1Vos.addAll(e01Z1ExcelVo.getJdcl());
                e01Z1Vos.addAll(e01Z1ExcelVo.getXlxw());
                e01Z1Vos.addAll(e01Z1ExcelVo.getZyzg());
                e01Z1Vos.addAll(e01Z1ExcelVo.getKysp());
                e01Z1Vos.addAll(e01Z1ExcelVo.getPxcl());
                e01Z1Vos.addAll(e01Z1ExcelVo.getZscl());
                e01Z1Vos.addAll(e01Z1ExcelVo.getDtcl());
                e01Z1Vos.addAll(e01Z1ExcelVo.getJlicl());
                e01Z1Vos.addAll(e01Z1ExcelVo.getCfcl());
                e01Z1Vos.addAll(e01Z1ExcelVo.getGzcl());
                e01Z1Vos.addAll(e01Z1ExcelVo.getRmcl());
                e01Z1Vos.addAll(e01Z1ExcelVo.getCgcl());
                e01Z1Vos.addAll(e01Z1ExcelVo.getDbdh());
                e01Z1Vos.addAll(e01Z1ExcelVo.getQtcl());
                returnMap = DaUtils.checkE01z1(e01Z1Vos,returnMap);
            }

        }else {
            returnMap.put("isRight",isRight);
            returnMap.put("wrongExcelColumns",wrongExcelColumns);
        }
        return returnMap;
    }

    public void saveA38ExcelData(A38ExcelVo a38ExcelVo,UserLoginDetails details){
        String id;
        if(a38ExcelVo!=null){
            //新增档案
            A38Vo jbxxA38Vo = a38ExcelVo.getJbxxA38Vo();
            A38 a38 = new A38();

            String a0104Content = jbxxA38Vo.getA0104Content();
            jbxxA38Vo.setA0104(getDictionaryItem(a0104Content,"GB/T2261.1-2003"));
            String gbztContent = jbxxA38Vo.getGbztContent();
            jbxxA38Vo.setGbztCode(getDictionaryItem(gbztContent,"SAN_GBZT"));

            org.springframework.beans.BeanUtils.copyProperties(jbxxA38Vo, a38);
            a38.setId(null);
            a38.setSjzt("1");
            EntityWrapper.wrapperSaveBaseProperties(a38,details);
            id = a38Service.save(a38);
            if(StringUtils.isNotEmpty(id)){
                a38.setId(id);
                //新增职务变动
                A38Vo a38VoForA52 = a38ExcelVo.getZwbdA38Vo();
                if(a38VoForA52!=null&&a38VoForA52.getA52Vos().size()>0){
                    List<A52Vo> a52Vos = a38VoForA52.getA52Vos();
                    for(int i=0;i<a52Vos.size();i++){
                        A52 a52 = new A52();
                        A52Vo a52Vo = a52Vos.get(i);
                        Integer oldPxInteger=a52Service.getMaxSort(a52Vo.getId());
                        org.springframework.beans.BeanUtils.copyProperties(a52Vo,a52);
                        a52.setA38(a38);
                        a52.setPx(oldPxInteger);
                        EntityWrapper.wrapperSaveBaseProperties(a52,details);
                        a52Service.save(a52);
                    }
                }

                //添加工资变动记录
                List<A32Vo> gzbdList = a38ExcelVo.getGzbdList();
                if(gzbdList.size()>0){
                    for(int i=0;i<gzbdList.size();i++){
                        A32 a32 = new A32();
                        A32Vo a32Vo = gzbdList.get(i);
                        Integer oldPxInteger=a32Service.getMaxSort(a32Vo.getId());

                        org.springframework.beans.BeanUtils.copyProperties(a32Vo,a32);
                        a32.setA38(a38);
                        a32.setPx(oldPxInteger);
                        EntityWrapper.wrapperSaveBaseProperties(a32,details);
                        a32Service.save(a32);
                    }
                }

                //添加材料接收记录
                List<E01z2Vo> e01z2Vos = a38ExcelVo.getE01z2Vos();
                if(e01z2Vos.size()>0){
                    for(int i=0;i<e01z2Vos.size();i++){
                        E01Z2 e01z2 = new E01Z2();
                        E01z2Vo e01z2Vo = e01z2Vos.get(i);
                        Integer oldPxInteger=e01z2Service.getMaxSort(e01z2Vo.getId());

                        String e01Z237Content = e01z2Vo.getE01Z237Content();
                        e01z2Vo.setE01Z237(getDictionaryItem(e01Z237Content,"CLCLBS-2018"));
                        String e01Z244Content = e01z2Vo.getE01Z244Content();
                        e01z2Vo.setE01Z244(getDictionaryItem(e01Z244Content,"SFBS-2018"));

                        org.springframework.beans.BeanUtils.copyProperties(e01z2Vo,e01z2);
                        e01z2.setA38(a38);
                        e01z2.setE01Z214(oldPxInteger);
                        EntityWrapper.wrapperSaveBaseProperties(e01z2,details);
                        e01z2Service.save(e01z2);
                    }
                }
                //添加目录信息及材料
                E01Z1ExcelVo e01Z1ExcelVo = a38ExcelVo.getE01Z1ExcelVo();
                if(e01Z1ExcelVo!=null){
                    addE01z1(e01Z1ExcelVo.getJlcl(), "jlcl", id);
                    addE01z1(e01Z1ExcelVo.getZzcl(), "zzcl", id);
                    addE01z1(e01Z1ExcelVo.getJdcl(), "jdcl", id);
                    addE01z1(e01Z1ExcelVo.getXlxw(), "xlxw", id);
                    addE01z1(e01Z1ExcelVo.getZyzg(), "zyzg", id);
                    addE01z1(e01Z1ExcelVo.getKysp(), "kysp", id);
                    addE01z1(e01Z1ExcelVo.getPxcl(), "pxcl", id);
                    addE01z1(e01Z1ExcelVo.getZscl(), "zscl", id);
                    addE01z1(e01Z1ExcelVo.getDtcl(), "dtcl", id);
                    addE01z1(e01Z1ExcelVo.getJlicl(), "jlicl", id);
                    addE01z1(e01Z1ExcelVo.getCfcl(), "cfcl", id);
                    addE01z1(e01Z1ExcelVo.getGzcl(), "gzcl", id);
                    addE01z1(e01Z1ExcelVo.getRmcl(), "rmcl", id);
                    addE01z1(e01Z1ExcelVo.getCgcl(), "cgcl", id);
                    addE01z1(e01Z1ExcelVo.getDbdh(), "dbdh", id);
                    addE01z1(e01Z1ExcelVo.getQtcl(), "qtcl", id);
                }
            }
        }
    }

    @RequestMapping(value = "/ajax/cwjl")
    public ModelAndView loadGjcx(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("datas",this.wrongExcelColumns);
        return new ModelAndView("saas/zzb/dzda/e01z5/wrongList",map);
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

    /**
     * 新增材料，将错误数据返回
     * @param e01Z1Vos
     * @param listStr
     * @param a38Id
     * @return
     */
    public void addE01z1(List<E01Z1Vo> e01Z1Vos, String listStr, String a38Id){
        if(e01Z1Vos.size()>0){
            //获得材料类别
            String catalogCode = DaUtils.getCatalogCode(listStr);//获取材料类别Code
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" catalogCode = :catalogCode ", "catalogCode", catalogCode));
            CommonOrderBy orderBy = new CommonOrderBy();
            List<ECatalogTypeInfo> entities = eCatalogTypeService.list(query, orderBy);
            ECatalogTypeInfo eCatalogTypeInfo = new ECatalogTypeInfo();
            if(entities.size()>0){
                eCatalogTypeInfo=entities.get(0);
            }
            try {

                for(E01Z1Vo e01Z1Vo:e01Z1Vos) {
                    if (e01Z1Vo != null) {

                        //拼接日期
                        String e01Z117 = "";
                        if(StringUtils.isNotEmpty(e01Z1Vo.getYear())){
                            e01Z117 = e01Z1Vo.getYear();
                            if(StringUtils.isNotEmpty(e01Z1Vo.getMonth())){
                                e01Z117 += e01Z1Vo.getMonth();
                                if(StringUtils.isNotEmpty(e01Z1Vo.getDay())){
                                    e01Z117 += e01Z1Vo.getDay();
                                }
                            }
                        }
                        e01Z1Vo.setE01Z117(e01Z117);

                        int sort = e01Z1Service.getMaxSort(a38Id, eCatalogTypeInfo.getCatalogCode());
                        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
                        E01Z1 e01Z1 = new E01Z1();
                        org.springframework.beans.BeanUtils.copyProperties(e01Z1Vo, e01Z1);
                        e01Z1.setE01Z101B(eCatalogTypeInfo.getCatalogCode());
                        e01Z1.setE01Z101A(eCatalogTypeInfo.getCatalogValue());
                        e01Z1.setECatalogTypeId(eCatalogTypeInfo.getId());
                        e01Z1.setYjztps(0);
                        if (com.hisun.util.StringUtils.isNotBlank(a38Id)) {
                            e01Z1.setA38(a38Service.getByPK(a38Id));
                        }
                        EntityWrapper.wrapperSaveBaseProperties(e01Z1, userLoginDetails);
                        int newSort = e01Z1.getE01Z104();
                        if (newSort < sort) {
                            e01Z1Service.updateSortBeforSave(e01Z1, sort);
                        }
                        e01Z1Service.save(e01Z1);
                    }
                }
            }catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}