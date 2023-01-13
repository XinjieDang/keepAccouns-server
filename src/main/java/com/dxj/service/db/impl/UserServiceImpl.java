package com.dxj.service.db.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dxj.bo.ResponseInfo;
import com.dxj.dto.LoginRequest;
import com.dxj.mapper.UserMapper;
import com.dxj.model.LoginUser;
import com.dxj.model.User;
import com.dxj.security.common.utils.JwtTokenUtils;
import com.dxj.service.db.UserService;
import com.dxj.utils.RedisUtils;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public ResponseInfo login(LoginRequest loginRequest) {
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassWord());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过，给出对应的提示
        if (Objects.isNull(authenticate)) {
            throw new BadCredentialsException("用户名或者密码错误！");
        }
        //如果认证通过，使用 userId 生成一个jwt
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        // JwtTokenUtil
        String jwt = jwtTokenUtils.generateToken(loginUser, userId);
        //将用户信息存入Redis
        redisUtils.setCacheObject("login:" + userId, loginUser);
        //将jwt 返回
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return ResponseInfo.success(map);
    }

    @Override
    public ResponseInfo logout() {
        //获取SecurityContextHolder中的用户id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            String userId = loginUser.getUser().getId().toString();
            //删除redis中的值
            redisUtils.deleteObject("login:" + userId);
            return ResponseInfo.success("退出成功");
        }
        // UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext();
        return ResponseInfo.fail("退出失败..");
    }
}
