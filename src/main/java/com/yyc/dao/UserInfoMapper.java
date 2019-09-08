package com.yyc.dao;

import com.yyc.entity.UserInfo;

import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insertUser(UserInfo record);

    UserInfo selectByName(String userName);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record); //不可以更新密码

    List<UserInfo> selectAllUser();

}