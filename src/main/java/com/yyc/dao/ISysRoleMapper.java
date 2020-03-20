package com.yyc.dao;

import com.yyc.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ISysRoleMapper {
    //通过username查找用户角色信息
    List<SysRole> findRoleByUsername(@Param("username") String username);

    //通过username查找用户所有角色名
    List<String> findRoleNameByUsername(@Param("username") String username);

    //通过角色名查找角色信息
    SysRole selectRoleByRoleName(@Param("roleName") String roleName);

    //通过角色名修改角色信息
    Integer updateRoleByRoleName(SysRole role);

    //插入一条角色信息
    Integer insertRole(SysRole role);

    //插入一条角色权限信息
    Integer insertRoleAndPermission(@Param("roleId") Integer roleId,@Param("permissionId") Integer permissionId);

    //查询所有的角色
    List<SysRole> selectAllRole();
}