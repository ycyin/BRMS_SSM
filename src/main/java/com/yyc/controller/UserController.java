package com.yyc.controller;

import com.yyc.service.UserService;
import com.yyc.vo.RespMsg;
import com.yyc.vo.ResultEnum;
import com.yyc.vo.request.UserVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: SSM
 * @description: 用户管理控制层
 * @author: yyc
 * @create: 2020-03-11 11:00
 **/
//@RequiresRoles("admin")
@Controller
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserService userService;

	@RequiresPermissions("user:get")
    @RequestMapping(value="/getUserList")
    @ResponseBody
    public RespMsg getUserList(){
		return  this.userService.getALLUser();
    }

	@RequiresPermissions("user:add")
    @RequestMapping(value="/addUser")
	@ResponseBody
	public RespMsg addUser(UserVo userVo){
		return this.userService.insertUser(userVo);
	}

	@RequiresPermissions("user:remove")
	@RequestMapping(value="/deleteUser")
	@ResponseBody
	public RespMsg deleteUser(Integer id){
		return this.userService.removeUserAndRoleInfo(id);
	}

	@RequiresPermissions("user:get")
	@RequestMapping(value="/getUserInfoById/{userId}")
	@ResponseBody
	public RespMsg getUserInfoById(@PathVariable("userId") Integer userId){
		return this.userService.findUserByUserId(userId);
	}

	@RequiresPermissions("user:modify")
	@RequestMapping(value="/updateUser")
	@ResponseBody
	public RespMsg updateUser(UserVo userVo){
		return this.userService.modifyUser(userVo);
	}

}
