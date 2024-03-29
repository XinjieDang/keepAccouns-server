package com.dxj.service.db.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dxj.bo.AmountBo;
import com.dxj.bo.ResponseInfo;
import com.dxj.dto.QueryAmountRequest;
import com.dxj.mapper.AmountMapper;
import com.dxj.mapper.CategoryMapper;
import com.dxj.model.Amount;
import com.dxj.model.Category;
import com.dxj.model.LoginUser;
import com.dxj.security.common.utils.JwtTokenUtils;
import com.dxj.service.db.AmountService;
import com.dxj.vo.AmountCategoryInfoVo;
import com.dxj.vo.AmountInfoVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AmountServiceImpl extends ServiceImpl<AmountMapper, Amount> implements AmountService {
    @Resource
    private AmountMapper amountMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Override
    public ResponseInfo findAll(QueryAmountRequest queryAmountRequest) {
      LoginUser currentLoginUser= JwtTokenUtils.getCurrentLoginUser();
        queryAmountRequest.setUserId(currentLoginUser.getUser().getId());
        //日期去重后的数据
        List<String> dateList = amountMapper.amountDateList(queryAmountRequest);
        //返回的数据列表
        List<AmountInfoVo> amountInfoVoList = new ArrayList<>();
        for (String dateObject : dateList) {
            //组装记账日期
            AmountInfoVo amountInfoVo = new AmountInfoVo();
            //设置记账日期
            amountInfoVo.setCreateDate(dateObject);
            //查询当前日期下所有的收支情况
            queryAmountRequest.setCreateTime(dateObject);
            List<AmountCategoryInfoVo> amountCategoryInfoVos = categoryMapper.AmountCategoryInfo(queryAmountRequest);
            //计算收入和支出情况
            BigDecimal expend = new BigDecimal(0);
            BigDecimal income = new BigDecimal(0);
            for (AmountCategoryInfoVo amountCategoryInfoVo : amountCategoryInfoVos) {
                if (amountCategoryInfoVo.getPayType().equals(0)) {//支出
                    expend = expend.add(new BigDecimal(amountCategoryInfoVo.getAmount().toBigInteger()));
                } else {
                    income = income.add(new BigDecimal(amountCategoryInfoVo.getAmount().toBigInteger()));
                }
            }
            //设置当前日期下的收入和支出
            amountInfoVo.setExpend(expend);
            amountInfoVo.setIncome(income);
            //查询当前日期下所有的记账记录列表
            List<Amount> amountList = amountMapper.amountList(queryAmountRequest);
            // 组装当前日期下所有的记账记录
            List<AmountBo> amountBos = new ArrayList<>();
            for (Amount amount : amountList) {
                AmountBo amountBo = new AmountBo();
                //查询记账类型
                Category category = categoryMapper.selectOne(new LambdaQueryWrapper<Category>().select(Category::getPayType,Category::getId).eq(Category::getId, amount.getCategoryId()));
                //设置记账分类
                amountBo.setCategoryId(category.getId());
                //设置记账类型
                amountBo.setAmountType(category.getPayType());
                //记账金额
                amountBo.setAmount(amount.getAmount());
                //记账备注
                amountBo.setRemark(amount.getRemark());
                //记账时间
                amountBo.setCreateTime(amount.getCreateTime());
                amountBos.add(amountBo);

            }
            //设置记账记录的list
            amountInfoVo.setAmountBos(amountBos);
            amountInfoVoList.add(amountInfoVo);
        }
        //计算总支出和总收入
        BigDecimal allExpend = new BigDecimal(0);
        BigDecimal allIncome = new BigDecimal(0);
        for (AmountInfoVo amountInfoVo : amountInfoVoList) {
            allExpend = allExpend.add(new BigDecimal(amountInfoVo.getExpend().toBigInteger()));
            allIncome = allIncome.add(new BigDecimal(amountInfoVo.getIncome().toBigInteger()));
        }
        Map<Object, Object> res = new HashMap<>();
        res.put("allExpend", allExpend);
        res.put("allIncome", allIncome);
        res.put("data", amountInfoVoList);
        return ResponseInfo.success(res);
    }
}
