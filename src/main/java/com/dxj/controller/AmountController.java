package com.dxj.controller;

import com.dxj.bo.ResponseInfo;
import com.dxj.dto.QueryAmountRequest;
import com.dxj.model.Amount;
import com.dxj.service.db.AmountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@Api(tags = "记账模块")
@RequestMapping("/amount")
public class AmountController {
    @Resource
    private AmountService amountService;
    @PostMapping("/find")
    @ApiOperation(value = "查询记账信息")
    public ResponseInfo find(@RequestBody(required = false) QueryAmountRequest queryAmountRequest){
        log.info("接收参数{}",queryAmountRequest);
        return amountService.findAll(queryAmountRequest);
    }
    @PostMapping("/add")
    @ApiOperation(value = "添加记账信息")
    public ResponseInfo add(@RequestBody Amount amount){
        log.info("接收参数{}",amount);
        return ResponseInfo.success( amountService.save(amount));
    }
}
