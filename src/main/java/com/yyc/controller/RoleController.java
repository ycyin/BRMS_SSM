package com.yyc.controller;

import com.yyc.service.RoleService;
import com.yyc.vo.RespMsg;
import com.yyc.vo.ResultEnum;
import com.yyc.vo.request.RoleAndPermissionVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: SSM
 * @description: 角色
 * @author: yyc
 * @create: 2020-03-20 09:09
 **/
@Controller
@RequestMapping(value="/role")
public class RoleController {


    @Autowired
    private RoleService roleService;

    /**
     * 添加或更新角色权限信息
     * @return
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
