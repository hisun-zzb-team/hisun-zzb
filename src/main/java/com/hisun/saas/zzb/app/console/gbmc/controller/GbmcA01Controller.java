package com.hisun.saas.zzb.app.console.gbmc.controller;

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
import com.hisun.saas.zzb.app.console.gbmc.entity.*;
import com.hisun.saas.zzb.app.console.gbmc.entity.GbMcA01;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcA01Service;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcA01Service;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcB01Service;
import com.hisun.saas.zzb.app.console.gbmc.service.GbMcService;
import com.hisun.saas.zzb.app.console.gbmc.vo.GbMcA01Vo;
import com.hisun.saas.zzb.app.console.gbmc.vo.GbMcA01Vo;
import com.hisun.saas.zzb.app.console.gbmc.vo.GbMcA01gzjlVo;
import com.hisun.util.DateUtil;
import com.hisun.util.UUIDUtil;
import com.hisun.util.WordUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/gbmc/a01")
public class GbmcA01Controller extends BaseController{

    @Autowired
    private GbMcA01Service gbMcA01Service;

    @Autowired
    private GbMcB01Service gbMcB01Service;
    @Autowired
    private GbMcService gbMcService;
    @Value("${upload.absolute.path}")
    private String uploadAbsolutePath;

    private final static String DEFAULT_IMG_HEAD_PATH = "/WEB-INF/images/defaultHeadImage.png";

    @RequestMapping("/list")
    public ModelAndView list(HttpServletRequest req, @RequestParam(value="mcb01id",required=false)String mcb01id,@RequestParam(value="mcid")String mcid
            ,String xmQuery,String gbrmspbFileQuery,@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String returnList = "gbmcList";//返回列表 gbmcList返回名册列表 b01Lis返回目录列表
            GbMc gbMc = this.gbMcService.getByPK(mcid);
            CommonConditionQuery query = new CommonConditionQuery();

//            if(mcb01id!=null && !mcb01id.equals("")) {
//                query.add(CommonRestrictions.and(" gbMcB01.id = :mcb01id", "mcb01id", mcb01id));
//            }else{
//                query.add(CommonRestrictions.and(" gbMc.id = :mcid", "mcid", mcid));
//            }
//            if(xmQuery!=null && !xmQuery.equals("")){
//                query.add(CommonRestrictions.and(" xm like :xmQuery", "xmQuery", "%"+ xmQuery+ "%"));
//            }
//            query.add(CommonRestrictions.and("  tombstone = :tombstone", "tombstone", 0));
//            CommonOrderBy orderBy = new CommonOrderBy();
//            orderBy.add(CommonOrder.asc("gbMcB01.px"));
//            orderBy.add(CommonOrder.asc("px"));

            String hql = " from GbMcA01 a01  ";
            if(StringUtils.isNotBlank(gbrmspbFileQuery) && !gbrmspbFileQuery.equals("all")) {
                if(gbrmspbFileQuery.equals("no")) {
                    hql = hql + " left join a01.gbMca01gbrmspbs rmspb where rmspb.id is null and";
                }else{
                    hql = hql + " left join a01.gbMca01gbrmspbs rmspb where  rmspb.filepath <>'' and";
                }
            }else{
                hql = hql+" where";
            }
            List<Object> paramList = Lists.newArrayList();

            if(mcb01id!=null && !mcb01id.equals("")) {
                paramList.add(mcb01id);
                hql = hql+" a01.gbMcB01.id = ?";
            }else{
                paramList.add(mcid);
                hql = hql+" a01.gbMc.id = ?";
            }
            if(xmQuery!=null && !xmQuery.equals("")){
                paramList.add("%"+ xmQuery+ "%");
                hql = hql+" and a01.xm like  ? ";
//                query.add(CommonRestrictions.and(" xm like :xmQuery", "xmQuery", "%"+ xmQuery+ "%"));
            }
            hql = hql+" and a01.tombstone =? order by a01.gbMcB01.px,a01.px";
            paramList.add(0);
            int total = this.gbMcA01Service.count("select  count(DISTINCT a01.id) "+hql,paramList);
            List<GbMcA01> gbMcA01s = this.gbMcA01Service.list("select  DISTINCT(a01) "+hql,paramList, pageNum,
                    pageSize);
            List<GbMcA01Vo> shpcVos = new ArrayList<GbMcA01Vo>();
            if (gbMcA01s != null) {// entity ==> vo
                for (GbMcA01 gbMcA01 : gbMcA01s) {
                    GbMcA01Vo vo = new GbMcA01Vo();
                    BeanUtils.copyProperties(vo, gbMcA01);
                    if(gbMcA01.getGbMca01gbrmspbs()!=null &&gbMcA01.getGbMca01gbrmspbs().size()>0) {
                        GbMcA01gbrmspb gbMcA01gbrmspb = gbMcA01.getGbMca01gbrmspbs().get(0);
                        if(gbMcA01gbrmspb.getFilepath()!=null && !gbMcA01gbrmspb.getFilepath().equals("")){
                            vo.setHavagbrmspbFile(true);
                        }
                    }
                    shpcVos.add(vo);
                }
            }
            PagerVo<GbMcA01Vo> pager = new PagerVo<GbMcA01Vo>(shpcVos, total,
                    pageNum, pageSize);

            //返回列表 gbmcList返回名册列表 b01Lis返回目录列表
            if(mcb01id==null || mcb01id.equals ("")){
                returnList = "gbmcList";
            }else{
                if(gbMc.getIsMl() == GbMc.YML){
                    returnList = "b01Lis";
                }else{
                    returnList = "gbmcList";
                }
            }
            map.put("pager", pager);
            map.put("mcb01id", mcb01id);
            map.put("mcid", mcid);
            map.put("returnList", returnList);
            map.put("xmQuery", xmQuery);
            map.put("gbrmspbFileQuery", gbrmspbFileQuery);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/gbmc/a01/list", map);
    }

