package com.dragonsoft.EasyTest.user.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "用户接口")
@Controller
public class UserController {



    @ApiOperation(value = "欢迎" ,  notes="欢迎页面")
    @RequestMapping("/hello")
    public String hello() {
        return "/user/ycjjRlyjPage";
    }

    @ApiOperation(value = "发送mq请求" ,  notes="发送mq请求")
    @ResponseBody
    @RequestMapping("/sendMq")
    public void send(@RequestBody String data) {
        System.out.println(data);
        try {
            testQueueProducer(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生产者队列
     *
     * @throws Exception
     */
    public static void testQueueProducer(String msg) throws Exception {
        //1、创建一个连接工厂对象，需要指定服务的ip及端口。
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://20.20.30.98:61616");
        //2、使用工厂对象创建一个Connection对象。
        Connection connection = connectionFactory.createConnection();
        //3、开启连接，调用Connection对象的start方法。
        connection.start();
        //4、创建一个Session对象。
        //第一个参数：是否开启事务。如果true开启事务，第二个参数无意义。一般不开启事务false。
        //第二个参数：应答模式。自动应答或者手动应答。一般自动应答。
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5、使用Session对象创建一个Destination对象。两种形式queue、topic，现在应该使用queue
        Queue queue = session.createQueue("iflytek.warn");
        //6、使用Session对象创建一个Producer对象。
        MessageProducer producer = session.createProducer(queue);
        //7、创建一个Message对象，可以使用TextMessage。
        /*TextMessage textMessage = new ActiveMQTextMessage();
		textMessage.setText("hello Activemq");*/
        //解析本地json文件转化为json对象
        JSONObject obj = JSONObject.parseObject(msg);
        obj.getJSONObject("body").put("warnTime", System.currentTimeMillis());
        TextMessage textMessage = session.createTextMessage(obj.toString());
        producer.send(textMessage);

        //9、关闭资源
        producer.close();
        session.close();
        connection.close();
    }

    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add("11");
        list.add("22");
        list.add("33");
        list.add("44");
        list.add("55");
        list.add("23");
//        list.forEach(v->{
//            System.out.println(v);
//        });
        list.stream()
                .sorted()
                .filter((s)->s.endsWith("3"))
                .forEach(System.out::println);
        System.out.println("<==========----------------------=========>");
        Map<String,String> map=new HashMap<>();
        map.put("name","112");
        map.put("age","113");
        map.put("sex","114");
        map.put("addr","115");
        map.forEach((k,v)->{
            System.out.println(k+":"+v);
        });

    }
}
