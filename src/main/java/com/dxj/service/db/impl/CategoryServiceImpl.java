package com.dxj.service.db.impl;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dxj.bo.CategoryInfoBO;
import com.dxj.bo.ResponseInfo;
import com.dxj.mapper.CategoryMapper;
import com.dxj.model.Category;
import com.dxj.service.db.CategoryService;
import com.dxj.vo.CategoryInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Override
    public ResponseInfo findAll() {
        LambdaQueryWrapper<Category> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(Category::getId,Category::getPayType,Category::getCategoryName);
        //查询全部的分类信息
        List<Category> list=categoryMapper.selectList(lambdaQueryWrapper);
        //构造接收vo
        List<Category> categoryInfoVo=new ArrayList<>();
        //构造返回map
        Map<Object,Object> map=new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getPayType().equals(0)){
                categoryInfoVo.add(list.get(i));
            }
        }
        map.put("list1",categoryInfoVo);
       Stream<Category> list2=list.stream().filter(item->item.getPayType().equals(1));
        CategoryInfoVo vo=null;
        map.put("list2",list2);
//        System.out.println("原数组"+list);
        //通过类型进行分组
        return ResponseInfo.success(map);
    }
}
