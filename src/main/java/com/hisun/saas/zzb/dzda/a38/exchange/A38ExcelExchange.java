package com.hisun.saas.zzb.dzda.a38.exchange;

import com.aspose.cells.*;
import com.hisun.saas.sys.exchange.AbsExcelExchange;
import com.hisun.util.AsposeLicenseUtil;
import com.hisun.util.JacksonUtil;
import com.hisun.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by 60514 on 2018/5/18.
 */
@Component
public class A38ExcelExchange extends AbsExcelExchange{
    public void toExcel(Object object, String tmplateFile, String destFile, int xml, int dml,Map<String,Object> map) throws Exception {
        toExcel(JacksonUtil.nonDefaultMapper().toJson(object), tmplateFile, destFile,xml, dml,map);
    }

    public void toExcel(String json, String tmplateFile, String destFile,int xml, int dml,Map<String,Object> map) throws Exception {
        JSONObject jsonObject = new JSONObject(json);
        JSONObject jbxx = (JSONObject) jsonObject.get("jbxxA38Vo");
        JSONObject mlxx = (JSONObject) jsonObject.get("e01Z1ExcelVo");
        JSONObject zwbd = (JSONObject) jsonObject.get("zwbdA38Vo");
        JSONArray gzbd = (JSONArray) jsonObject.get("gzbdList");
        JSONArray e01z2 = (JSONArray) jsonObject.get("e01z2Vos");
        AsposeLicenseUtil.newInstance().init();
        Workbook workbook = read(tmplateFile);
        WorksheetCollection worksheets = workbook.getWorksheets();
        for (Iterator<Worksheet> iterator = worksheets.iterator(); iterator.hasNext(); ) {
            Worksheet worksheet = iterator.next();
            if("Sheet1".equals(worksheet.getCodeName())){
                toExcels(worksheet,jbxx);
            }else if("Sheet2".equals(worksheet.getCodeName())){
                setLines(xml, dml,map,worksheet,mlxx);
            }else if("Sheet3".equals(worksheet.getCodeName())){
                toExcels(worksheet,zwbd);
            }else if("Sheet4".equals(worksheet.getCodeName())){
                toExcelByManyJson(worksheet,gzbd);
            }else if("Sheet5".equals(worksheet.getCodeName())){
                toExcelByManyJson(worksheet,e01z2);
            }
        }
        workbook.save(destFile);

    }

