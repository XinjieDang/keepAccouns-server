package com.dxj.service.db;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dxj.bo.ResponseInfo;
import com.dxj.dto.LoginRequest;
import com.dxj.model.User;

public interface UserService extends IService<User> {
    ResponseInfo login(LoginRequest loginRequest);

    ResponseInfo logout();
}
