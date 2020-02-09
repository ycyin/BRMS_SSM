package com.yyc.dao;

import com.yyc.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISysRoleMapper {
    //通过username查找用户角色信息
    List<SysRole> findRoleByUsername(@Param("username") String username);
}