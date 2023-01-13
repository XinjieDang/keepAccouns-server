package com.dxj.controller;
import com.dxj.bo.ResponseInfo;
import com.dxj.dto.LoginRequest;
import com.dxj.model.User;
import com.dxj.service.db.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

@RestController
@Api(tags = "用户模块")
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;
    @PostMapping("/login")
    @ApiOperation(value = "用户登录认证")
    public ResponseInfo login(@RequestBody LoginRequest loginRequest){
        log.info("接收参数{}",loginRequest);
        return userService.login(loginRequest);
    }
    @GetMapping("/sign-up")
    @ApiOperation(value = "用户注册")
    public ResponseInfo signUp(@RequestBody User user){
        userService.save(user);
        return ResponseInfo.success();
    }
    @GetMapping("/logout")
    @ApiOperation(value = "退出登录")
    public ResponseInfo logout(){
        return userService.logout();
    }

}
