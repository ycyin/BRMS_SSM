package com.yyc.controller;

import com.yyc.service.RoleService;
import com.yyc.vo.RespMsg;
import com.yyc.vo.request.RoleAndPermissionVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明: 角色
 ***************************************
 */
@Controller
@RequestMapping(value="/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 添加或更新角色权限信息
     */
    @RequiresPermissions("role:add")
    @RequestMapping("/addOrUpdateRoleAndPermission")
    @ResponseBody
    public RespMsg addRoleAndPermission(RoleAndPermissionVo rp){
        return  this.roleService.addRoleAndPermission(rp);
    }

    @RequiresPermissions("role:get")
    @RequestMapping("/getRoleList")
    @ResponseBody
    public RespMsg getRoleList(){
        return  this.roleService.findAllRole();
    }
}
