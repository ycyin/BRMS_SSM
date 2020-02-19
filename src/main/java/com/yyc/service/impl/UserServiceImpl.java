package com.yyc.service.impl;

import com.yyc.dao.ISysRoleMapper;
import com.yyc.dao.IUserInfoMapper;
import com.yyc.entity.UserInfo;
import com.yyc.vo.RespMsg;
import com.yyc.vo.ResultEnum;
import com.yyc.vo.request.LoginVo;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.yyc.service.UserService;

import java.util.HashMap;
import java.util.List;

/**************************************
* @author 尹以操 E-mail:34782655@qq.com
* @version 创建时间：2017年6月23日 下午10:10:43
* 类说明:
***************************************
*/
@Service("userService")
public class UserServiceImpl implements UserService {

	private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	IUserInfoMapper userInfoMapper;
	@Autowired
	ISysRoleMapper sysRoleMapper;


//	@Override
//	public RespMsg getALLUser() {
//		List<UserInfo> userInfos = this.userInfoMapper.selectAllUser();
//		return new RespMsg(ResultEnum.SELECT_SUCCESS,userInfos);
//	}
//
//	@Override
//	public RespMsg modifyUser(UserInfo userinfo) {
//		Integer res = 0;
//		try{
//			res = this.userInfoMapper.updateByPrimaryKey(userinfo);
//			return res >=1?new RespMsg(ResultEnum.UPDATE_SUCCESS,res):new RespMsg(ResultEnum.UPDATE_FAILD,res);
//		}catch (Exception e){
//			if(res > 0){
//				return new RespMsg(ResultEnum.UPDATE_SUCCESS,res);
//			}
//			if(e.getCause().toString().contains("Duplicate")){
//				return new RespMsg(ResultEnum.UPDATE_FAILD_HAS_USER_DUPLICATE,res);
//			}else{
//				return new RespMsg(ResultEnum.UPDATE_FAILD_UNKNOW,res);
//			}
//		}
//
//	}
//
//	@Override
//	public RespMsg insertUser(UserInfo userinfo) {
//		Integer res = 0;
//		try{
//			res = this.userInfoMapper.insertUser(userinfo);
//			return res >=1?new RespMsg(ResultEnum.ADD_SUCCESS,res):new RespMsg(ResultEnum.ADD_SUCCESS,res);
//		}catch (Exception e){
//			if(res > 0){
//				return new RespMsg(ResultEnum.ADD_SUCCESS,res);
//			}
//			if(e.getCause().toString().contains("Duplicate")){
//				return new RespMsg(ResultEnum.ADD_FAILD_HAS_USER_DUPLICATE,res);
//			}else{
//				return new RespMsg(ResultEnum.ADD_FAILD_UNKNOW,res);
//			}
//		}
//	}
//
//	@Override
//	public RespMsg removeUserByPrimaryKey(Integer id) {
//		Integer res = this.userInfoMapper.deleteByPrimaryKey(id);
//		return res >= 1 ? new RespMsg(ResultEnum.DELETE_SUCCESS,res):
//				new RespMsg(ResultEnum.DELETE_FAILD,res);
//	}

	@Override
	public Object login(LoginVo login) {
		String loginname = login.getLoginname().trim();
		String password = login.getPassword().trim();
		Boolean rememberme = login.getRememberme();
		String errmessage = "";
		List<String> roleNames = null;
		String userName = null;
		// 1、获取Subject实例对象
		Subject currentUser = SecurityUtils.getSubject();

		// 2、判断当前用户是否登录
		// if (currentUser.isAuthenticated() == false) {
		// 3、将用户名和密码,是否登记住我，封装到UsernamePasswordToken
		UsernamePasswordToken token = new UsernamePasswordToken(loginname, password, rememberme);
		// 4、认证
		try {
			currentUser.login(token);// 传到MyShiroRealm类中的方法进行认证
			Session session = currentUser.getSession();
			session.setAttribute("loginname", loginname);
//			// 认证成功，返回角色信息
			roleNames = sysRoleMapper.findRoleNameByUsername(loginname);
			userName = userInfoMapper.findNameByUsername(loginname);
			session.setAttribute("role", roleNames);
			session.setTimeout(1000 * 60 * 60 * 2);//设置会话过期时间，2小时
			HashMap data = new HashMap();
			data.put("username",userName);
			data.put("loginname", loginname);
			data.put("rolenames", roleNames);
			return new RespMsg(ResultEnum.LOGIN_SUCCESS,data);
		} catch (UnknownAccountException uae) {
			logger.warn("对用户[" + loginname + "]进行登录验证..验证未通过,未知账户");
			errmessage = "未知账户";
		} catch (IncorrectCredentialsException ice) {
			logger.warn("对用户[" + loginname + "]进行登录验证..验证未通过,错误的凭证");
			errmessage = "密码不正确";
		} catch (LockedAccountException lae) {
			logger.warn("对用户[" + login + "]进行登录验证..验证未通过,账户已锁定");
			errmessage = "账户已锁定";
		} /*
		 * catch (PasswordNotModifyException pe) { logger.error("对用户[" +
		 * loginname + "]进行登录验证..验证未通过,堆栈轨迹如下"); pe.printStackTrace();
		 * errmessage = "修改密码后才允许登录！"; }
		 */ catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			logger.error("对用户[" + loginname + "]进行登录验证..验证未通过,堆栈轨迹如下");
			ae.printStackTrace();
			errmessage = "未知错误，请联系管理员！";
		}

		return new RespMsg(500, errmessage,null);
	}

	@Override
	public UserInfo findByUsername(String username) {
		return userInfoMapper.findByUsername(username);
	}
}
