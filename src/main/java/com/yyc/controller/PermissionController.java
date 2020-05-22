package com.yyc.controller;

import com.yyc.service.PermissionService;
import com.yyc.vo.RespMsg;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明: 权限信息
 ***************************************
 */
@Controller
@RequestMapping(value="/permission")
public class PermissionController {

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

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
