package com.dragonsoft.EasyTest.util.excel;

import com.dragonsoft.EasyTest.enums.DateEnum;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReadExcel {
    public static void read(String fileName){
        try {
            InputStream is = new FileInputStream(fileName);
            POIFSFileSystem fs = new POIFSFileSystem(is);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            //遍历Sheet页
            for(int sheet=0; sheet < wb.getNumberOfSheets(); sheet++){
                HSSFSheet s = wb.getSheetAt(sheet);
                System.out.println("sheetname："+s.getSheetName());
                if(s == null){
                    continue;
                }
                //遍历row
                for(int row = 0; row <= s.getLastRowNum(); row++){
                    HSSFRow r = s.getRow(row);
                    if(r == null){
                        continue;
                    }
                    //遍历单元格 cell
                    for(int cell =0; cell <= r.getLastCellNum(); cell++){
                        HSSFCell c = r.getCell(cell);
                        if(c == null){
                            continue;
                        }
                        //第一行作为map的key  其它行作为值

                        //判断单元格数据类型
                        switch (c.getCellTypeEnum()) {
                            case BOOLEAN: // Boolean类型的处理
                                System.out.print(String.valueOf(c.getBooleanCellValue()) + " ");
                                break;
                            case NUMERIC: // 数字类型的处理
                                System.out.print(String.valueOf(c.getNumericCellValue()) + " ");
                                break;
                            default: // 其他类型则按字符串处理
                                System.out.print(String.valueOf(c.getStringCellValue()) + " ");
                                break;
                        }
                    }
//                    System.out.println("-----");
                }
            }
            if(is != null){
                is.close();
            }
            if(wb != null){
                wb.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 解析指定路径下的excel文件   将其解析成map集合  只适用于第一行作为表头 其它行作为表头字段对应内容的表格解析
     * @param fileName
     * @return
     */
    public static List<Map<String,String>> readExcel(String fileName){
        List<Map<String,String>> maps=null;
        try {
            InputStream is = new FileInputStream(fileName);
            POIFSFileSystem fs = new POIFSFileSystem(is);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            //遍历Sheet页
            for(int sheet=0; sheet < wb.getNumberOfSheets(); sheet++){
                HSSFSheet s = wb.getSheetAt(sheet);
                System.out.println("表格名称："+s.getSheetName());
                if(s == null){
                    continue;
                }
                maps=new ArrayList<>();
                //提取key   即为第一行的数据
                HSSFRow r0=s.getRow(0);
                List<String> keys=new ArrayList<>();
                for(int cc=0;cc<=r0.getLastCellNum();cc++){
                    HSSFCell c = r0.getCell(cc);
                    if(c == null){
                        continue;
                    }
                    String cVal=fetchCellType2Str(c);
                    keys.add(cVal);
                }
                //遍历row
                for(int row = 1; row <= s.getLastRowNum(); row++){
                    HSSFRow r = s.getRow(row);
                    if(r == null){
                        continue;
                    }
                    //遍历单元格 cell
                    Map<String,String> map=new HashMap<>();
                    for(int cell =0; cell <= r.getLastCellNum(); cell++){
                        HSSFCell c = r.getCell(cell);
                        if(c == null){
                            continue;
                        }
                        String cVal=fetchCellType2Str(c);
                        //判断单元格数据类型
                        map.put(keys.get(cell),cVal);
                    }
                    maps.add(map);
                }
            }
            if(is != null){
                is.close();
            }
            if(wb != null){
                wb.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return maps;
    }
    private static String fetchCellType2Str(HSSFCell c){
        String res="";
        //判断单元格的类型  返回转换后类型的字符串数据
        switch (c.getCellTypeEnum()) {
            case BOOLEAN: // Boolean类型的处理
                res=String.valueOf(c.getBooleanCellValue());
                break;
            case NUMERIC: // 数字类型的处理
                res=String.valueOf(c.getNumericCellValue());
                break;
            default: // 其他类型则按字符串处理
                res=String.valueOf(c.getStringCellValue());
                break;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(readExcel("E:\\20190215\\newExcel3.xls"));
        SimpleDateFormat sdf=new SimpleDateFormat(DateEnum.NUBMER19.getCode());
        System.out.println(sdf.format(new Date()));
    }
}
