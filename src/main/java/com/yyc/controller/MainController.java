
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
* @version 创建时间：2017年6月23日 下午4:18:25
* 类说明:
***************************************
*/
@Controller
@RequestMapping(value="/main")
public class MainController {
	private static Logger logger = LoggerFactory.getLogger(MainController.class);


	/**
	 * 1、@Autowired与@Resource都可以用来装配bean. 都可以写在字段上,或写在setter方法上。 
	   2、@Autowired默认按类型装配（这个注解是属业spring的），默认情况下必须要求依赖对象必须存在，
	   	如果要允许null 值，可以设置它的required属性为false，如：@Autowired(required=false) ，
	   	如果我们想使用名称装配可以结合@Qualifier注解进行使用，如下： 
		@Autowired() @Qualifier("baseDao")     
		private BaseDao baseDao;    
 	   3、@Resource（这个注解属于J2EE的），默认安照名称进行装配，名称可以通过name属性进行指定， 
		 如果没有指定name属性，当注解写在字段上时，默认取字段名进行按照名称查找，
		 如果注解写在setter方法上默认取属性名进行装配。 当找不到与名称匹配的bean时才按照类型进行装配。
		 但是需要注意的是，如果name属性一旦指定，就只会按照名称进行装配。
	 */
	private UserService userService;
	private MainService mainService;

	@Autowired
	public MainController(UserService userService, MainService mainService) {
		this.userService = userService;
		this.mainService = mainService;
	}
	//	@RequestMapping(value="/getUserInfo/{userId}")
//	@ResponseBody
//	public RespMsg getUserInfoById(@PathVariable("userId") Integer userId){
//		logger.info("getUserInfoById-->>>"+userId);
//		return  this.userService.getUserById(userId);
//	}

//	@RequestMapping(value="/updateUserInfo")
//	@ResponseBody
//	public RespMsg updateUserInfo(UserInfo userInfo){
//		return this.userService.modifyUser(userInfo);
//	}
//

//	@RequestMapping(value="/login")
//	@ResponseBody
//	public RespMsg login(UserInfo userInfo){
//		logger.info(userInfo.getPassword()+"----->>>>"+userInfo.getUsername());
//		return userService.getUserByName(userInfo);
//	}
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
