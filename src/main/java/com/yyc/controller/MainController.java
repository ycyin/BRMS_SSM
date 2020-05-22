
package com.yyc.controller;

import com.yyc.service.MainService;
import com.yyc.service.UserService;
import com.yyc.vo.RespMsg;
import com.yyc.vo.request.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**************************************
* @author 尹以操 E-mail:34782655@qq.com
* @version 创建时间：
* 类说明:
***************************************
*/
@Controller
@RequestMapping(value="/main")
public class MainController {

	private final UserService userService;
	private final MainService mainService;

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
	 */
	@RequestMapping(value = "/getSiteData")
	@ResponseBody
	public RespMsg getSiteData(){
		return mainService.getSiteData();
	}

}
