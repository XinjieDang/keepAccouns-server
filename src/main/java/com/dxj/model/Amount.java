package com.dxj.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
@TableName("t_ka_amount")
public class Amount {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer categoryId;
    private BigDecimal amount;
    private Date createTime;
    private Date updateTime;
    private String remark;

}
