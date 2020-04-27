package com.yyc.service.impl;

import com.yyc.dao.ISysPermissionMapper;
import com.yyc.dao.ISysRoleMapper;
import com.yyc.dao.IUserInfoMapper;
import com.yyc.dto.UserDTO;
import com.yyc.entity.UserInfo;
import com.yyc.utils.GeneratePasswordAndSalt;
import com.yyc.vo.RespMsg;
import com.yyc.vo.ResultEnum;
import com.yyc.vo.request.LoginVo;

import com.yyc.vo.request.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.yyc.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	@Autowired
	ISysPermissionMapper sysPermissionMapper;


	@Override
	public RespMsg getALLUser() {
		List<UserDTO> userInfos = this.userInfoMapper.selectAllUser();
		Session session = SecurityUtils.getSubject().getSession();
		String loginname = (String) session.getAttribute("loginname");
		// 过滤掉角色是admin的用户和登录名是自身的用户
		List<UserDTO> collect = userInfos.stream().filter(u ->
		!u.getUserName().equalsIgnoreCase(loginname)&&
		!"admin".equalsIgnoreCase(u.getRole())).collect(Collectors.toList());
		return new RespMsg(ResultEnum.SELECT_SUCCESS,collect);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public RespMsg insertUser(UserVo userVo) {
		Map<String, String> passAndSalt = GeneratePasswordAndSalt.generate(userVo.getUsername(), userVo.getPassword());
		String password = passAndSalt.get("password");
		String salt = passAndSalt.get("salt");
		UserInfo user = new UserInfo(userVo.getUsername(),userVo.getNickname(),password,salt,(byte)userVo.getState());
		Integer res = 0,res2 = 0;
		try {
			res = userInfoMapper.insertUser(user);
			//使用mybatis的selectKey标签可以返回添加后的ID
			Integer userId = user.getId();
			Integer roleId = userVo.getRole();
			if (userId != null && roleId != null){
				res2 = userInfoMapper.insertUserAndRole(userId, roleId);
				return res >=1 && res2 >= 1?new RespMsg(ResultEnum.ADD_SUCCESS,res):new RespMsg(ResultEnum.ADD_SUCCESS,res);
			}else {
				return new RespMsg(ResultEnum.HAS_NULL,res);
			}
		}catch (Exception e) {
			e.printStackTrace();
			if(res > 0 && res2 > 0){
				return new RespMsg(ResultEnum.ADD_SUCCESS,res);
			}
			//手动回滚事务，由于已经被捕获异常，故事务不会自动回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			if(e.getCause().toString().contains("Duplicate")){
				return new RespMsg(ResultEnum.ADD_FAILD_HAS_USER_DUPLICATE,res);
			}else{
				return new RespMsg(ResultEnum.ADD_FAILD_UNKNOW,res);
			}
		}
	}

	@Transactional
	@Override
	public RespMsg modifyUser(UserVo userVo) {
		Integer res1 = 0;
		Integer res2 = 0;
		UserInfo user = new UserInfo(userVo.getId(),userVo.getUsername(),userVo.getNickname(),(byte)userVo.getState());
		try{
			res1 = this.userInfoMapper.updateUser(user);
			res2 = this.userInfoMapper.updateUserAndRole(user.getId(), userVo.getRole());
			return new RespMsg(ResultEnum.UPDATE_SUCCESS,res1);
		}catch (Exception e){
			e.printStackTrace();
			if(res1 > 0 || res2 > 0){
				return new RespMsg(ResultEnum.UPDATE_SUCCESS,res1);
			}
			//手动回滚事务，由于已经被捕获异常，故事务不会自动回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			if(e.getCause().toString().contains("Duplicate")){
				return new RespMsg(ResultEnum.UPDATE_FAILD_HAS_USER_DUPLICATE,res1);
			}else{
				return new RespMsg(ResultEnum.UPDATE_FAILD_UNKNOW,res1);
			}
		}

	}


	@Transactional
	@Override
	public RespMsg removeUserAndRoleInfo(Integer id) {
		if (id ==  null)
			return new RespMsg(ResultEnum.HAS_NULL);
		Integer res1 = this.userInfoMapper.deleteRoleByUserId(id);
		Integer res2 = this.userInfoMapper.deleteUserByUserId(id);
		return res1 >= 1 && res2 >= 1 ? new RespMsg(ResultEnum.DELETE_SUCCESS,res2):
				new RespMsg(ResultEnum.DELETE_FAILD,res2);
	}

	@Override
	public Object login(LoginVo login) {
		String loginname = login.getLoginname().trim();
		String password = login.getPassword().trim();
		Boolean rememberme = login.getRememberme();
		String errmessage = "";
		List<String> roleNames = null;
		List<String> permissions = null;
		String userName = null;
		// 1、获取Subject实例对象
		Subject currentUser = SecurityUtils.getSubject();

		// 2、判断当前用户是否登录
		// if (currentUser.isAuthenticated() == false) {
		// 3、将用户名和密码,是否登记住我，封装到UsernamePasswordToken
		UsernamePasswordToken token = new UsernamePasswordToken(loginname, password, rememberme);
		// 4、认证
		try {
			// 传到MyShiroRealm类中的方法进行认证
			currentUser.login(token);
			Session session = currentUser.getSession();
			session.setAttribute("loginname", loginname);
//			// 认证成功，返回角色信息
			roleNames = sysRoleMapper.findRoleNameByUsername(loginname);
			permissions = sysPermissionMapper.findPermissionsByRoleNames(roleNames);
			userName = userInfoMapper.findNameByUsername(loginname);
			session.setAttribute("role", roleNames);
			// 设置会话过期时间，2小时
			session.setTimeout(1000 * 60 * 60 * 2);
			HashMap data = new HashMap();
			data.put("username",userName);
			data.put("loginname", loginname);
			data.put("rolenames", roleNames);
			data.put("permissions",permissions);
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

	@Override
	public RespMsg findUserByUserId(Integer id) {
		if (id ==  null)
			return new RespMsg(ResultEnum.HAS_NULL);
		UserDTO userDTO = this.userInfoMapper.selectUserByUserId(id);
		UserVo uv = new UserVo(userDTO.getUserName(),userDTO.getNickName(),Integer.valueOf(userDTO.getRole()),userDTO.getState());
		return userDTO != null ? new RespMsg(ResultEnum.SELECT_SUCCESS,uv):
					new RespMsg(ResultEnum.SELECT_FAILD,uv);
	}
}
