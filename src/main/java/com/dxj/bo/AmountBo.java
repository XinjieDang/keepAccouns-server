package com.dxj.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Date updateTime;
    private String remark;
}
