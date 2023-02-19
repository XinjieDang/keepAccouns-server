package com.dxj.service.db;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dxj.bo.ResponseInfo;
import com.dxj.dto.QueryAmountRequest;
import com.dxj.model.Amount;
public interface AmountService extends IService<Amount> {
    ResponseInfo findAll(QueryAmountRequest amountRequest);

}
