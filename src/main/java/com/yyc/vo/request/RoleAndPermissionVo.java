package com.yyc.vo.request;

import java.util.List;

/**
 * @program: SSM
 * @description: 角色和权限VO
 * @author: yyc
 * @create: 2020-03-20 09:12
 **/
public class RoleAndPermissionVo {
    private String roleName;
    private String roleDesc;
    private List<Integer> existingPermissions;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Integer> getExistingPermissions() {
        return existingPermissions;
    }

    public void setExistingPermissions(List<Integer> existingPermissions) {
        this.existingPermissions = existingPermissions;
    }

    @Override
    public String toString() {
        return "RoleAndPermissionVo{" +
                "roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", existingPermissions=" + existingPermissions +
                '}';
    }
}
