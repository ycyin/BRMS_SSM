/**
 * 
 */
package com.yyc.service;

import com.yyc.entity.UserInfo;
import com.yyc.vo.RespMsg;
import com.yyc.vo.request.LoginVo;
import com.yyc.vo.request.UserVo;

/**************************************
* @author 尹以操 E-mail:34782655@qq.com
* @version 创建时间：2017年6月23日 下午10:05:44
* 类说明:
***************************************
*/
public interface UserService {


	RespMsg getALLUser();
//
	RespMsg modifyUser(UserVo userVo);
//
	RespMsg insertUser(UserVo userVo);
//
	RespMsg removeUserAndRoleInfo(Integer id);

	//登录
	Object login(LoginVo login);

	//通过username查找用户信息
	UserInfo findByUsername(String username);

	//通过userId查找用户信息
	RespMsg findUserByUserId(Integer id);
}
