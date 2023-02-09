package com.dxj.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dxj.model.Category;
import com.dxj.vo.AmountCategoryInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {
  List<AmountCategoryInfoVo> AmountCategoryInfo(@Param("dateValue") String dateValue);
}
