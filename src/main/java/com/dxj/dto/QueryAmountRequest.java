package com.dxj.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * @author dxj
 * @Description 记账记录查询dto
 * @date 2023/2/19 20:44
 */
@Data
@AllArgsConstructor
public class QueryAmountRequest {
    private Long userId;
    private Integer categoryId;
    private String createTime;
}
