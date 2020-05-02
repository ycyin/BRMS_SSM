package com.yyc.dao;

import com.yyc.entity.RolePermission;
import com.yyc.entity.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ISysPermissionMapper {
    //根据角色ID查询角色对应的权限信息
    List<SysPermission> findPermissionByRoleId(@Param("roleId") Integer roleId);
    //查询所有权限信息
    List<SysPermission> findAllPermission();

    //根据角色ID查询角色对应的所有权限ID
    List<Integer> findPermissionIdsByRoleId(@Param("roleId")Integer roleId);

    //根据角色ID删除角色对应的所有权限
    Integer deletePermissionsByRoleId(@Param("roleId")Integer roleId);

    //批量插入角色权限表
    Integer insertPermissionsWithRoleId(List<RolePermission> list);

    /**
     * 根据角色名查询拥有的权限字符串
     */
    List<String> findPermissionsByRoleNames(@Param("roleNames")List<String> roleNames);

    /**
     * 根据角色名查询拥有的权限名称
     */
    List<String> findPermissionNamesByRoleNames(@Param("roleNames")List<String> roleNames);

}