
package com.yyc.controller;

import com.yyc.service.MainService;
import com.yyc.vo.RespMsg;
import com.yyc.vo.ResultEnum;
import com.yyc.vo.request.LoginVo;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.yyc.service.UserService;
import java.util.HashMap;
import java.util.Map;

/**************************************
* @author 尹以操 E-mail:34782655@qq.com
* @version 创建时间：
* 类说明:
***************************************
*/
@Controller
@RequestMapping(value="/main")
public class MainController {
	private static Logger logger = LoggerFactory.getLogger(MainController.class);

	private UserService userService;
	private MainService mainService;

	@Autowired
	public MainController(UserService userService, MainService mainService) {
		this.userService = userService;
		this.mainService = mainService;
	}
	/**
	 * 真正的登录方法
	 */
	@RequestMapping(value = "/login")
	@ResponseBody
	public Object login(LoginVo user) {
		return userService.login(user);
	}

	/**
	 * 返回站点信息，包括：
	 * 本站图书总数
	 * 本站分销点总数
	 * 本站店长总数
	 * 本站分销员总数
	 * 本站普通店员总数
	 * @return
	 */
	@RequestMapping(value = "/getSiteData")
	@ResponseBody
	public RespMsg getSiteData(){
		return mainService.getSiteData();
	}

}
