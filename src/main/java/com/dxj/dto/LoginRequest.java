package com.dxj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户登录请求dto
 */
@Data
@AllArgsConstructor
public class LoginRequest {
    private String userName;
    public String passWord;
    private Boolean rememberMe;
}
