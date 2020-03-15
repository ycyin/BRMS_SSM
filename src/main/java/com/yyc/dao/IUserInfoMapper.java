package com.yyc.dao;

import com.yyc.dto.UserDTO;
import com.yyc.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IUserInfoMapper {
    //通过username查找用户角色信息
    UserInfo findByUsername(@Param("username") String username);

    //通过username查找用户名字
    String findNameByUsername(@Param("username") String username);

    List<UserDTO> selectAllUser();

    //添加用户
    Integer insertUser(UserInfo user);

    //添加用户角色
    Integer insertUserAndRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    //删除角色
    Integer deleteRoleByUserId(@Param("userId") Integer userId);

    //删除用户
    Integer deleteUserByUserId(@Param("userId") Integer userId);

    //根据id查询用户
    UserDTO selectUserByUserId(@Param("userId") Integer userId);

    //更新用户
    Integer updateUser(UserInfo user);

    //更新角色
    Integer updateUserAndRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

}
