package com.yyc.service.impl;

import com.yyc.utils.JWTUtils;
import com.yyc.vo.RespMsg;
import com.yyc.vo.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yyc.dao.UserInfoMapper;
import com.yyc.entity.UserInfo;
import com.yyc.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**************************************
* @author 尹以操 E-mail:34782655@qq.com
* @version 创建时间：2017年6月23日 下午10:10:43
* 类说明:
***************************************
*/
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfoMapper userInfoDao;
	
	/**
	 * create by yyc 2017年6月23日下午10:10:43
	 */
	@Override
	public RespMsg getUserById(Integer userId) {
		RespMsg  rm = null;
		if (userId == null) {
			return new RespMsg(ResultEnum.HAS_NULL,userId);
		}
		UserInfo userInfo = this.userInfoDao.selectByPrimaryKey(userId);
		return new RespMsg(ResultEnum.SELECT_SUCCESS,userInfo);
	}

	@Override
	public RespMsg getUserByName(UserInfo user) {
		RespMsg  rm = null;
		UserInfo resUser = this.userInfoDao.selectByName(user.getUsername());
		if (resUser !=null){
			if (resUser.getPassword().equals(user.getPassword())){
				// 生成token 有效时间 600 秒
				String token = JWTUtils.createJWT(600,resUser.getUsername().trim());
				Map<String,Object> map = new HashMap<>();
				map.put("user",resUser);
				map.put("token",token);
				rm = new RespMsg(ResultEnum.LOGIN_SUCCESS,map);
			}else {
				rm = new RespMsg(ResultEnum.NAMEORPASS_EXCEPTION,resUser);
			}
		}else{
			rm = new RespMsg(ResultEnum.NAMEORPASS_EXCEPTION,user);
		}
		return rm;
	}

	@Override
	public RespMsg getALLUser() {
		List<UserInfo> userInfos = this.userInfoDao.selectAllUser();
		return new RespMsg(ResultEnum.SELECT_SUCCESS,userInfos);
	}

	@Override
	public RespMsg modifyUser(UserInfo userinfo) {
		Integer res = 0;
		try{
			res = this.userInfoDao.updateByPrimaryKey(userinfo);
			return res >=1?new RespMsg(ResultEnum.UPDATE_SUCCESS,res):new RespMsg(ResultEnum.UPDATE_FAILD,res);
		}catch (Exception e){
			if(res > 0){
				return new RespMsg(ResultEnum.UPDATE_SUCCESS,res);
			}
			if(e.getCause().toString().contains("Duplicate")){
				return new RespMsg(ResultEnum.UPDATE_FAILD_HAS_USER_DUPLICATE,res);
			}else{
				return new RespMsg(ResultEnum.UPDATE_FAILD_UNKNOW,res);
			}
		}

	}

	@Override
	public RespMsg insertUser(UserInfo userinfo) {
		Integer res = 0;
		try{
			res = this.userInfoDao.insertUser(userinfo);
			return res >=1?new RespMsg(ResultEnum.ADD_SUCCESS,res):new RespMsg(ResultEnum.ADD_SUCCESS,res);
		}catch (Exception e){
			if(res > 0){
				return new RespMsg(ResultEnum.ADD_SUCCESS,res);
			}
			if(e.getCause().toString().contains("Duplicate")){
				return new RespMsg(ResultEnum.ADD_FAILD_HAS_USER_DUPLICATE,res);
			}else{
				return new RespMsg(ResultEnum.ADD_FAILD_UNKNOW,res);
			}
		}
	}

	@Override
	public RespMsg removeUserByPrimaryKey(Integer id) {
		Integer res = this.userInfoDao.deleteByPrimaryKey(id);
		return res >= 1 ? new RespMsg(ResultEnum.DELETE_SUCCESS,res):
				new RespMsg(ResultEnum.DELETE_FAILD,res);
	}


}
