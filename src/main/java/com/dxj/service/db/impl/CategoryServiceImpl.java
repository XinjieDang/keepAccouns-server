package com.dxj.service.db.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dxj.bo.ResponseInfo;
import com.dxj.mapper.CategoryMapper;
import com.dxj.model.Category;
import com.dxj.service.db.CategoryService;
import com.dxj.vo.CategoryInfoVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public ResponseInfo findAll() {
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(Category::getId, Category::getPayType, Category::getCategoryName);
        //查询全部的分类信息
        List<Category> list = categoryMapper.selectList(lambdaQueryWrapper);
        //构造接收vo
        List<Category> categoryInfoListType1 = new ArrayList<>();
        List<Category> categoryInfoListType2 = new ArrayList<>();
        List<CategoryInfoVo> categoryInfoVos=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPayType().equals(0)) {
                categoryInfoListType1.add(list.get(i));
            }
            else {
                categoryInfoListType2.add(list.get(i));
            }
        }
        //通过类型进行分组
        CategoryInfoVo categoryInfoVoType1=new CategoryInfoVo();
        categoryInfoVoType1.setPayType(0);
        categoryInfoVoType1.setList(categoryInfoListType1);
        categoryInfoVos.add(categoryInfoVoType1);
        CategoryInfoVo categoryInfoVoType2=new CategoryInfoVo();
        categoryInfoVoType2.setPayType(1);
        categoryInfoVoType2.setList(categoryInfoListType2);
        categoryInfoVos.add(categoryInfoVoType2);
        return ResponseInfo.success(categoryInfoVos);
    }
}
