package com.dragonsoft.EasyTest.mongodb.web;

import com.dragonsoft.EasyTest.mongodb.base.Result;
import com.dragonsoft.EasyTest.mongodb.enums.StatusCodeEnum;
import com.dragonsoft.EasyTest.mongodb.po.Person;
import com.dragonsoft.EasyTest.mongodb.po.TSysCode;
import com.dragonsoft.EasyTest.mongodb.po.TUser;
import com.dragonsoft.EasyTest.mongodb.service.PersonService;
import com.dragonsoft.EasyTest.mongodb.service.SysCodeService;
import com.dragonsoft.EasyTest.mongodb.service.UserService;
import com.dragonsoft.EasyTest.mongodb.vo.BmUpdVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: EasyTest
 * @description: web访问入口
 * @author: songzm
 * @create: 2019-12-04 13:23
 **/
@Api(description = "测试接口")
@RestController
@CrossOrigin
public class WebController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private PersonService personService;
    @Autowired
    private UserService userService;
    @Autowired
    private SysCodeService sysCodeService;

    @ApiOperation(value = "保存人员数据" ,  notes="保存人员数据")
    @PostMapping("/savePerson.do")
    public Person savePerson(@RequestBody Person person){
        return personService.save(person);
    }

    //处理用户信息
    @ApiOperation(value = "保存用户信息数据" ,  notes="保存用户信息数据")
    @PostMapping("/saveUser.do")
    public TUser saveUser(@RequestBody TUser user){
        return userService.save(user);
    }
    @ApiOperation(value = "查询用户所有信息" ,  notes="查询用户所有信息")
    @GetMapping("/queryUserList.do")
    public List<TUser> queryUserList(){
        return userService.queryList();
    }

    //处理表码
    @ApiOperation(value = "保存表码信息数据" ,  notes="保存表码信息数据")
    @PostMapping("/saveBm")
    public TSysCode saveBm(@RequestBody TSysCode code){
        return sysCodeService.save(code);
    }
    @ApiOperation(value = "查询表码所有信息" ,  notes="查询表码所有信息")
    @GetMapping("/queryBmList")
    public Result queryBmList(){
        //1、查询到所有表码
        List<TSysCode> sysCodes=sysCodeService.queryList();
        //2、循环所有表码进行归类  按照type的类型进行归类
//        Map<String,List<TSysCode>> sysCodeMap=new HashMap<>();
//        for(TSysCode code:sysCodes){
//            if(sysCodeMap.get(code)==null){
//                List<TSysCode> codes=new ArrayList<>();
//                codes.add(code);
//                sysCodeMap.put(code.getType(),codes);
//            }else{
//                sysCodeMap.get(code).add(code);
//            }
//        }
        return new Result(StatusCodeEnum.SUCCESS.getCode(),"表码查询成功",sysCodes);
    }
    @ApiOperation(value = "更新表码信息数据" ,  notes="更新表码信息数据")
    @PostMapping("/updateBm")
    public Result updateBm(@RequestBody BmUpdVo updVo){

        //1、先将之前的表码数据删除
        sysCodeService.deleteByField("type",updVo.getType());
        List<TSysCode> results=new ArrayList<>();
        //2、再将新的数据插入
        for(TSysCode sysCode:updVo.getSysCodes()){
            sysCodeService.save(sysCode);
            results.add(sysCode);
        }
        return new Result(StatusCodeEnum.SUCCESS.getCode(),"更新成功",results);
    }
    @ApiOperation(value = "获取指定表码的数据" ,  notes="获取指定表码的数据")
    @GetMapping("/getByType/{type}")
    public List<TSysCode> getByType(@PathVariable(name="type") String type){
        return sysCodeService.getByType(type);
    }

    @ApiOperation(value = "删除指定表码的数据" ,  notes="删除指定表码的数据")
    @GetMapping("/delByType/{type}")
    public Result delByType(@PathVariable(name="type") String type){
        sysCodeService.deleteByField("type",type);
        return new Result(StatusCodeEnum.SUCCESS.getCode(),"删除成功");
    }
}
