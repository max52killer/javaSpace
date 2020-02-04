package com.dragonsoft.EasyTest.util.excel;

import com.alibaba.fastjson.JSON;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class WriteExcel {
    public static void write(){
        //创建工作簿
        Workbook wb = new HSSFWorkbook();

        //创建A、B两个 Sheet 页
        Sheet sheetA = wb.createSheet("A");
        Sheet sheetB = wb.createSheet("B");

        //创建A的两个单元行
        Row row0 = sheetA.createRow(0);
        Row row1 = sheetA.createRow(1);
        //创建B的两个单元行
        Row rowB0 = sheetB.createRow(0);
        Row rowB1 = sheetB.createRow(1);

        //创建sheetA第一、二行的单元格
        Cell cell0 = row0.createCell(0);
        Cell cell10 = row1.createCell(0);
        Cell cell11 = row1.createCell(1);
        //创建sheetB第一、二行的单元格
        Cell cellB0 = rowB0.createCell(0);
        Cell cellB10 = rowB1.createCell(0);
        Cell cellB11 = rowB1.createCell(1);

        //给单元格赋值
        cell0.setCellValue("一");
        cell10.setCellValue(true);
        cell11.setCellValue(5);
        cellB0.setCellValue(3.3);
        cellB10.setCellValue("abcd");
        cellB11.setCellValue("二");

        try {
            FileOutputStream fos = new FileOutputStream("E:\\20190213\\newExcel.xls");
            wb.write(fos);
            fos.close();
            wb.close();
            System.out.println("写数据结束！");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static void writeExcel(List<Map<String,String>> datas,String fileName){
        //创建工作簿
        Workbook wb = new HSSFWorkbook();
        //创建A、B两个 Sheet 页
        Sheet sheet = wb.createSheet("sheet1");

        //获取表格长度
        int rowCount=datas.size();

        //提取第一行表头的数据  使用list中map的key作为表头
        Map<String,String> data0=datas.get(0);
        Row row0 = sheet.createRow(0);
        int k=0;
        for(String s:data0.keySet()){
            Cell cell0 = row0.createCell(k);
            cell0.setCellValue(s);
            k++;
        }
        int d=1;
        for(Map<String,String> map:datas){
            Row row = sheet.createRow(d);
            int c=0;
            for(String s1:map.keySet()){
                Cell cell = row.createCell(c);
                cell.setCellValue(map.get(s1));
                c++;
            }
            d++;
        }
        try {
            exitsFile(fileName);
            FileOutputStream fos = new FileOutputStream(fileName);
            wb.write(fos);
            fos.close();
            wb.close();
            System.out.println("表格写数据结束！");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //处理目标路径的文件是否存在   不存在则创建该文件及文件路径
    public static void exitsFile(String fileName){
        File file=new File(fileName);
        if(file.exists()){
            file.delete();
        }
        if(!file.exists()){
            try {
                File parent=file.getParentFile();
                if(!parent.exists()){
                    parent.mkdirs();
                }
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * excel生成
     * @param datas 需要导出的数据对象集合
     * @param sheetName 表格tab名称
     * @param exportMap 导出字段映射map
     * @param <T>
     * @return
     */
    public static <T> Workbook  genExcel(List<T> datas, String sheetName, String exportMap){
        //1、创建工作簿
        Workbook wb = new HSSFWorkbook();
        //2、创建 Sheet 页
        Sheet sheet = wb.createSheet(sheetName);
        //3、创建表头（提取第一行表头的数据  使用映射map中的对象的字段字段name作为作为表头）
        Row row0 = sheet.createRow(0);
        Map<String,String> objMap= JSON.parseObject(exportMap,Map.class);
        int tt=0;
        for(String key:objMap.keySet()){
            Cell cell0 = row0.createCell(tt);
            cell0.setCellValue(objMap.get(key));
            tt++;
        }
        int rr=1;
        for(T clz:datas){
            Row row=sheet.createRow(rr);
            Class clazz=clz.getClass();
            int cc=0;
            for(String key:objMap.keySet()){
                try {
                    Field field=clazz.getDeclaredField(key);
                    PropertyDescriptor pd=new PropertyDescriptor(field.getName(),clazz);
                    Method method=pd.getReadMethod();
                    //执行get方法
                    String val=(String) method.invoke(clz);
                    Cell cell = row.createCell(cc);
                    cell.setCellValue(val);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                cc++;
            }
            rr++;
        }
        //4、创建内容
        return wb;
    }

    public static void main(String[] args) {
//        write();
//        Map<String,String> map=new HashMap<>();
//        map.put("11111","aaaaaa");
//        map.put("22222","g");
//        map.put("3333333","n");
//        map.put("44444","b");
//        map.put("555555","bb");
//
//        for (String s:map.keySet()){
//            System.out.println(s);
//        }
        List<Map<String,String>> maps=new ArrayList<>();
        for(int i=0;i<4;i++){
            Map<String,String> map=new HashMap<>();
            map.put("name","李"+i);
            map.put("sex","M");
            map.put("age","2"+i);
            maps.add(map);
        }
        writeExcel(maps,"E:\\20190215\\newExcel3.xls");

    }
}
