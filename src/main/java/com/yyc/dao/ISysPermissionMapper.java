package com.yyc.dao;

import com.yyc.entity.RolePermission;
import com.yyc.entity.SysPermission;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明: 系统权限Mapper
 ***************************************
 */
public interface ISysPermissionMapper {
    /**
     * 根据角色ID查询对应角色拥有的权限信息
     * @param roleId 角色ID
     * @return 权限列表
     */
    List<SysPermission> findPermissionByRoleId(@Param("roleId") Integer roleId);
    /**
     * 查询所有权限信息
     * @return 权限列表
     */
    List<SysPermission> findAllPermission();
    /**
     * 根据角色ID查询对应角色拥有的所有权限ID
     * @param roleId 角色ID
     * @return 权限ID列表
     */
    List<Integer> findPermissionIdsByRoleId(@Param("roleId")Integer roleId);
    /**
     * 根据角色ID删除对应角色拥有的所有权限信息
     * @param roleId 角色ID
     * @return SQL执行受影响的条数
     */
    Integer deletePermissionsByRoleId(@Param("roleId")Integer roleId);
    /**
     * 批量插入角色拥有的权限信息（通常一个角色拥有多个权限）
     * @param list 需要批量插入的角色权限信息
     * @return SQL执行受影响的条数
     */
    Integer insertPermissionsWithRoleId(List<RolePermission> list);
    /**
     * 根据角色名查询拥有的权限字符串
     * @param roleNames 角色名列表
     * @return 权限字符串列表
     */
    List<String> findPermissionsByRoleNames(@Param("roleNames")List<String> roleNames);
    /**
     * 根据角色名查询拥有的权限中文名称
     * @param roleNames 角色名列表
     * @return 权限中文名称列表
     */
    List<String> findPermissionNamesByRoleNames(@Param("roleNames")List<String> roleNames);

}