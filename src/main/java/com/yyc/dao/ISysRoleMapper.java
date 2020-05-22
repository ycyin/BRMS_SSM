package com.yyc.dao;

import com.yyc.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明: 系统角色Mapper
 ***************************************
 */
public interface ISysRoleMapper {
    /**
     * 通过username查找用户角色信息
     * @param username 用户名
     * @return 用户的角色列表
     */
    List<SysRole> findRoleByUsername(@Param("username") String username);

    /**
     * 通过username查找用户所有角色名
     * @param username 用户名
     * @return 用户的角色名列表
     */
    List<String> findRoleNameByUsername(@Param("username") String username);

    /**
     * 通过角色名查找角色信息
     * @param roleName 角色名
     * @return 角色信息
     */
    SysRole selectRoleByRoleName(@Param("roleName") String roleName);

    /**
     * 通过角色名修改角色信息
     * @param role 角色名
     * @return SQL执行受影响的条数
     */
    Integer updateRoleByRoleName(SysRole role);

    /**
     * 插入一条角色信息
     * @param role 需要插入的角色信息
     * @return SQL执行受影响的条数
     */
    Integer insertRole(SysRole role);

    /**
     * 插入一条角色权限信息
     * @param roleId 角色ID
     * @param permissionId 权限ID
     * @return SQL执行受影响的条数
     */
    Integer insertRoleAndPermission(@Param("roleId") Integer roleId,@Param("permissionId") Integer permissionId);

    /**
     * 查询所有的角色
     * @return 角色信息列表
     */
    List<SysRole> selectAllRole();


}