    @RequestMapping(value="/ajax/execute")
    public @ResponseBody
    Map<String,Object> upload(String mcb01id, String token, @RequestParam(value="attachFile",required=false) MultipartFile file,
                              HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserLoginDetails userLoginDetails = UserLoginDetailsUtil.getUserLoginDetails();
        Map<String,Object> map = new HashMap<String,Object>();
        if(file==null || file.isEmpty()){
            map.put("code", -1);
            map.put("message", "文件没有内容");
            return map;
        }

        try{
            String fileName = file.getOriginalFilename();
            if(fileName.endsWith(".doc") ||fileName.endsWith(".DOC") ||fileName.endsWith(".docx") ||fileName.endsWith(".DOCX") ){
                String fileDir = uploadAbsolutePath + GbMcA01Service.ATTS_PATH;
                File _fileDir = new File(fileDir);
                if (_fileDir.exists() == false) {
                    _fileDir.mkdirs();
                }
                String savePath = fileDir + UUIDUtil.getUUID()+"_"+fileName;

                try {
                    File tempFile = new File(savePath);
                    FileOutputStream fos = new FileOutputStream(tempFile);
                    fos.write(file.getBytes());
                    fos.flush();
                    fos.close();
                    //先将word转成Map
                    String tmplateWordPath = uploadAbsolutePath + GbMcA01Service.IMPORT_DOC_TEMPLATE;
                    WordUtil wordUtil = WordUtil.newInstance();
                    Map<String,String> dataMap = wordUtil.convertMapByTemplate(savePath,tmplateWordPath);
                    GbMcB01 gbMcB01 = this.gbMcB01Service.getByPK(mcb01id);
                    this.gbMcA01Service.saveFromWordDataMap(dataMap,gbMcB01);
                    tempFile.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new GenericException(e);
                }
            }else{
                map.put("code", -1);
                map.put("message", "请上传word");
                return map;
            }
        }catch(Exception e){
            map.put("code", -1);
            map.put("message", "读取文件错误，请检查word是否能正确打开");
            return map;
        }
        try{

        }catch(GenericException e){
            logger.error(e, e);
            map.put("code", -1);
            map.put("message", e.getMessage());
            return map;
        }catch(Exception e){
            logger.error(e, e);
            map.put("code", -1);
            map.put("message", "系统错误，请联系管理员");
            return map;
        }
        map.put("code", 1);
        return map;
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
            GbMcA01 gbMcA01 = this.gbMcA01Service.getByPK(id);
            if(gbMcA01 != null){
                this.gbMcA01Service.delete(gbMcA01);
            }
            map.put("success", true);
        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "删除失败！");
            throw new GenericException(e);
        }
        return map;

    }
    @RequestMapping("/{id}/photo")
    public HttpEntity<byte[]> getPhoto (@PathVariable("id")String id,
                                        HttpServletRequest request, HttpServletResponse response) throws IOException {
        GbMcA01 gbMcA01 = this.gbMcA01Service.getByPK(id);
        if(StringUtils.isEmpty(gbMcA01.getZppath())==false){
            File file = new File(uploadAbsolutePath+gbMcA01.getZppath());
            if(file.exists()){
                FileInputStream fis = new FileInputStream(file);
                StreamUtils.copy(fis,response.getOutputStream());
                response.setContentType(MediaType.IMAGE_JPEG_VALUE);
                return new HttpEntity(HttpStatus.OK);
            }else{
                //为空或者没有返回默认图片
                File defaultfile = new File(request.getServletContext().getRealPath(DEFAULT_IMG_HEAD_PATH));
                FileInputStream fis = new FileInputStream(defaultfile);
                StreamUtils.copy(fis,response.getOutputStream());
                response.setContentType(MediaType.IMAGE_PNG_VALUE);
                return new HttpEntity(HttpStatus.OK);
            }
        }else{
            //为空或者没有返回默认图片
            File defaultfile = new File(request.getServletContext().getRealPath(DEFAULT_IMG_HEAD_PATH));
            FileInputStream fis = new FileInputStream(defaultfile);
            StreamUtils.copy(fis,response.getOutputStream());

            response.setContentType(MediaType.IMAGE_PNG_VALUE);
            return new HttpEntity(HttpStatus.OK);
        }

    }
    /**
     * 调转到查看页面
     * @return
     */
