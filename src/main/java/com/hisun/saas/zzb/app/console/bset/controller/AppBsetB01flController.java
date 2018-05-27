package com.hisun.saas.zzb.app.console.bset.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.base.vo.PagerVo;
import com.hisun.saas.zzb.app.console.bset.entity.AppBsetFl;
import com.hisun.saas.zzb.app.console.bset.vo.B01TreeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouying on 2017/9/16.
 */
@Controller
@RequestMapping("/zzb/app/console/bset/fl")
public class AppBsetB01flController extends BaseController{

    @RequestMapping(value = "/flManage")
    public ModelAndView list(){
        return new ModelAndView("saas/zzb/app/console/bsetB01/fl/flManage");
    }

    @RequestMapping(value="/ajax/load/tree")
    public @ResponseBody
    Map<String,Object> loadTree(HttpServletRequest request)throws GenericException {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<B01TreeVo> b01TreeVoList = new ArrayList<B01TreeVo>();
            B01TreeVo vo = new B01TreeVo();

            vo.setId("1");
            vo.setName("湘西州");
            vo.setpId("0");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);
            vo = new B01TreeVo();
            vo.setId("2");
            vo.setName("市四套班子，纪委班子");
            vo.setpId("1");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);
            vo = new B01TreeVo();
            vo.setId("3");
            vo.setName("市纪委和市委工作部门");
            vo.setpId("1");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);
            vo = new B01TreeVo();
            vo.setId("4");
            vo.setName("市人大常委会工作部门");
            vo.setpId("1");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);
            vo = new B01TreeVo();
            vo.setId("5");
            vo.setName("市政府工作部门");
            vo.setpId("1");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);
            vo = new B01TreeVo();
            vo.setId("6");
            vo.setName("市政府派出机构");
            vo.setpId("1");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);
            vo = new B01TreeVo();
            vo.setId("7");
            vo.setName("市法院、检察院");
            vo.setpId("1");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);
            vo = new B01TreeVo();
            vo.setId("8");
            vo.setName("事业单位");
            vo.setpId("1");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);
            vo = new B01TreeVo();
            vo.setId("9");
            vo.setName("区、县级市");
            vo.setpId("1");
            vo.setIsParent(true);
            vo.setDropInner(true);
            vo.setOpen(true);
            b01TreeVoList.add(vo);
            // 添加未分组节点
            map.put("customTree", b01TreeVoList);
            map.put("success", true);
        } catch (Exception e) {
            logger.error(e,e);
            map.put("success", false);
            map.put("message", "服务器忙，请稍后再试！");
        }
        return map;
    }
    @RequestMapping(value="/ajax/list")
    public ModelAndView getList(String queryId,String b0101Query,
                                @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        Map<String, Object> map = Maps.newHashMap();
        try {

            map.put("success", true);
        } catch (Exception e) {
            logger.error(e,e);
            map.put("success", false);
        }
        return new ModelAndView("saas/zzb/app/console/bsetB01/fl/rightList", map);

    }


}
