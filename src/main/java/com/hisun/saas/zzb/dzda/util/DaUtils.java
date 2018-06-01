package com.hisun.saas.zzb.dzda.util;

import com.hisun.saas.sys.admin.dictionary.service.DictionaryItemService;
import com.hisun.saas.zzb.dzda.a38.vo.WrongExcelColumn;
import com.hisun.saas.zzb.dzda.mlcl.vo.E01Z1Vo;
import com.hisun.util.StringUtils;

import javax.annotation.Resource;
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
