package com.dxj.vo;
import com.dxj.model.Category;
import lombok.Data;
import java.util.List;

@Data
public class CategoryInfoVo {
    private Integer payType;
    private List<Category> list;
}
