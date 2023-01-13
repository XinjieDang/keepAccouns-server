package com.dxj.vo;

import com.dxj.bo.CategoryInfoBO;
import lombok.Data;

import java.util.List;

@Data
public class CategoryInfoVo {
    private Integer id;
    private Integer payType;
    private List<CategoryInfoBO> list;
}
