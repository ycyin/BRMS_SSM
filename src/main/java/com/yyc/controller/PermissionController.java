package com.yyc.controller;

import com.yyc.service.PermissionService;
import com.yyc.vo.RespMsg;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: SSM
 * @description: 权限信息
 * @author: yyc
 * @create: 2020-03-19 17:05
 **/

@Controller
@RequestMapping(value="/permission")
public class PermissionController {


    @Autowired
    private PermissionService permissionService;

    @RequiresPermissions("permiss:get")
    @RequestMapping("/getAllPermission")
    @ResponseBody
    public RespMsg getAllPermission(){
        return  this.permissionService.getAllPermission();
    }

    @RequiresPermissions("permiss:get")
    @RequestMapping("/getPermissionsByRoleId/{roleId}")
    @ResponseBody
    public RespMsg getPermissionsByRoleId(@PathVariable("roleId") Integer roleId){
        return  this.permissionService.getPermissionIdsByRoleId(roleId);
    }

}
