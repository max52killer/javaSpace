package com.dragonsoft.EasyTest.freemarker;

import com.dragonsoft.EasyTest.util.IDCardUtil;
import com.dragonsoft.EasyTest.util.random.RandomUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: EasyTest
 * @description: freemarker模板生成工具类
 * @author: songzm
 * @create: 2019-11-28 09:03
 **/
public class FreemarkerUtils {
    private Configuration configuration = null;

    public FreemarkerUtils() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
    }

    /**
     * 通过模板构造doc文档
     * @param datas
     * @param fileName
     * @return
     * @throws UnsupportedEncodingException
     */
    public String createDoc(Map<String, Object> datas, String fileName) throws UnsupportedEncodingException {
        Template t = null;
        Writer out = null;
        FileOutputStream fos = null;
        try {
            configuration.setDirectoryForTemplateLoading(new File(this.getClass().getResource("/").getFile()+"template"));
            //test.ftl为要装载的模板
            t = configuration.getTemplate("template.ftl");
            //输出文档路径及名称
            File outFile = new File(fileName);
            fos = new FileOutputStream(outFile);
            OutputStreamWriter oWriter = new OutputStreamWriter(fos, "UTF-8");
            //这个地方对流的编码不可或缺，使用main（）单独调用时，应该可以，但是如果是web请求导出时导出后word文档就会打不开，并且包XML文件错误。主要是编码格式不正确，无法解析。
            //out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
            out = new BufferedWriter(oWriter);
            t.process(datas, out);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return out.toString();
    }

    public Map<String,Object> makeDatas(){
        Map<String,Object> exportDatas=new HashMap<>();
        List<Map<String,Object>> lists=new ArrayList<Map<String,Object>>();
        String[] entitys=new String[]{"QB研发一部","QB研发二部","JZ组","大数据小组","第五组"};
        for(String tableName:entitys){
            //2、获取该数据源下指定表的字段信息
            List<Map<String,Object>> dataFields=getDataFields(tableName);
            //3、构造结果返回信息
            Map<String,Object> data=new HashMap<String,Object>();
            data.put("groupName",tableName);
            data.put("tableFields",dataFields);
            lists.add(data);
        }
        exportDatas.put("exportDatas",lists);


        return exportDatas;
    }
    private static List<Map<String,Object>> getDataFields(String entityname){
        List<Map<String,Object>> mapList=new ArrayList<>();
        for(int i=0;i<RandomUtil.randomMaxMinNum(1,10);i++){
            Map<String,Object> map=new HashMap<>();
            String sfzh=RandomUtil.getIdNo(true);
            map.put("index",i+1);
            map.put("sfzh",sfzh );
            map.put("name",RandomUtil.randomName());
            map.put("sex", IDCardUtil.getSex(sfzh));
            map.put("age",IDCardUtil.getAge(sfzh));
            map.put("mz",RandomUtil.randomMz());

            mapList.add(map);
        }
        return mapList;
    }

    public static void main(String[] args) {
        FreemarkerUtils utils=new FreemarkerUtils();
        try {
            System.out.println(utils.createDoc(utils.makeDatas(),"D://xxx01.doc"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
