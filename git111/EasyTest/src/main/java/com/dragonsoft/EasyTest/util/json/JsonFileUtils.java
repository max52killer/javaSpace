package com.dragonsoft.EasyTest.util.json;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * json工具类
 */
public class JsonFileUtils {
    /**
     * 读取数组格式的json文件
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    public static String readArrJsonFile(String filePath) throws FileNotFoundException {

        BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath));
        Gson gson=new Gson();
        JsonArray js=gson.fromJson(bufferedReader,JsonArray.class);

        return js.toString();
    }

    /**
     * 读取对象格式的json文件
     * @param filePath
     * @return
     * @throws FileNotFoundException
     */
    public static String readJsonFile(String filePath) throws FileNotFoundException {

        BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath));
        Gson gson=new Gson();
        JsonObject js=gson.fromJson(bufferedReader,JsonObject.class);

        return js.toString();
    }
    public static void main(String[] args) {
        try {
            System.out.println(readArrJsonFile("E:\\20190213\\test.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
