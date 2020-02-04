package com.dragonsoft.EasyTest.util.file;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dragonsoft.EasyTest.enums.CharsetEnum;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.thymeleaf.util.StringUtils;

import java.io.*;
import java.nio.charset.Charset;

public class FileUtils {

    /**
     * 传入文件绝对路径读取文件中的数据信息    返回String串
     *
     * @param fileName
     * @return
     */
    public static String readFileContent(String fileName) {
//        String userPath = System.getProperty("user.dir");
        StringBuffer content = new StringBuffer();
        BufferedReader reader = null;
        try {
//            reader = new BufferedReader(new FileReader(new File(userPath + "/" + fileName)));
            reader = new BufferedReader(new FileReader(new File(fileName)));
            String line = null;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return content.toString();
    }
    public static JSONObject parseJson(String str) {
        JSONObject json = JSON.parseObject(str);
        return json;
    }

    /**
     * 使用缓存字节流写入文件（推荐使用）
     * @param inf
     * @param outf
     * @param charset 转换的字符集格式  默认GBK
     * @throws IOException
     */
    public static void writeFile(String inf, String outf,String charset) throws IOException {
        //读取文件(缓存字节流)
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(inf));
        //写入相应的文件
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outf));
        //读取数据
        //一次性取多少字节
        byte[] bytes = new byte[2048];
        //接受读取的内容(n就代表的相关数据，只不过是数字的形式)
        int n = -1;
        //循环取出数据
        while ((n = in.read(bytes, 0, bytes.length)) != -1) {
            //转换成字符串
            if(StringUtils.isEmpty(charset)){
                charset= CharsetEnum.GBK.getCode();
            }
            String str = new String(bytes, 0, n, "GBK");
//            System.out.println(str);
            //写入相关文件
            out.write(bytes, 0, n);
        }
        //清楚缓存
        out.flush();
        //关闭流
        in.close();
        out.close();
    }

    /**
     * InputStream、OutputStream（字节流）
     * @param inF
     * @param outF
     * @throws Exception
     */
    public static void writeFile_(String inF, String outF) throws IOException {
        //读取文件(字节流)
        InputStream in = new FileInputStream(inF);
        //写入相应的文件
        OutputStream out = new FileOutputStream(outF);
        //读取数据
        //一次性取多少字节
        byte[] bytes = new byte[2048];
        //接受读取的内容(n就代表的相关数据，只不过是数字的形式)
        int n = -1;
        //循环取出数据
        while ((n = in.read(bytes, 0, bytes.length)) != -1) {
            //转换成字符串
            // 这里可以实现字节到字符串的转换，比较实用
            String str = new String(bytes, 0, n, "GBK");

//            System.out.println(str);
            //写入相关文件
            out.write(bytes, 0, n);
        }
        //关闭流
        in.close();
        out.close();
    }

    /**
     * BufferedReader、BufferedWriter(缓存流，提供readLine方法读取一行文本)
     * @param inf
     * @param outf
     * @param charset
     * @throws Exception
     */
    public static void write_buffer(String inf,String outf,String charset)throws IOException{
        //读取文件(字符流)
        if(StringUtils.isEmpty(charset)){
            charset=CharsetEnum.GBK.getCode();
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("d:\\1.txt"),charset));//这里主要是涉及中文
        //BufferedReader in = new BufferedReader(new FileReader("d:\\1.txt")));
        //写入相应的文件
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:\\2.txt"),charset));
        //BufferedWriter out = new BufferedWriter(new FileWriter("d:\\2.txt"))；
        //读取数据
        //循环取出数据
        String str = null;
        while ((str = in.readLine()) != null) {
            System.out.println(str);
            //写入相关文件
            out.write(str);
            out.newLine();
        }

        //清楚缓存
        out.flush();
        //关闭流
        in.close();
        out.close();
    }

    /**
     * Reader、PrintWriter（PrintWriter这个很好用，在写数据的同事可以格式化）
     * @param inf
     * @param outf
     * @param charset
     * @throws Exception
     */
    public static void writeFilePrint(String inf,String outf,String charset) throws IOException {
        //读取文件(字节流)
        if(StringUtils.isEmpty(charset)){
            charset=CharsetEnum.GBK.getCode();
        }
        Reader in = new InputStreamReader(new FileInputStream(inf),charset);
        //写入相应的文件
        PrintWriter out = new PrintWriter(new FileWriter(outf));
        //读取数据
        //循环取出数据
        byte[] bytes = new byte[1024];
        int len = -1;
        while ((len = in.read()) != -1) {
            System.out.println(len);
            //写入相关文件
            out.write(len);
        }
        //清楚缓存
        out.flush();
        //关闭流
        in.close();
        out.close();
    }

    public static void main(String[] args) {
        String ss = readFileContent("E:\\20190213\\test1.json");
        System.out.println(ss);
        JSONObject json = JSON.parseObject(ss);
        System.out.println(json);
        System.out.println("------------华丽分割线---------------");
        try {
            writeFilePrint("E:\\20190213\\test1.json","E:\\20190213\\test4.json","GBK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
