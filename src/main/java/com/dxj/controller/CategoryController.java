package com.dxj.controller;

import com.dxj.bo.ResponseInfo;
import com.dxj.model.Category;
import com.dxj.service.db.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
@Api(tags = "分类模块")
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService service;
    @ApiOperation(value = "添加分类信息")
    @PostMapping("/add")
    public ResponseInfo add(@RequestBody Category category){
        service.save(category);
        return ResponseInfo.success();
    }

    @ApiOperation(value = "查询全部分类信息")
    @PostMapping("/find")
    public ResponseInfo find(){
        return service.findAll();
    }
}
