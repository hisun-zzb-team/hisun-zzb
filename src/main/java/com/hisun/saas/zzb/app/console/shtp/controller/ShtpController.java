package com.hisun.saas.zzb.app.console.shtp.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.entity.Shpc;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01Service;
import com.hisun.saas.zzb.app.console.shpc.vo.Sha01Vo;
import com.hisun.saas.zzb.app.console.shtp.entity.Shtp;
import com.hisun.saas.zzb.app.console.shpc.service.ShpcService;
import com.hisun.saas.zzb.app.console.shtp.entity.Shtpsj;
import com.hisun.saas.zzb.app.console.shtp.service.ShtpService;
import com.hisun.saas.zzb.app.console.shpc.vo.ShpcVo;
import com.hisun.saas.zzb.app.console.shtp.service.ShtpsjService;
import com.hisun.saas.zzb.app.console.shtp.vo.ShtpVo;
import com.hisun.util.DateUtil;
import com.hisun.util.WebUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/8.
 */
@Controller
@RequestMapping("/zzb/app/console/tp")
public class ShtpController extends BaseController {
    @Resource
    private ShtpService shtpService;
    @Resource
    private ShpcService shpcService;
    @Resource
    private Sha01Service sha01Service;
    @Resource
    private ShtpsjService shtpsjService;

    @RequestMapping("/")
    public ModelAndView list(HttpServletRequest req, String pcmc,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            query.add(CommonRestrictions.and(" sjlx = :sjlx", "sjlx", Shpc.SJLX_GB));
            if(pcmc!=null && !pcmc.equals("")){
                query.add(CommonRestrictions.and(" pcmc like :pcmc", "pcmc",  "%"+pcmc+ "%"));
            }
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("px"));

            Long total = this.shpcService.count(query);
            List<Shpc> shpcs = this.shpcService.list(query, orderBy, pageNum,pageSize);
            List<ShpcVo> shpcVos = new ArrayList<ShpcVo>();
            if (shpcs != null) {
                for (Shpc shpc : shpcs) {
                    ShpcVo vo = new ShpcVo();
                    BeanUtils.copyProperties(vo, shpc);
                    vo.setPcsjValue(DateUtil.formatDateByFormat(shpc.getPcsj(),"yyyyMMdd"));
                    vo.setTpCount(shpc.getShtps().size());
                    shpcVos.add(vo);
                }
            }
            PagerVo<ShpcVo> pager = new PagerVo<ShpcVo>(shpcVos, total.intValue(),
                    pageNum, pageSize);
            map.put("pager", pager);
            map.put("pcmc", pcmc);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/tp/list", map);
    }

    @RequestMapping("/list")
    public ModelAndView tplist(HttpServletRequest req,@RequestParam(value="shpcId")String shpcId,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        String pcmc = "";
        try {
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" shpc.id = :shpcId", "shpcId",shpcId));
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.desc("tp_sj"));
            Shpc shpc = this.shpcService.getByPK(shpcId);
            pcmc = shpc.getPcmc();
            Long total = this.shtpService.count(query);
            List<Shtp> shtps = this.shtpService.list(query, orderBy, pageNum,pageSize);
            List<ShtpVo> shtpVos = new ArrayList<ShtpVo>();
            if (shtps != null) {// entity ==> vo
                for (Shtp shtp : shtps) {
                    ShtpVo vo = new ShtpVo();
                    BeanUtils.copyProperties(vo, shtp);
                    vo.setTp_sj_str(DateUtil.formatDateByFormat(shtp.getTp_sj(),"yyyyMMdd"));
                    vo.setTpqkCount(shtp.getShtpsjs().size());
                    shtpVos.add(vo);
                }
            }
            PagerVo<ShtpVo> pager = new PagerVo<ShtpVo>(shtpVos, total.intValue(),
                    pageNum, pageSize);
            map.put("pager", pager);
            map.put("shpcId", shpcId);
            map.put("pcmc", pcmc);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/tp/tpList", map);
    }

    @RequestMapping("/result")
    public ModelAndView resultList(HttpServletRequest req,@RequestParam(value="shpcId")String shpcId,
                                 @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String shpcmc = "";
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" shpc.id = :shpcId", "shpcId", shpcId));
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.asc("px"));

            //取得当前批次下的人员列表
            Long total = this.sha01Service.count(query);
            List<Sha01> sha01s = this.sha01Service.list(query,orderBy, pageNum,
                    pageSize);
            List<Sha01Vo> sha01Vos = new ArrayList<Sha01Vo>();
            //查询该批次下的投票数据
            query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" shtp.shpc.id = :shpcId", "shpcId", shpcId));
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));
            List<Shtpsj> shtpsjs = this.shtpsjService.list(query, null);
            Shpc shpc = this.shpcService.getByPK(shpcId);
            shpcmc = shpc.getPcmc();
            if (sha01s != null) {// entity ==> vo
                for (Sha01 sha01 : sha01s) {
                    Sha01Vo vo = new Sha01Vo();
                    BeanUtils.copyProperties(vo, sha01);
                    int tyCount = 0;//同意票数
                    int btyCount = 0;//不同意票数
                    int qqCount = 0;//弃权票数
                    String dplCount = "0";//得票率

                    if(shtpsjs!=null){
                        for(Shtpsj shtpsj : shtpsjs){
                            if(shtpsj.getSha01().getId().equals(sha01.getId())){
                                if(shtpsj.getTp()==1){
                                    tyCount++;
                                }else  if(shtpsj.getTp()==2){
                                    btyCount++;
                                }else  if(shtpsj.getTp()==3){
                                    qqCount++;
                                }
                            }
                        }
                    }
                    float num= (float)tyCount/(tyCount+btyCount+qqCount)*100;
                    if(Float.isNaN(num)){
                        dplCount="NaN";
                    }else {
                        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
                        dplCount = df.format(num);//返回的是String类型
                    }
                    vo.setTyCount(tyCount);
                    vo.setBtyCount(btyCount);
                    vo.setQqCount(qqCount);
                    vo.setDplCount(dplCount);

                    sha01Vos.add(vo);
                }
            }
            PagerVo<Sha01Vo> pager = new PagerVo<Sha01Vo>(sha01Vos, total.intValue(),
                    pageNum, pageSize);
            map.put("pager", pager);
            map.put("shpcId", shpcId);
            map.put("shpcmc", shpcmc);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/tp/result", map);
    }



    @RequestMapping(value = "/ajax/exportToExcel")
    public void exportToExcel(@RequestParam(value="shpcId")String shpcId,
            HttpServletRequest request,HttpServletResponse response) {
        try {
            Shpc shpc = this.shpcService.getByPK(shpcId);
            String fileName ="“"+ shpc.getPcmc()+"”票决结果";
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName="+encode(fileName+".xls"));
            OutputStream output=response.getOutputStream();
            this.shpcService.exportExcel(fileName,shpcService.getShpcById(shpcId),output);
            output.flush();
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String encode(String filename) throws UnsupportedEncodingException {
        if (WebUtil.getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
            filename = URLEncoder.encode(filename, "UTF-8");
        } else {
            filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
        }
        return filename;
    }
}
