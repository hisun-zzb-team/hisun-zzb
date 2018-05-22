package com.hisun.saas.zzb.dzda.mlcl.exchange;

        import com.aspose.cells.*;
        import com.hisun.saas.sys.exchange.AbsExcelExchange;
        import com.hisun.util.AsposeLicenseUtil;
        import com.hisun.util.JacksonUtil;
        import org.json.JSONObject;
        import org.springframework.stereotype.Component;

        import java.util.*;

/**
 * Created by 60514 on 2018/5/18.
 */
@Component
public class MlxxExcelExchange extends AbsExcelExchange{
        public void setLines(Object object, String tmplateFile, String destFile, int xml, int dml,Map<String,Object> map) throws Exception {
                setLines(JacksonUtil.nonDefaultMapper().toJson(object), tmplateFile, destFile,xml, dml,map);
        }

        public void setLines(String json, String tmplateFile, String destFile, int xml, int dml,Map<String,Object> map) throws Exception {
                JSONObject jsonObject = new JSONObject(json);
                Map<String,Integer> fieldMap = new HashMap<>();
                AsposeLicenseUtil.newInstance().init();
                int to=0;//已添加行数
                int rows=0;
                Workbook workbook = read(tmplateFile);
                WorksheetCollection worksheets = workbook.getWorksheets();
                //获取模板插入数据行数位置
                for (Iterator<Worksheet> iterator = worksheets.iterator(); iterator.hasNext(); ) {
                        Worksheet worksheet = iterator.next();
                        Cells cells = worksheet.getCells();
                        for (Iterator<Cell> cellIterator = (cells.iterator()); cellIterator.hasNext(); ) {
                                Cell cell = cellIterator.next();
                                String value = cell.getStringValue();
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
                                                                        to+=dml;
                                                                }else {
                                                                        to+=(xml+1);
                                                                }
                                                        }
                                                }

                                                if(row!=rows){
                                                        rows = row;
                                                        if(size==0){
                                                                to+=(dml-1);
                                                        }
                                                }else if(size==0){
                                                }
                                        }
                                }
                        }
                }

                //根据获得的数据位置设置模板格式
                Map<String,Integer> linkMap = sortMap(fieldMap);
                for (Iterator<Worksheet> iterator = worksheets.iterator(); iterator.hasNext(); ) {
                        Worksheet worksheet = iterator.next();
                        Cells cells = worksheet.getCells();

                        for(Map.Entry<String, Integer> mapEntry : linkMap.entrySet()){
                                System.out.println("key:"+mapEntry.getKey()+"  value:"+mapEntry.getValue());
                                String field = mapEntry.getKey();
                                int row = mapEntry.getValue();
                                Integer size = (Integer) map.get(field);
                                for (int i = 0; i < size; i++) {
                                        if(i==size-1){
                                                insertRows(cells,row + 1 +i + (i>0?xml*i:0),dml);
                                        }else {
                                                insertRows(cells,row + 1 +i + (i>0?xml*i:0),xml+1);
                                        }
                                }

                                if(size==0){
                                        insertRows(cells,row+1,dml-1);
                                }
                        }
                }

                //插入数据
                for (Iterator<Worksheet> iterator = worksheets.iterator(); iterator.hasNext(); ) {
                        Worksheet worksheet = iterator.next();
                        Cells cells = worksheet.getCells();
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
                                                                cells.get(row + i + (i > 0 ? xml * i : 0), column).setValue(realValue);
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

                workbook.save(destFile);
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
                        Cells tpltCells = tpltWorksheet.getCells();
                        for (Iterator<Cell> tpltCellIterator = tpltCells.iterator(); tpltCellIterator.hasNext(); ) {
                                Cell tpltCell = tpltCellIterator.next();
                                String value = tpltCell.getStringValue();
                                List<String> fields = this.parseField(value);
                                String realValue = "";
                                if (fields != null) {
                                        for (String field : fields) {
                                                if (isListField(value)) {
                                                        Cells srcCells = srcWorksheets.get(sheetIndex).getCells();
                                                        setListValue(jsonObject,field,tpltCell,srcCells);
                                                } else if (isImageField(value)) {

                                                } else {
                                                        realValue = srcWorksheets.get(sheetIndex).getCells().get(tpltCell.getRow(), tpltCell.getColumn()).getStringValue();
                                                        setValue(jsonObject,field,realValue);
                                                }
                                        }
                                }
                        }
                        sheetIndex++;
                }
                return jsonObject;
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

}
