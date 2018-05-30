package com.hisun.saas.zzb.dzda.util;

import com.hisun.base.dao.util.CommonConditionQuery;
import com.hisun.base.dao.util.CommonOrder;
import com.hisun.base.dao.util.CommonOrderBy;
import com.hisun.base.dao.util.CommonRestrictions;
import com.hisun.base.entity.TombstoneEntity;
import com.hisun.saas.sys.admin.dictionary.entity.DictionaryItem;
import com.hisun.saas.sys.admin.dictionary.service.DictionaryItemService;
import com.hisun.saas.sys.admin.dictionary.service.impl.DictionaryItemServiceImpl;
import com.hisun.saas.sys.admin.dzda.entity.ECatalogTypeInfo;
import com.hisun.saas.sys.admin.dzda.service.ECatalogTypeService;
import com.hisun.saas.sys.admin.dzda.service.impl.ECatalogTypeServiceImpl;
import com.hisun.saas.sys.auth.UserLoginDetails;
import com.hisun.saas.sys.auth.UserLoginDetailsUtil;
import com.hisun.saas.sys.util.EntityWrapper;
import com.hisun.saas.zzb.dzda.a32.entity.A32;
import com.hisun.saas.zzb.dzda.a32.service.A32Service;
import com.hisun.saas.zzb.dzda.a32.service.impl.A32ServiceImpl;
import com.hisun.saas.zzb.dzda.a32.vo.A32Vo;
import com.hisun.saas.zzb.dzda.a38.entity.A38;
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
import com.hisun.saas.zzb.dzda.mlcl.service.impl.E01Z1ServiceImpl;
import com.hisun.saas.zzb.dzda.mlcl.vo.E01Z1ExcelVo;
import com.hisun.saas.zzb.dzda.mlcl.vo.E01Z1Vo;
import com.hisun.util.StringUtils;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 60514 on 2018/5/30.
 */
public class DaUtils {
    @Resource
    static DictionaryItemService dictionaryItemService;
    /**
     * 判断字符串是否为日期格式
     * @param dateStr
     * @return
     */
    public static boolean isNotDate(String dateStr){
        if(StringUtils.isNotEmpty(dateStr)) {
            int lengh = dateStr.length();
            if (lengh == 4 || lengh == 6 || lengh == 8) {
                if (StringUtils.isNumeric(dateStr)) {
                    if(lengh==6){
                        int mouth = Integer.parseInt(dateStr.substring(4,6));
                        if(mouth>=1&&mouth<=12){
                            return false;
                        }
                        return true;
                    }else if(lengh==8){
                        int mouth = Integer.parseInt(dateStr.substring(4,6));
                        int day = Integer.parseInt(dateStr.substring(6,8));
                        if(mouth>=1&&mouth<=12&&day>=1&&day<=31){
                            return false;
                        }
                        return true;
                    }
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 获得list对应的目录code
     * @param listStr
     * @return
     */
    public static String getCatalogCode(String listStr){
        String catalogCode = "";
        if("jlcl".equals(listStr)){
            catalogCode = "010";
        }else if("zzcl".equals(listStr)){
            catalogCode = "020";
        }else if("jdcl".equals(listStr)){
            catalogCode = "030";
        }else if("xlxw".equals(listStr)){
            catalogCode = "041";
        }else if("zyzg".equals(listStr)){
            catalogCode = "042";
        }else if("kysp".equals(listStr)){
            catalogCode = "043";
        }else if("pxcl".equals(listStr)){
            catalogCode = "044";
        }else if("zscl".equals(listStr)){
            catalogCode = "050";
        }else if("dtcl".equals(listStr)){
            catalogCode = "060";
        }else if("jlicl".equals(listStr)){
            catalogCode = "070";
        }else if("cfcl".equals(listStr)){
            catalogCode = "080";
        }else if("gzcl".equals(listStr)){
            catalogCode = "091";
        }else if("rmcl".equals(listStr)){
            catalogCode = "092";
        }else if("cgcl".equals(listStr)){
            catalogCode = "093";
        }else if("dbdh".equals(listStr)){
            catalogCode = "094";
        }else if("qtcl".equals(listStr)){
            catalogCode = "100";
        }
        return catalogCode;
    }

}
