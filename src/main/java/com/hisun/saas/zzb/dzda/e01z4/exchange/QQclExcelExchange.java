package com.hisun.saas.zzb.dzda.e01z4.exchange;

import com.aspose.cells.*;
import com.hisun.saas.sys.exchange.AbsExcelExchange;
import com.hisun.util.AsposeLicenseUtil;
import com.hisun.util.JacksonUtil;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

/**
 * Created by 60514 on 2018/5/18.
 */
@Component
public class QQclExcelExchange extends AbsExcelExchange{
    public void toExcel(String json, String tmplateFile, String destFile) throws Exception {
        JSONObject jsonObject = new JSONObject(json);
        AsposeLicenseUtil.newInstance().init();
        Workbook workbook = read(tmplateFile);
        WorksheetCollection worksheets = workbook.getWorksheets();
        for (Iterator<Worksheet> iterator = worksheets.iterator(); iterator.hasNext(); ) {
            Worksheet worksheet = iterator.next();
            Cells cells = worksheet.getCells();
            for (Iterator<Cell> cellIterator = cells.iterator(); cellIterator.hasNext(); ) {
                Cell cell = cellIterator.next();
                String value = cell.getStringValue();
                List<String> fields = this.parseField(value);
                String realValue = "";
                if (fields != null) {
                    for (String field : fields) {
                        if (isListField(value)) {
                            int size = getListFieldSize(jsonObject, field);
                            int row = cell.getRow();
                            int column = cell.getColumn();
                            for (int i = 0; i < size; i++) {
                                realValue = getListValue(jsonObject, field, i);
                                cells.get(row + i, column).setValue(realValue);
                            }
                            if(size==0){
                                cell.setValue(realValue);
                            }
                        } else if (isImageField(value)) {

                        } else {
                            realValue = getValue(jsonObject, field);
                            cell.setValue(realValue);
                        }
                    }
                }
            }
        }
        workbook.save(destFile);
    }




    public void toExcel(Object object, String tmplateFile, String destFile) throws Exception {
        toExcel(JacksonUtil.nonDefaultMapper().toJson(object), tmplateFile, destFile);
    }
}
