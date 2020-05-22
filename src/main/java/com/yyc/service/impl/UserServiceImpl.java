package com.yyc.service.impl;

import com.alibaba.fastjson.JSON;
import com.yyc.dao.ISysPermissionMapper;
import com.yyc.dao.ISysRoleMapper;
import com.yyc.dao.IUserInfoMapper;
import com.yyc.dto.UserDTO;
import com.yyc.entity.UserInfo;
import com.yyc.shiro.MyAuthenticationToken;
import com.yyc.utils.GeneratePasswordAndSalt;
import com.yyc.utils.JwtUtils;
import com.yyc.vo.RespMsg;
import com.yyc.vo.ResultEnum;
import com.yyc.vo.request.LoginVo;
import com.yyc.vo.request.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
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
* @version 创建/修改时间：
* 类说明:
***************************************
*/
@Service("userService")
public class UserServiceImpl implements UserService {

	private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private final IUserInfoMapper userInfoMapper;
	private final ISysRoleMapper sysRoleMapper;
	private final ISysPermissionMapper sysPermissionMapper;

	@Autowired
	public UserServiceImpl(IUserInfoMapper userInfoMapper, ISysRoleMapper sysRoleMapper, ISysPermissionMapper sysPermissionMapper) {
		this.userInfoMapper = userInfoMapper;
		this.sysRoleMapper = sysRoleMapper;
		this.sysPermissionMapper = sysPermissionMapper;
	}


	@Override
	public RespMsg getAllUser() {
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
		UserInfo user = new UserInfo(userVo.getUsername(),userVo.getNickname(),password,salt,Byte.valueOf(userVo.getState()+""));
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
			return e.getCause().toString().contains("Duplicate")?
				new RespMsg(ResultEnum.ADD_FAILD_HAS_USER_DUPLICATE,res):
				new RespMsg(ResultEnum.ADD_FAILD_UNKNOW,res);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public RespMsg modifyUser(UserVo userVo) {
		Integer res1 = 0;
		Integer res2 = 0;
		UserInfo user = new UserInfo(userVo.getId(),userVo.getUsername(),userVo.getNickname(),Byte.valueOf(userVo.getState()+""));
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
			return e.getCause().toString().contains("Duplicate")?
				new RespMsg(ResultEnum.UPDATE_FAILD_HAS_USER_DUPLICATE,res1):
				new RespMsg(ResultEnum.UPDATE_FAILD_UNKNOW,res1);
		}

	}


	@Transactional(rollbackFor = Exception.class)
	@Override
	public RespMsg removeUserAndRoleInfo(Integer id) {
		if (id ==  null){
			return new RespMsg(ResultEnum.HAS_NULL);
		}
		Integer res1 = this.userInfoMapper.deleteRoleByUserId(id);
		Integer res2 = this.userInfoMapper.deleteUserByUserId(id);
		return res1 >= 1 && res2 >= 1 ? new RespMsg(ResultEnum.DELETE_SUCCESS,res2):
				new RespMsg(ResultEnum.DELETE_FAILD,res2);
	}

	@Override
	public Object login(LoginVo login) {
		String loginname = login.getLoginname().trim();
		String password = login.getPassword().trim();
		// 将用户名和密码封装到实体对象
		UserInfo user = new UserInfo(loginname,password);
		// 生成token 有效时间 600 秒
		String jwtToken = JwtUtils.createJWT(600,JSON.toJSONString(user));
		// 将JWT TOKEN封装到自定义MyAuthenticationToken
		MyAuthenticationToken token = new MyAuthenticationToken(jwtToken);
		String errmessage = "";
		try {
			// 获取Subject实例对象，并传到MyShiroRealm类中的方法进行认证
			SecurityUtils.getSubject().login(token);
			// 未产生异常认证成功，返回角色信息
			List<String> roleNames = sysRoleMapper.findRoleNameByUsername(loginname);
			// 权限信息
			List<String> permissions = sysPermissionMapper.findPermissionsByRoleNames(roleNames);
			String userName = userInfoMapper.findNameByUsername(loginname);
			List<String> permissionNames = sysPermissionMapper.findPermissionNamesByRoleNames(roleNames);
			HashMap data = new HashMap(6);
			data.put("token",jwtToken);
			data.put("username",userName);
			data.put("loginname", loginname);
			data.put("rolenames", roleNames);
			data.put("permissions",permissions);
			data.put("permissionNames",permissionNames);
			return new RespMsg(ResultEnum.LOGIN_SUCCESS,data);
		} catch (UnknownAccountException uae) {
			logger.warn("对用户【{}】进行登录验证..验证未通过,未知账户：{}",loginname,uae.getMessage());
			errmessage = "未知账户";
		} catch (IncorrectCredentialsException ice) {
			logger.warn("对用户【{}】进行登录验证..验证未通过,错误的凭证：{}",loginname,ice.getMessage());
			errmessage = "密码不正确";
		} catch (LockedAccountException lae) {
			logger.warn("对用户【{}】进行登录验证..验证未通过,账户已锁定：{}",loginname,lae.getMessage());
			errmessage = "账户已锁定";
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			logger.warn("对用户【{}】进行登录验证..验证未通过,错误信息：{}",loginname,ae.getMessage());
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
		if (id ==  null){
			return new RespMsg(ResultEnum.HAS_NULL);
		}
		UserDTO userDTO = this.userInfoMapper.selectUserByUserId(id);
		UserVo uv = new UserVo(userDTO.getUserName(),userDTO.getNickName(),Integer.valueOf(userDTO.getRole()),userDTO.getState());
		return userDTO != null ? new RespMsg(ResultEnum.SELECT_SUCCESS,uv):
					new RespMsg(ResultEnum.SELECT_FAILD,uv);
	}
}
