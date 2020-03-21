package com.yyc.entity;

/**
 * @program: SSM
 * @description: 角色权限中间表实体
 * @author: yyc
 * @create: 2020-03-21 08:51
 **/
public class RolePermission {
    private Integer roleId;
    private Integer permissionId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}
