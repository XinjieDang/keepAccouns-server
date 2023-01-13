package com.dxj.controller;

import com.dxj.bo.ResponseInfo;
import com.dxj.model.User;
import com.dxj.service.db.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;
@Slf4j
@RestController
@Api(tags = "应用测试接口")
public class AppliController {
    @Resource
    private UserService userService;
    @ApiOperation(value = "测试hello")
    @GetMapping("/test")
    public ResponseInfo test(){
        List<User> list=userService.list();
        return ResponseInfo.success(list);
    }
    @ApiOperation(value = "测试业务代码类型异常2")
    @GetMapping("/calc")
    public ResponseInfo<String> calc(Integer id) {
        try {
            // 模拟异常业务代码
            int num = 1 / id;
            log.info("计算结果numdfddfd={}", num);
            return ResponseInfo.success();
        } catch (Exception e) {
            return ResponseInfo.fail("系统异常，请联系管理员！");
        }
    }
    @GetMapping("/calc1")
    @ApiOperation(value = "测试业务代码异常2")
    public ResponseInfo<String> calc1(Integer id) {
        // 模拟异常业务代码
        int num = 1 / id;
        log.info("计算结果num={}", num);
        return ResponseInfo.success();
    }
}
