/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.b.service;

import com.hisun.base.service.BaseService;
import com.hisun.saas.zzb.b.entity.B01;
import com.hisun.saas.zzb.b.entity.BFl;
import com.hisun.saas.zzb.b.vo.B01TreeNode;

import java.util.List;

/**
 * @author liuzj {279421824@qq.com}
 */
public interface BFlService extends BaseService<BFl,String> {
    Integer getMaxSort();
    void updateBFl(BFl bFl, Integer oldSort,String parentId)  throws Exception;
}
