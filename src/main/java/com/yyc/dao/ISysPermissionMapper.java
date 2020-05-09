package com.yyc.dao;

import com.yyc.entity.RolePermission;
import com.yyc.entity.SysPermission;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @description: 系统权限Mapper
 * @author: yyc
 **/
public interface ISysPermissionMapper {
    /**
     * 根据角色ID查询对应角色拥有的权限信息
     * @param roleId
     * @return
     */
    List<SysPermission> findPermissionByRoleId(@Param("roleId") Integer roleId);
    /**
     * 查询所有权限信息
     * @return
     */
    List<SysPermission> findAllPermission();
    /**
     * 根据角色ID查询对应角色拥有的所有权限ID
     * @param roleId
     * @return
     */
    List<Integer> findPermissionIdsByRoleId(@Param("roleId")Integer roleId);
    /**
     * 根据角色ID删除对应角色拥有的所有权限信息
     * @param roleId
     * @return
     */
    Integer deletePermissionsByRoleId(@Param("roleId")Integer roleId);
    /**
     * 批量插入角色拥有的权限信息（通常一个角色拥有多个权限）
     * @param list
     * @return
     */
    Integer insertPermissionsWithRoleId(List<RolePermission> list);
    /**
     * 根据角色名查询拥有的权限字符串
     * @param roleNames
     * @return
     */
    List<String> findPermissionsByRoleNames(@Param("roleNames")List<String> roleNames);
    /**
     * 根据角色名查询拥有的权限中文名称
     * @param roleNames
     * @return
     */
    List<String> findPermissionNamesByRoleNames(@Param("roleNames")List<String> roleNames);

}