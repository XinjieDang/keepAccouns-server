package com.dxj.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dxj.model.Amount;
import java.util.List;
public interface AmountMapper extends BaseMapper<Amount> {
   List< String> amountDateList();
}