    public void toExcels(Worksheet worksheet,JSONObject jsonObject){
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
                    } else if (isImageField(value)) {

                    } else {
                        realValue = getValue(jsonObject, field);
                        cell.setValue(realValue);
                    }
                }
            }
        }
    }

    public void toExcelByManyJson(Worksheet worksheet,JSONArray gzbd) throws Exception {
        List<JSONObject> jsonObjects = new ArrayList<>();
        for(int i=0;i<gzbd.length();i++){
            JSONObject jsonObject = gzbd.getJSONObject(i);
            jsonObjects.add(jsonObject);
        }
        Cells cells = worksheet.getCells();
        for (Iterator<Cell> cellIterator = cells.iterator(); cellIterator.hasNext(); ) {
            Cell cell = cellIterator.next();
            String value = cell.getStringValue();
            List<String> fields = this.parseField(value);
            String realValue = "";
            if (fields != null) {
                for (String field : fields) {
                    if (isListField(value)) {
                    } else if (isImageField(value)) {
                    } else {
                        int row = cell.getRow();
                        int column = cell.getColumn();
                        for(int i = 0;i<jsonObjects.size();i++){
                            realValue = getValue(jsonObjects.get(i), field);
                            cells.get(row+i,column).setValue(realValue);
                        }
                    }
                }
            }
        }
    }

    public void setLines(int xml, int dml,Map<String,Object> map,Worksheet worksheet,JSONObject jsonObject) throws Exception {
        String tmplCellValue = "";
        int tmplCellValueNumber = 0;
        Map<String,Object> tmplCell = new HashMap<>();
        Map<String,Integer> fieldMap = new HashMap<>();
        AsposeLicenseUtil.newInstance().init();
        int to=0;//已添加行数
        int rows=0;
        //获取模板插入数据行数位置
        Cells cells = worksheet.getCells();
        for (Iterator<Cell> cellIterator = (cells.iterator()); cellIterator.hasNext(); ) {
            Cell cell = cellIterator.next();
            String value = cell.getStringValue();
            tmplCell =valueAndNum(tmplCellValue,tmplCellValueNumber,value);
            tmplCellValue = (String) tmplCell.get("tmplCellValue");
            tmplCellValueNumber = (int) tmplCell.get("tmplCellValueNumber");
            List<String> fields = this.parseField(value);
            if (fields != null) {

                for (String field : fields) {

                    int dot = field.indexOf(".");
                    String listFieldName = field.substring(0, dot);
                    Integer size = (Integer) map.get(listFieldName);
                    int row = cell.getRow();
                    if(row!=rows){
                        fieldMap.put(listFieldName,(row+to));
                    }
                    for (int i = 0; i < size; i++) {
                        if(row!=rows){

                            if(i==size-1){
                                if(tmplCellValueNumber==41||tmplCellValueNumber==42
                                        ||tmplCellValueNumber==43||tmplCellValueNumber==44
                                        ||tmplCellValueNumber==91||tmplCellValueNumber==92
                                        ||tmplCellValueNumber==93||tmplCellValueNumber==94){
                                    to+=xml;
                                }else {
                                    to+=dml;
                                }
                            }else {
                                to++;
                            }
                        }
                    }

                    if(row!=rows){
                        rows = row;
                        if(size==0){
                            if(tmplCellValueNumber==41||tmplCellValueNumber==42
                                    ||tmplCellValueNumber==43||tmplCellValueNumber==44
                                    ||tmplCellValueNumber==91||tmplCellValueNumber==92
                                    ||tmplCellValueNumber==93||tmplCellValueNumber==94){
                                to+=(xml-1);
                            }else {
                                to+=(dml-1);
                            }
                        }
                    }else if(size==0){
                    }
                }
            }
        }

        //根据获得的数据位置设置模板格式
        Map<String,Integer> linkMap = sortMap(fieldMap);

//        Cells cells = worksheet.getCells();

        for(Map.Entry<String, Integer> mapEntry : linkMap.entrySet()){
            String field = mapEntry.getKey();
            int row = mapEntry.getValue();
            Integer size = (Integer) map.get(field);
            for (int i = 0; i < size; i++) {
                if(i==size-1){
                    if("xlxw".equals(field)||"zyzg".equals(field)
                            ||"kysp".equals(field)||"pxcl".equals(field)
                            ||"gzcl".equals(field)||"rmcl".equals(field)
                            ||"cgcl".equals(field)||"dbdh".equals(field)){
                        insertRows(cells,row + 1 +i,xml);
                    }else{
                        insertRows(cells,row + 1 +i,dml);
                    }
                }else {
                    insertRows(cells,row + 1 +i ,1);
                }
            }

            if(size==0){
                if("xlxw".equals(field)||"zyzg".equals(field)
                        ||"kysp".equals(field)||"pxcl".equals(field)
                        ||"gzcl".equals(field)||"rmcl".equals(field)
                        ||"cgcl".equals(field)||"dbdh".equals(field)){
                    insertRows(cells,row + 1 ,xml-1);
                }else{
                    insertRows(cells,row+1,dml-1);
                }
            }
        }

        //插入数据
//        Cells cells = worksheet.getCells();
        for (Iterator<Cell> cellIterator = (cells.iterator()); cellIterator.hasNext(); ) {
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
                            cells.get(row + i , column).setValue(realValue);
                        }
                        if (row != rows) {
                            rows = row;
                            if (size == 0) {
                                cell.setValue("");
                            }
                        } else if (size == 0) {
                            cell.setValue("");
                        }
                    }
                }
            }
        }
    }

    public Map<String , Object> valueAndNum(String tmplCellValue, int tmplCellValueNumber, String value){
        Map<String , Object> tmpl= new HashMap<>();
        if("一".equals(value)){
            tmplCellValueNumber = 1;
            tmplCellValue = value;
        }else if("二".equals(value)){
            tmplCellValueNumber = 2;
            tmplCellValue = value;
        }else if("三".equals(value)){
            tmplCellValueNumber = 3;
            tmplCellValue = value;
        }else if("四".equals(value)){
            tmplCellValueNumber = 4;
            tmplCellValue = value;
        }else if("五".equals(value)){
            tmplCellValueNumber = 5;
            tmplCellValue = value;
        }else if("六".equals(value)){
            tmplCellValueNumber = 6;
            tmplCellValue = value;
        }else if("七".equals(value)){
            tmplCellValueNumber = 7;
            tmplCellValue = value;
        }else if("八".equals(value)){
            tmplCellValueNumber = 8;
            tmplCellValue = value;
        }else if("九".equals(value)){
            tmplCellValueNumber = 9;
            tmplCellValue = value;
        }else if("十".equals(value)){
            tmplCellValueNumber = 10;
            tmplCellValue = value;
        }

        if("(一)".equals(value)&&tmplCellValueNumber == 4){
            tmplCellValueNumber = 41;
            tmplCellValue = value;
        }else if("(二)".equals(value)&&tmplCellValueNumber == 41){
            tmplCellValueNumber = 42;
            tmplCellValue = value;
        }else if("(三)".equals(value)&&tmplCellValueNumber == 42){
            tmplCellValueNumber = 43;
            tmplCellValue = value;
        }else if("(四)".equals(value)&&tmplCellValueNumber == 43){
            tmplCellValueNumber = 44;
            tmplCellValue = value;
        }

        if("(一)".equals(value)&&tmplCellValueNumber == 9){
            tmplCellValueNumber = 91;
            tmplCellValue = value;
        }else if("(二)".equals(value)&&tmplCellValueNumber == 91){
            tmplCellValueNumber = 92;
            tmplCellValue = value;
        }else if("(三)".equals(value)&&tmplCellValueNumber == 92){
            tmplCellValueNumber = 93;
            tmplCellValue = value;
        }else if("(四)".equals(value)&&tmplCellValueNumber == 93){
            tmplCellValueNumber = 94;
            tmplCellValue = value;
        }

        tmpl.put("tmplCellValue",tmplCellValue);
        tmpl.put("tmplCellValueNumber",tmplCellValueNumber);
        return tmpl;
    }

    public Map<String,Integer> sortMap(Map<String,Integer> fieldMap){

        Set<Map.Entry<String,Integer>> mapEntries = fieldMap.entrySet();

        List<Map.Entry<String, Integer>> result = new LinkedList<>(mapEntries);
        Collections.sort(result, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {

                return o1.getValue().compareTo(o2.getValue()) ;
            }
        });

        Map<String,Integer> linkMap = new LinkedHashMap<>();
        for(Map.Entry<String,Integer> newEntry :result){
            linkMap.put(newEntry.getKey(), newEntry.getValue());
        }
        return linkMap;
    }


    public Object fromExcel(Class clazz, String tmplateFile, String srcFile) throws Exception {
        JSONObject jsonObject = fromExcel(tmplateFile,srcFile);
        return JacksonUtil.nonDefaultMapper().fromJson(jsonObject.toString(), clazz);
    }

    public JSONObject fromExcel(String tmplateFile, String srcFile) throws Exception {
        JSONObject jsonObject = new JSONObject();
        AsposeLicenseUtil.newInstance().init();
        //模板Excel
        Workbook tpltWorkbook = read(tmplateFile);
        WorksheetCollection tpltWorksheets = tpltWorkbook.getWorksheets();
        //数据Excel
        Workbook srcWorkbook = read(srcFile);
        WorksheetCollection srcWorksheets = srcWorkbook.getWorksheets();
        int sheetIndex = 0;
        for (Iterator<Worksheet> iterator = tpltWorksheets.iterator(); iterator.hasNext(); ) {
            Worksheet tpltWorksheet = iterator.next();
            if("Sheet1".equals(tpltWorksheet.getCodeName())){
                jsonObject.put("jbxxA38Vo",fromExcel(tpltWorksheet,srcWorksheets.get(sheetIndex)));
            }else if("Sheet2".equals(tpltWorksheet.getCodeName())){
                jsonObject.put("e01Z1ExcelVo",fromExcelForMlxx(tpltWorksheet,srcWorksheets.get(sheetIndex)));
            }else if("Sheet3".equals(tpltWorksheet.getCodeName())){
                jsonObject.put("zwbdA38Vo",fromExcel(tpltWorksheet,srcWorksheets.get(sheetIndex)));
            }else if("Sheet4".equals(tpltWorksheet.getCodeName())){
                jsonObject.put("gzbdList",fromExcel2ManyPojo(tpltWorksheet,srcWorksheets.get(sheetIndex)));
            }else if("Sheet5".equals(tpltWorksheet.getCodeName())){
                jsonObject.put("e01z2Vos",fromExcel2ManyPojo(tpltWorksheet,srcWorksheets.get(sheetIndex)));
            }
            sheetIndex++;
        }
        return jsonObject;
    }

    public Object fromExcel(Worksheet tpltWorksheet, Worksheet srcWorksheet) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Cells tpltCells = tpltWorksheet.getCells();
            for (Iterator<Cell> tpltCellIterator = tpltCells.iterator(); tpltCellIterator.hasNext(); ) {
                Cell tpltCell = tpltCellIterator.next();
                String value = tpltCell.getStringValue();
                List<String> fields = this.parseField(value);
                String realValue = "";
                if (fields != null) {
                    for (String field : fields) {
                        if (isListField(value)) {
                            Cells srcCells = srcWorksheet.getCells();
                            setListValue(jsonObject,field,tpltCell,srcCells);
                        } else if (isImageField(value)) {

                        } else {
                            realValue = srcWorksheet.getCells().get(tpltCell.getRow(), tpltCell.getColumn()).getStringValue();
                            setValue(jsonObject,field,realValue);
                        }
                    }
                }
            }
        return jsonObject;
    }

    public  List<JSONObject> fromExcel2ManyPojo(Worksheet tpltWorksheet, Worksheet srcWorksheet) throws Exception {
        List<JSONObject> jsonObjects = new ArrayList<>();
        Cells tpltCells = tpltWorksheet.getCells();
        Cells srcCells = srcWorksheet.getCells();
        for (Iterator<Cell> tpltCellIterator = tpltCells.iterator(); tpltCellIterator.hasNext(); ) {
            Cell tpltCell = tpltCellIterator.next();
            String value = tpltCell.getStringValue();
            List<String> fields = this.parseField(value);
            if (fields != null) {
                for (String field : fields) {
                    if (isListField(value)) {
                    } else if (isImageField(value)) {
                    } else {
                        setValue(jsonObjects,field,tpltCell,srcCells);
                    }
                }
            }
        }
        return jsonObjects;
    }



    public JSONObject fromExcelForMlxx(Worksheet tpltWorksheet, Worksheet srcWorksheet) throws Exception {
        String tmplCellValue = "";
        int tmplCellValueNumber = 0;
        Map<String,Object> tmplCell = new HashMap<>();
        Map<String,Object> cellMap = new HashMap<>();
        int rowNumberStart = 0;//生成list后设置值的开始位置
        int rowNumberEnd = 0;//用作生成list的开始位置
        JSONObject jsonObject = new JSONObject();
        AsposeLicenseUtil.newInstance().init();
        int sheetIndex = 0;
        Cells tpltCells = tpltWorksheet.getCells();
        for (Iterator<Cell> tpltCellIterator = tpltCells.iterator(); tpltCellIterator.hasNext(); ) {
            if(cellMap.size()>0){
                rowNumberStart = (int) cellMap.get("rowNumberStart");
                rowNumberEnd = (int) cellMap.get("rowNumberEnd");
            }
            Cell tpltCell = tpltCellIterator.next();
            String value = tpltCell.getStringValue();
            tmplCell =valueAndNum(tmplCellValue,tmplCellValueNumber,value);
            tmplCellValue = (String) tmplCell.get("tmplCellValue");//模板value 大类（一、二、三、、、） 小类（二）（三）
            tmplCellValueNumber = (int) tmplCell.get("tmplCellValueNumber");//模板number 大类（1、2、3、、、） 小类（41、42、43、、、、）
            List<String> fields = this.parseField(value);
            if (fields != null) {
                for (String field : fields) {
                    if (isListField(value)) {
                        Cells srcCells = srcWorksheet.getCells();
                        cellMap=setListValue(jsonObject,field,tpltCell,srcCells,tmplCellValue,tmplCellValueNumber,rowNumberStart,rowNumberEnd);
                    }
                }
            }
        }
        return jsonObject;
    }

    protected Map<String , Object> setListValue(JSONObject jsonObject,String field,Cell tpltCell,Cells srcCells,String tmplCellValue,int tmplCellValueNumber, int rowNumberStart, int rowNumberEnd){
        Map<String , Object> cellMap = new HashMap<>();
        String srcCellValue = "";
        int srcCellValueNumber = 0;
        Map<String,Object> srcCellMap = new HashMap<>();
        int dot = field.indexOf(".");
        String listFieldName = field.substring(0, dot);
        String fieldName = field.substring(dot + 1, field.length());
        String realValue = "";
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray(listFieldName);
        } catch (Exception ex) {
        }
        String tmplCellValueBig  = yiToOne(tmplCellValueNumber+1);//获取下一个类别
        if (jsonArray == null) {
            if(tmplCellValueNumber == 1){
                rowNumberEnd = tpltCell.getRow();
            }
            List<JSONObject> jsonObjectList = new ArrayList<>();
            for (Iterator<Cell> srcCellIterator = srcCells.iterator(); srcCellIterator.hasNext(); ) {
                Cell srcCell = srcCellIterator.next();
                String value = srcCell.getStringValue();
                srcCellMap =valueAndNum(srcCellValue,srcCellValueNumber,value);
                srcCellValueNumber = (int) srcCellMap.get("tmplCellValueNumber");//获得当前导入excel文件当前位置的类别
                if(tmplCellValueBig.equals(value)){//如果当前类别和下一个类别相等
                    if("九".equals(value)){
                        rowNumberEnd = srcCell.getRow()+1;
                        rowNumberEnd++;//为小类下移一个位置
                        break;
                    }else if("四".equals(value)){
                        rowNumberEnd = srcCell.getRow()+1;
                        rowNumberEnd++;//为小类下移一个位置
                        break;
                    }else if(tmplCellValueNumber>90&&tmplCellValueNumber>srcCellValueNumber){//当小类无法确定位置时，用当前位置类别number做第二次判断
                        if("十".equals(value)){
                            rowNumberEnd = srcCell.getRow()+1;
                            break;
                        }
                    }else{
                        rowNumberEnd = srcCell.getRow()+1;
                        break;
                    }
                }
                if (srcCell.getRow()>=rowNumberEnd
                        &&srcCell.getColumn() == tpltCell.getColumn()) {
                    if(jsonObjectList.size()==0){
                        rowNumberStart = srcCell.getRow();
                    }
                    realValue = StringUtils.trimNull2Empty(srcCell.getStringValue());
                    JSONObject jsonObject1 = new JSONObject();
                    jsonObject1.put(fieldName,realValue);
                    jsonObjectList.add(jsonObject1);

                }
            }
            jsonObject.put(listFieldName, jsonObjectList);
        } else {
            int listIndex = 0;
            for (Iterator<Cell> srcCellIterator = srcCells.iterator(); srcCellIterator.hasNext(); ) {
                Cell srcCell = srcCellIterator.next();
                if (srcCell.getRow()>=rowNumberStart
                        &&srcCell.getColumn() == tpltCell.getColumn()) {
                    if(jsonArray.length()>listIndex){
                        realValue = StringUtils.trimNull2Empty(srcCell.getStringValue());
                        jsonArray.getJSONObject(listIndex).put(fieldName,realValue);
                    }
                    listIndex++;
                }
            }
        }
        cellMap.put("rowNumberStart",rowNumberStart);
        cellMap.put("rowNumberEnd",rowNumberEnd);
        return cellMap;
    }

    public String yiToOne(int number){
        String str = "";
        switch (number){
            case 1:
                str = "一";
                break;
            case 2:
                str = "二";
                break;
            case 3:
                str = "三";
                break;
            case 4:
                str = "四";
                break;
            case 5:
                str = "五";
                break;
            case 6:
                str = "六";
                break;
            case 7:
                str = "七";
                break;
            case 8:
                str = "八";
                break;
            case 9:
                str = "九";
                break;
            case 10:
                str = "十";
                break;
            case 41:
                str = "(一)";
                break;
            case 42:
                str = "(二)";
                break;
            case 43:
                str = "(三)";
                break;
            case 44:
                str = "(四)";
                break;
            case 45:
                str = "五";
                break;
            case 91:
                str = "(一)";
                break;
            case 92:
                str = "(二)";
                break;
            case 93:
                str = "(三)";
                break;
            case 94:
                str = "(四)";
                break;
            case 95:
                str = "十";
                break;
        }
        return  str;
    }

}
