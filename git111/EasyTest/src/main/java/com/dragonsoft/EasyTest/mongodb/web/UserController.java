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
@Api(description = "用户信息接口")
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private UserService userService;

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
    @ApiOperation(value = "用户登陆接口" ,  notes="用户登陆接口")
    @PostMapping("/loginUser.do")
    public Result loginUser(@RequestBody TUser user){
        return userService.login(user);
    }
    @ApiOperation(value = "删除指定id的用户" ,  notes="删除指定id的用户")
    @GetMapping("/delUserById/{id}")
    public Result delUserById(@PathVariable(name="id") String id){
        userService.deleteById(id);
        return new Result(StatusCodeEnum.SUCCESS.getCode(),"删除成功");
    }
    @ApiOperation(value = "更新单个用户信息" ,  notes="更新表码信息数据")
    @PostMapping("/updateUser.do")
    public Result updateUser(@RequestBody TUser user){
        //1、先将之前的用户数据删除
        userService.deleteById(user.get_id());
        TUser result=userService.save(user);
        return new Result(StatusCodeEnum.SUCCESS.getCode(),"更新成功",result);
    }
    @ApiOperation(value = "更新单个用户信息" ,  notes="更新表码信息数据")
    @GetMapping("/current.do")
    public Result current(){
        return userService.current();
    }
}
