package com.yyc.dao;

import com.yyc.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface IUserInfoMapper {
    //通过username查找用户角色信息
    UserInfo findByUsername(@Param("username") String username);

    //通过username查找用户名字
    String findNameByUsername(@Param("username") String username);
}