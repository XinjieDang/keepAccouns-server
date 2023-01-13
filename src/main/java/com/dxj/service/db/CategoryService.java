package com.dxj.service.db;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dxj.bo.ResponseInfo;
import com.dxj.model.Category;

public interface CategoryService  extends IService<Category> {
    ResponseInfo findAll();
}
