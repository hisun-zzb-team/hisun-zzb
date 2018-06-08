/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a38.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.tenant.privilege.entity.TenantPrivilege;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.vo.A38ExcelVo;
import com.hisun.saas.zzb.dzda.a38.vo.A38Vo;
import com.hisun.saas.zzb.dzda.dak.vo.DakVo;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author liuzj {279421824@qq.com}
 */
public interface A38Service extends BaseService<A38,String>{
    String getGjcxHql(DakVo dakVo,UserLoginDetails userLoginDetails);
    Map<String,Object> checkA38(A38Vo a38);
    Map<String,Object> checkA38ExcelData(A38ExcelVo a38ExcelVo, Map<String,Object> returnMap);
    String saveA38(A38Vo a38Vo,UserLoginDetails details);
    String saveA38ExcelData(A38ExcelVo a38ExcelVo,UserLoginDetails details,boolean gzbdIsEmpty,boolean cljsIsEmpty);
    /**
     * 档案信息列表导出
     * @param resp
     * @param resultList
     * @return
     */
    String download(HttpServletResponse resp, List<A38> resultList);

    /**
     * 从广州三零系统导入数据
     * @param dataSource
     * @return
     */
    int saveFromGzslws(DataSource dataSource)throws Exception;
}
