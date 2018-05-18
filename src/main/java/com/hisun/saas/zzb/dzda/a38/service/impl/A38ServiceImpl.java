/*
 * Copyright (c) 2018. Hunan Hisun Union Information Technology Co, Ltd. All rights reserved.
 * http://www.hn-hisun.com
 * 注意:本内容知识产权属于湖南海数互联信息技术有限公司所有,除非取得商业授权,否则不得用于商业目的.
 */

package com.hisun.saas.zzb.dzda.a38.service.impl;

import com.hisun.base.dao.BaseDao;
import com.hisun.base.service.impl.BaseServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.tenant.privilege.dao.TenantPrivilegeDao;
import com.hisun.saas.sys.tenant.privilege.entity.TenantPrivilege;
import com.hisun.saas.sys.tenant.privilege.service.TenantPrivilegeService;
import com.hisun.saas.zzb.dzda.a38.dao.A38Dao;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
import com.hisun.saas.zzb.dzda.a38.service.A38Service;
import com.hisun.saas.zzb.dzda.dak.vo.DakVo;
import com.hisun.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuzj {279421824@qq.com}
 */
@Service
public class A38ServiceImpl extends BaseServiceImpl<A38,String>
        implements A38Service {

    private A38Dao a38Dao;

    @Resource
    public void setBaseDao(BaseDao<A38, String> baseDao) {
        this.baseDao = baseDao;
        this.a38Dao = (A38Dao)baseDao;
    }

    public List<A38> gjcxList(DakVo dakVo,UserLoginDetails userLoginDetails){
        StringBuffer sql = new StringBuffer();
        sql.append("select * from ( select a38.* from A38 a38 ")
                .append(" inner join e01z1 e on a38.id = e.a38_id ")
                .append(" where sjzt = 1 and a38.tenant_id = '" + userLoginDetails.getTenantId() + "'");
        if(StringUtils.isNotEmpty(dakVo.getA0101())){//姓名
            sql.append(" and a38.a0101 like '%" + dakVo.getA0101() + "%'");
        }
        if(StringUtils.isNotEmpty(dakVo.getA0157())){//单位职务
            sql.append(" and a38.a0157 like '%" + dakVo.getA0157() + "%'");
        }

        if(StringUtils.isNotEmpty(dakVo.getDabh())){//档案编号
            sql.append(" and a38.dabh =  '" + dakVo.getDabh() + "'");
        }
        if(StringUtils.isNotEmpty(dakVo.getSmxh())){//扫描序号
            sql.append(" and a38.smxh =  '" + dakVo.getSmxh() + "'");
        }

        if(StringUtils.isNotEmpty(dakVo.getGbztCodes())){//干部状态
            sql.append(" and a38.gbzt_code =  '" + dakVo.getGbztCodes() + "'");
        }
        if(StringUtils.isNotEmpty(dakVo.getGbztContents())){
            sql.append(" and a38.gbzt_content =  '" + dakVo.getGbztContents() + "'");
        }
        if(StringUtils.isNotEmpty(dakVo.getDaztCodes())){//档案状态
            sql.append(" and a38.dazt_code =  '" + dakVo.getDaztCodes() + "'");
        }
        if(StringUtils.isNotEmpty(dakVo.getDaztContents())){
            sql.append(" and a38.dazt_content =  '" + dakVo.getDaztContents() + "'");
        }

        if(StringUtils.isNotEmpty(dakVo.getDutyLevelValue())){//现职级
            sql.append(" and a38.duty_level_value =  '" + dakVo.getDutyLevelValue() + "'");
        }

        if(StringUtils.isNotEmpty(dakVo.getDutyLevelTimeBaseStart())){//职级时间
            sql.append(" and a38.duty_level_time_base >=  '" + dakVo.getDutyLevelTimeBaseStart() + "'");
        }
        if(StringUtils.isNotEmpty(dakVo.getDutyLevelTimeBaseEnd())){//职级时间
            sql.append(" and a38.duty_level_time_base <=  '" + dakVo.getDutyLevelTimeBaseEnd() + "'");
        }


        if(StringUtils.isNotEmpty(dakVo.getA3804A())){//档案来处
            sql.append(" and a38.a3804a like '%" + dakVo.getA3804A() + "%'");
        }
        if(StringUtils.isNotEmpty(dakVo.getA3821())){//专去单位
            sql.append(" and a38.a3821 like '%" + dakVo.getA3821() + "%'");
        }

        if(StringUtils.isNotEmpty(dakVo.getA3801Start())){//接受时间
            sql.append(" and a38.a3801 >= '%" + dakVo.getA3801Start() + "%'");
        }
        if(StringUtils.isNotEmpty(dakVo.getA3801End())){//接受时间
            sql.append(" and a38.a3801 <= '%" + dakVo.getA3801End() + "%'");
        }

        if(StringUtils.isNotEmpty(dakVo.getA3817Start())){//转出时间
            sql.append(" and a38.a3817 >= '%" + dakVo.getA3817Start() + "%'");
        }
        if(StringUtils.isNotEmpty(dakVo.getA3817End())){//转出时间
            sql.append(" and a38.a3817 <= '%" + dakVo.getA3817End() + "%'");
        }

        if(StringUtils.isNotEmpty(dakVo.getE01Z111())){//材料名称
            sql.append(" and e.e01z111 like '%" + dakVo.getE01Z111() + "%'");
        }
        if(StringUtils.isNotEmpty(dakVo.getE01Z101B())){//材料类型
            sql.append(" and e.e01z101b like '%" + dakVo.getE01Z101B() + "%'");
        }

        if(StringUtils.isNotEmpty(dakVo.getE01Z207())){//接收人
            sql.append(" and e.e01z207 like '%" + dakVo.getE01Z207() + "%'");
        }
        if(StringUtils.isNotEmpty(dakVo.getE01Z201Start())){//接收时间
            sql.append(" and e.e01z201 >= '%" + dakVo.getE01Z201Start() + "%'");
        }
        if(StringUtils.isNotEmpty(dakVo.getE01Z201End())){//接收时间
            sql.append(" and e.e01z201 <= '%" + dakVo.getE01Z201End() + "%'");
        }

        if(StringUtils.isNotEmpty(dakVo.getE01Z204())){//材料来处
            sql.append(" and e.e01z204 like '%" + dakVo.getE01Z204() + "%'");
        }
        if(StringUtils.isNotEmpty(dakVo.getE01z1CreatedTimeStart())){//创建时间
            sql.append(" and e.create_date >= '%" + dakVo.getE01z1CreatedTimeStart() + "%'");
        }
        if(StringUtils.isNotEmpty(dakVo.getE01z1CreatedTimeEnd())){//创建时间
            sql.append(" and e.create_date <= '%" + dakVo.getE01z1CreatedTimeEnd() + "%'");
        }

        if(StringUtils.isNotEmpty(dakVo.getE01Z117Start())){//材料制成时间
            sql.append(" and e.e01z117 >= '%" + dakVo.getE01Z117Start() + "%'");
        }
        if(StringUtils.isNotEmpty(dakVo.getE01Z117End())){//材料制成时间
            sql.append(" and e.e01z117 <= '%" + dakVo.getE01Z117End() + "%'");
        }
        if(StringUtils.isNotEmpty(dakVo.getYjztps())){//是否加载图片
            if("1".equals(dakVo.getYjztps())){
                sql.append(" and e.yjztps >0 ");
            }else {
                sql.append(" and ( e.yjztps <=0 or e.yjztps is null )");
            }
        }
        sql.append(" order by a38.smxh desc , a38.a0101 asc ) a38");
        sql.append(" group by a38.id ");

        Map<String,Object> paramMap = new HashMap<>();
        List<A38> a38List = a38Dao.gjcxList(sql.toString(),paramMap);
        return a38List;
}

}
