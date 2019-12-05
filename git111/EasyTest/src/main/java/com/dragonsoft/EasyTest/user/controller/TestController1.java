package com.dragonsoft.EasyTest.user.controller;

import com.alibaba.fastjson.JSON;
import com.dragonsoft.EasyTest.user.vo.User;
import com.dragonsoft.EasyTest.util.random.RandomUtil;
import com.sun.deploy.net.HttpResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create by zhangkang 2019/03/05 15:29
 */
@Api(description = "测试接口")
@RestController
@CrossOrigin
public class TestController1 {

    @ApiOperation(value = "用户登陆" ,  notes="测试用户登陆接口")
    @PostMapping("/login.do")
    public String login(@RequestBody User user){
        System.out.println("登陆用户：："+user.toString());

        return "success";
    }
    @PostMapping("/upload111.do")
    public String upload(Object object){
        String str= JSON.toJSONString(object);
        System.out.println("上传：："+str);

        return "success";
    }

    @ApiOperation(value = "在京失控" ,  notes="获取在京失控数据")
    @GetMapping("/api3.do")
    public Map<String,Object> fetchZjsk(){
        Map<String,Object> map=new HashMap<>();
        List<Object> list=new ArrayList<>();
        for(int i=0;i<20;i++){
            Map<String,Object> mp=new HashMap<>();
            mp.put("area","地址"+i);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd HH:mm");
            mp.put("cjsj",getRandomData());
            mp.put("charger",getRandomName()+i);
            mp.put("level",getLevel());
            mp.put("name", RandomUtil.randomName());
            mp.put("tel","1234567891"+i);
            mp.put("url","url"+i);
            StringBuffer sb=new StringBuffer();
            mp.put("zdrsfzhm",getRandomSfzh());
            list.add(mp);
        }
        map.put("data",list);

        return map;
    }
    private static String getRandomSfzh(){
        double ran=Math.random()*20;
        System.out.println(ran);
        String[] str={"330781198509071135","330781198509076091","330781198509075515","330781198509071653","110101199003077379","110101199003075138","11010119900307889X","11010119900307133X","110101199003079251","110101199003071436","110101199003076755","110101199003070038","110101199003074610","110101199003070636","11010119900307205X","110101199003078099","110101199003070935","11010119900307563X","110101199003072973","330781198509075515","330781198509076817"};
        return str[(int)ran];
    }
    private static String getRandomName(){
//        double ran=Math.floor(Math.random()*1)+10;
        double ran=Math.random()*10;
        System.out.println(ran);
        String[] str={"张","王","周","李","杜","孙","诸葛","白","胡","宋","毛"};
        return str[(int)ran];
    }
    private static String getRandomData(){
        double ran=Math.random()*10;
        String[] str={"2019.09.01 15:20","2019.09.02 16:30","2019.09.05 16:30","2019.09.07 16:30","2019.09.05 16:30","2019.09.24 16:30","2019.09.23 16:30","2019.09.18 16:30","2019.09.12 16:30","2019.09.10 16:30","2019.09.11 08:30","2019.09.12 02:30"};
        return str[(int)ran];
    }
    private static String getLevel(){
        double ran=Math.random()*10;
        String[] str={"三级","二级","一级","四级","五级"};
        return str[(int)ran/3];
    }
    private static String getSfzh(){
        double ran=Math.random()*10;
        String[] str={"11111","22222","33333","44444","五级"};
        return str[(int)ran/3];
    }
    @ApiOperation(value = "网络舆情" ,  notes="获取在网络舆情数据")
    @GetMapping("/api1.do")
    public Map<String, Object> fetchWlyq(@RequestParam("name")String name, @RequestParam("key") String key){
        System.out.println(name+"======"+key);
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String,Object>> focus = new ArrayList<>();
        for(int i=1;i<20;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("count", 22*i);
            map.put("source","AAA"+i+"BB");
            focus.add(map);
        }
        result.put("focus", focus);
        result.put("sources", focus);
        List<Map<String,Object>> items = new ArrayList<>();
        for(int i=1;i<20;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("title", "这是一条涉警涉渝信息"+i);
            map.put("author","人民日报");
            map.put("content","就反馈三级分类司空见惯就是空的简就反馈三级" +
                    "就反馈三级分类司空见惯就是空的简历上就是看电视几点开始接口发送金卡戴珊健康的设计费就开始几点开始接口发送大嫁风尚可点击就快放暑假了" +
                    "就反馈三级分类司空见惯就是空的简历上就是看电视几点开始接口发送金卡戴珊健康的设计费就开始几点开始接口发送大嫁风尚可点击就快放暑假了"+i);
            map.put("time",System.currentTimeMillis());
            map.put("source","搜狐");
            items.add(map);
        }

        result.put("items", items);
        return result;
    }

    @ApiOperation(value = "热点舆情" ,  notes="获取在热点舆情数据")
    @GetMapping("/api2.do")
    public Map<String, Object> fetchRdyq(@RequestParam("name")String name, @RequestParam("key") String key){
        System.out.println(name+"======"+key);
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String,Object>> list = new ArrayList<>();
        for(int i=1;i<20;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("count", 22*i);
            map.put("source","AAACCC"+i);
            list.add(map);
        }
        result.put("ciyun", list);

        List<Map<String,Object>> items = new ArrayList<>();
        for(int i=1;i<20;i++){
            Map<String,Object> map = new HashMap<>();
            map.put("title", "这是一条热点舆情信息"+i);
            map.put("author","人民日报");
            map.put("content","dffffffff第三方付付付付付付付付付方式事发地点多多多多多多多多多多多多多多多多多多方式发送的范德萨方式方式是发"+i);
            map.put("time",System.currentTimeMillis());
            map.put("source","搜狐");
            items.add(map);
        }
        result.put("items", items);
        return result;
    }
    public static String getIP(String url) {
        //使用正则表达式过滤，
        String re = "((http|ftp|https)://)(([a-zA-Z0-9._-]+)|([0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}))(([a-zA-Z]{2,6})|(:[0-9]{1,4})?)";
        String str = "";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(re);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        //若url==http://127.0.0.1:9040或www.baidu.com的，正则表达式表示匹配
        if (matcher.matches()) {
            str = url;
        } else {
            String[] split2 = url.split(re);
            if (split2.length > 1) {
                String substring = url.substring(0, url.length() - split2[1].length());
                str = substring;
            } else {
                str = split2[0];
            }
        }
        return str;
    }
    public static void test111(){
        for(int i=1;i<99;i++){
            for(int j=1;j<i+1;j++){
                if(j!=i){
                    System.out.print(i+"*"+j+"="+i*j+"\t");
                }else{
                    System.out.print(i+"*"+j+"="+i*j);
                }

            }
            System.out.println("");
        }
        System.out.println("------------华丽分割线---------------");
        for(int i=1;i<10;i++){
            for(int j=1;j<i+1;j++){
                if(j!=i){
                    System.out.print(i+"+"+j+"="+(i+j)+"\t");
                }else{
                    System.out.print(i+"+"+j+"="+(i+j));
                }

            }
            System.out.println("");
        }
        System.out.println("----------------华丽分割线11111-------------------");
        for(int i=1;i<10;i++){
            for(int j=1;j<i+1;j++){
                System.out.print("*");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {

//        System.out.println(getIP("http://www.baidu.com/tyzl"));
        char c=65;
        System.out.println(c);
    }
}
