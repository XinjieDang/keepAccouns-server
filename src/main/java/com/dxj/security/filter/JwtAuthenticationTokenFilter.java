package com.dxj.security.filter;

import com.alibaba.fastjson.JSON;
import com.dxj.model.LoginUser;
import com.dxj.security.common.constants.SecurityConstants;
import com.dxj.security.common.utils.JwtTokenUtils;
import com.dxj.utils.RedisUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * jwt自定义认证过滤器
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader(SecurityConstants.TOKEN_HEADER);
        if (!StringUtils.hasText(token) || !token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            //放行，后面过滤器会再认证
            filterChain.doFilter(request, response);
            return;
        }
        //解析token
        String tokenValue = token.replace(SecurityConstants.TOKEN_PREFIX, "");
        Claims claims = jwtTokenUtils.getClaimsFromToken(tokenValue);
        Object userId = claims.get("userId");
        //从redis里获取用户信息
        String redisKey = "login:" + userId;
        LoginUser loginUser = (LoginUser)redisUtils.getCacheObject(redisKey);
        if (Objects.isNull(loginUser)) {
            logger.error("用户未登录或登录已过期！");
            filterChain.doFilter(request, response);
            return;
        }
        //TODO 获取权限信息封装到Authentication中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        //存入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}
