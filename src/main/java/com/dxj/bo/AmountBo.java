package com.dxj.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class AmountBo {
    private Integer id;
    private Integer categoryId;
    /*支出类型*/
    private Integer amountType;
    private BigDecimal amount;
    private Date createTime;
    private Date updateTime;
    private String remark;
}
