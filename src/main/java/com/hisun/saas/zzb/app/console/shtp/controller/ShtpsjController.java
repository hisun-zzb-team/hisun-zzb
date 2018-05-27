package com.hisun.saas.zzb.app.console.shtp.controller;

import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.zzb.app.console.shpc.entity.Sha01;
import com.hisun.saas.zzb.app.console.shpc.service.Sha01Service;
import com.hisun.saas.zzb.app.console.shtp.entity.Shtp;
import com.hisun.saas.zzb.app.console.shtp.entity.Shtpsj;
import com.hisun.saas.zzb.app.console.shtp.service.ShtpService;
import com.hisun.saas.zzb.app.console.shtp.service.ShtpsjService;
import com.hisun.saas.zzb.app.console.shpc.vo.Sha01Vo;
import com.hisun.saas.zzb.app.console.shtp.vo.ShtpsjVo;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/8.
 */
@Controller
@RequestMapping("/zzb/app/console/tpsj")
public class ShtpsjController extends BaseController {
    @Resource
    private ShtpsjService shtpsjService;
    @Resource
    private Sha01Service sha01Service;
    @Resource
    private ShtpService shtpService;

    @RequestMapping("/")
    public ModelAndView list(HttpServletRequest req,@RequestParam(value="shtpId")String shtpId,String tpyj,
                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String tprxm ="";
            String tpqbh="";
            String shpcId = "";
            CommonConditionQuery query = new CommonConditionQuery();
            query.add(CommonRestrictions.and(" shtp.id = :shtpId", "shtpId", shtpId));
            if(tpyj!=null && !tpyj.equals("") && !tpyj.equals("all")){
                query.add(CommonRestrictions.and(" tp = :tpyj", "tpyj",  Integer.valueOf(tpyj)));
            }
            query.add(CommonRestrictions.and(" tombstone = :tombstone", "tombstone", 0));

            CommonOrderBy orderBy = new CommonOrderBy();
            orderBy.add(CommonOrder.desc("tp"));

            Long total = this.shtpsjService.count(query);
            List<Shtpsj> shtpsjs = this.shtpsjService.list(query, orderBy, pageNum,
                    pageSize);
            List<ShtpsjVo> shtpsjVos = new ArrayList<ShtpsjVo>();
            Shtp shtp = this.shtpService.getByPK(shtpId);
            tprxm = shtp.getTpr_xm();
            tpqbh = shtp.getTpq_bh();
            shpcId = shtp.getShpc().getId();
            if (shtpsjs != null) {
                for (Shtpsj shtpsj : shtpsjs) {

                    ShtpsjVo vo = new ShtpsjVo();
                    Sha01Vo sha01Vo = new Sha01Vo();
                    BeanUtils.copyProperties(vo, shtpsj);
                    BeanUtils.copyProperties(sha01Vo, shtpsj.getSha01());
                    vo.setSha01Vo(sha01Vo);
                    shtpsjVos.add(vo);
                }
            }
            PagerVo<ShtpsjVo> pager = new PagerVo<ShtpsjVo>(shtpsjVos, total.intValue(),
                    pageNum, pageSize);
            map.put("pager", pager);
            map.put("shtpId", shtpId);
            map.put("shpcId", shpcId);
            map.put("tpqbh", tpqbh);
            map.put("tpyj", tpyj);
        } catch (Exception e) {
            throw new GenericException(e);
        }
        return new ModelAndView("/saas/zzb/app/console/tpsj/list", map);
    }

}
