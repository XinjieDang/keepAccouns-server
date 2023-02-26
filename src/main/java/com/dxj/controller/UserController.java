package com.dxj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dxj.bo.ResponseInfo;
import com.dxj.dto.LoginRequest;
import com.dxj.model.LoginUser;
import com.dxj.model.User;
import com.dxj.security.common.utils.JwtTokenUtils;
import com.dxj.service.db.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public ResponseInfo login(@RequestBody LoginRequest loginRequest) {
        log.info("接收参数{}", loginRequest);
        return userService.login(loginRequest);
    }

    @PostMapping("/signUp")
    @ApiOperation(value = "用户注册")
    public ResponseInfo signUp(@RequestBody User user) {
        log.info("接收参数{}", user);
        //判断用户名是否一致
        String userName = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUserName, user.getUserName())).getUserName();
        if (userName.isEmpty()) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode(user.getUserPassword());
            user.setUserPassword(password);
            userService.save(user);
            return ResponseInfo.success();
        }
        return ResponseInfo.fail("注册失败！请换一个用户名注册...");

    }
    @GetMapping("/getUserInfo")
    @ApiOperation(value = "获取用户信息")
    public ResponseInfo getUserInfo() {
        LoginUser currentUser = JwtTokenUtils.getCurrentLoginUser();
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getId, currentUser.getUser().getId()).select(User::getUserName, User::getSignature, User::getAvatar));
        return ResponseInfo.success(user);
    }
    @GetMapping("/updateUserInfo")
    @ApiOperation(value = "用户信息修改")
    public ResponseInfo updateUserInfo(@RequestBody User user) {
        log.info("接收参数{}", user);
        LoginUser currentUser = JwtTokenUtils.getCurrentLoginUser();
        user.setId(currentUser.getUser().getId());
        return ResponseInfo.success(userService.saveOrUpdate(user));
    }
    @GetMapping("/updatePwd")
    @ApiOperation(value = "修改密码")
    public ResponseInfo updatePwd(@RequestBody User user) {
        log.info("接收参数{}", user);
        return ResponseInfo.success("修改密码");
    }
    @GetMapping("/logout")
    @ApiOperation(value = "退出登录")
    public ResponseInfo logout() {
        return userService.logout();
    }
}
