package com.yyc.service;

import com.yyc.vo.RespMsg;
import com.yyc.vo.request.RoleAndPermissionVo;

/**
 * @program: SSM
 * @description: 角色、角色权限
 * @author: yyc
 * @create: 2020-03-20 14:39
 **/
public interface RoleService {
    RespMsg addRoleAndPermission(RoleAndPermissionVo rp);
    RespMsg findAllRole();
}
