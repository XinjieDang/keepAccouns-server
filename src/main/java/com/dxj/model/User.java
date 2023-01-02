package com.dxj.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;
@Data
@TableName("t_ka_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String userName;
    private String userPassword;
    private String avatar;
    private String signature;
    private String remark;
    private Date createTime;
    private Date updateTime;
}
