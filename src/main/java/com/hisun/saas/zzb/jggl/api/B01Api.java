/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.jggl.api;

import com.google.common.collect.Maps;
import com.hisun.base.controller.BaseController;
import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.exception.GenericException;
import com.hisun.saas.zzb.b.service.B01Service;
import com.hisun.saas.zzb.b.vo.B01TreeNode;
import com.hisun.util.JacksonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rocky {rockwithyou@126.com}
 */
@Controller
@RequestMapping("/api/b01")
public class B01Api {
    @Resource
    private B01Service b01Service;

    /**
     * 对外使用的机构树
     * @param response
     * @return
     * @throws GenericException
     */
    @RequestMapping(value="/dtjz/tree")
    public void loadTree(HttpServletRequest request,HttpServletResponse response, String id, String param, String defaultkeys,String bSjlx) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<B01TreeNode> nodes = this.b01Service.getB01TreeVoList(id,param,defaultkeys,bSjlx);
            String json= JacksonUtil.nonDefaultMapper().toJson(nodes);
            response.getWriter().print(json);
//            map.put("data", nodes);
//            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            map.put("message", "服务器忙，请稍后再试！");
        }
    }

}
