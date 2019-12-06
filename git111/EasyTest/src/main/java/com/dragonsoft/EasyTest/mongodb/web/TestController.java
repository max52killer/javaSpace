package com.dragonsoft.EasyTest.mongodb.web;

import com.alibaba.fastjson.JSON;
import com.dragonsoft.EasyTest.mongodb.base.Result;
import com.dragonsoft.EasyTest.mongodb.enums.StatusCodeEnum;
import com.dragonsoft.EasyTest.mongodb.po.Person;
import com.dragonsoft.EasyTest.mongodb.po.Test;
import com.dragonsoft.EasyTest.mongodb.service.PersonService;
import com.dragonsoft.EasyTest.mongodb.service.TestService;
import com.dragonsoft.EasyTest.user.vo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@Api(description = "测试接口")
@RestController
@CrossOrigin
public class TestController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private TestService testService;
    @Autowired
    private PersonService personService;

    @ApiOperation(value = "查询文档测试表数据" ,  notes="查询文档测试表数据")
    @GetMapping("/queryAll.do")
    public List<Test> queryAll(){
        return testService.queryAll();
    }

    @ApiOperation(value = "保存单条文档数据" ,  notes="保存单条文档数据")
    @PostMapping("/save.do")
    public Test save(@RequestBody Test test){
        return testService.save(test);
    }
    @ApiOperation(value = "修改单条文档数据" ,  notes="修改单条文档数据")
    @PostMapping("/update.do")
    public Test update(@RequestBody Test test){
        return testService.update(test);
    }
    @ApiOperation(value = "删除单条文档数据" ,  notes="删除单条文档数据")
    @PostMapping("/delete.do")
    public Result deleteById(@RequestParam("_id") String _id){
        return testService.deleteById(_id);
    }
    @ApiOperation(value = "根据指定字段查询值" ,  notes="根据指定字段查询值")
    @PostMapping("/findByTitle.do")
    public List<Test> findByTitle(@RequestParam("title") String title,@RequestParam("value") String value){
        return testService.findByTitle(title,value);
    }
    @ApiOperation(value = "删除指定字段的内容" ,  notes="删除指定字段的内容")
    @PostMapping("/deleteByField")
    public Result deleteByField(@RequestParam("title") String title,@RequestParam("value") String value){
        return testService.deleteByField(title,value);
    }

    @PostMapping("/upload.do")
    public Result upload(MultipartFile file){
        logger.debug("传入的文件参数：{}", file.getOriginalFilename());
        String pathName = "D:/dmpFile/";
        String fullFileName = pathName + UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        FileOutputStream fos = null;
        Result result=null;
        try {
            fos=new FileOutputStream(fullFileName);
            fos.write(file.getBytes());
            System.out.println("文件内容："+new String(file.getBytes(),"utf-8"));
            result=new Result(StatusCodeEnum.SUCCESS.getCode(),"文件上传成功！！！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
