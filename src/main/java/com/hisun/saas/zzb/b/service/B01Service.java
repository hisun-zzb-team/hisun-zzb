/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.b.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.sys.taglib.treeTag.TreeNode;
import com.hisun.saas.zzb.b.entity.B01;
import com.hisun.saas.zzb.b.vo.B01TreeNode;
import com.hisun.saas.zzb.b.vo.B01Vo;
import com.hisun.saas.zzb.dzda.a32.entity.A32;

import java.util.List;

/**
 * @author liuzj {279421824@qq.com}
 */
public interface B01Service extends BaseService<B01,String> {
    public Integer getMaxSort(String parentId);
    void updatePx(int oldPx, int newPx, String parentId);
    List<B01TreeNode> getB01TreeVoList(String id,String param,String defaultkeys)throws Exception;

    String saveB01(B01Vo vo) throws Exception;
    void updatePxAndCxbm(B01 b01,B01 parentB01,Integer oldSort)throws Exception;

    void updateB01(B01 b01,
                         String oldPid) throws Exception;
}
