package com.dxj.vo;

import com.dxj.bo.AmountBo;
import com.dxj.model.Amount;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AmountInfoVo {
    /*日期*/
    private String createDate;
    /*金额-支出*/
    private BigDecimal expend;
    /*金额-收入*/
    private BigDecimal income;
    /*收支列表*/
    private List<AmountBo> amountBos;
}
