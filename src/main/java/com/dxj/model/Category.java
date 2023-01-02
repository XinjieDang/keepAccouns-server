package com.dxj.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@Data
@TableName("t_ka_category")
public class Category {
    private Integer id;
    private String categoryName;
    private String categoryIcon;
    private Integer payType;
    private Date createTime;
    private Date updateTime;
}