//    @RequiresPermissions("admin-assetStatus:edit")
    @RequestMapping(value = "/view")
    public ModelAndView view(@RequestParam(value="id")String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
           GbMcA01 gbMcA01 = this.gbMcA01Service.getByPK(id);
           GbMcA01Vo gbMcA01Vo = new GbMcA01Vo();
            boolean isHavagbrmspbFile = false;//是否存在干部详细信息
            //判断干部详细信息是否有附件
            if(gbMcA01.getGbMca01gbrmspbs()!=null &&gbMcA01.getGbMca01gbrmspbs().size()>0) {
                GbMcA01gbrmspb gbMcA01gbrmspb = gbMcA01.getGbMca01gbrmspbs().get(0);
                if(gbMcA01gbrmspb.getFilepath()!=null && !gbMcA01gbrmspb.getFilepath().equals("")){
                    isHavagbrmspbFile = true;
                }
            }

            if (gbMcA01 == null) {
                logger.error("数据不存在");
                throw new GenericException("数据不存在");
            }
            BeanUtils.copyProperties(gbMcA01Vo, gbMcA01);
            List<GbMcA01gzjlVo> gzjlVos = new ArrayList<GbMcA01gzjlVo>();
            if(gbMcA01.getGbMca01gzjls()!=null){
                for(GbMcA01gzjl gbMcA01gzjl : gbMcA01.getGbMca01gzjls()){
                    GbMcA01gzjlVo gbMcA01gzjlVo = new GbMcA01gzjlVo();
                    BeanUtils.copyProperties(gbMcA01gzjlVo, gbMcA01gzjl);
                    gzjlVos.add(gbMcA01gzjlVo);
                }
            }
            gbMcA01Vo.setGzjlVos(gzjlVos);

            map.put("gbMcA01Vo", gbMcA01Vo);
            map.put("mcb01id", gbMcA01.getGbMcB01().getId());
            map.put("mcid", gbMcA01.getGbMcB01().getGbMc().getId());
            map.put("mcb01id", gbMcA01.getGbMcB01().getId());
            map.put("isHavagbrmspbFile", isHavagbrmspbFile);

        }catch(Exception e){
            map.put("success", false);
            map.put("msg", "修改失败！");
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/gbmc/a01/view", map);
    }
}
