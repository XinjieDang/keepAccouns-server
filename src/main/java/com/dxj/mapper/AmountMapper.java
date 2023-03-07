package com.dxj.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dxj.dto.QueryAmountRequest;
import com.dxj.model.Amount;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface AmountMapper extends BaseMapper<Amount> {
   List< String> amountDateList(@Param("param") QueryAmountRequest queryAmountRequest);
   List<Amount>amountList(@Param("param") QueryAmountRequest queryAmountRequest);
}
