package com.dxj.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@Data
@TableName("t_ka_category")
public class Category {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String categoryName;
    private String categoryIcon;
    private Integer payType;
    private Date createTime;
    private Date updateTime;
}
