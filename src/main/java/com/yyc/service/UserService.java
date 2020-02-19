/**
 * 
 */
package com.yyc.service;

import com.yyc.entity.UserInfo;
import com.yyc.vo.RespMsg;
import com.yyc.vo.request.LoginVo;

/**************************************
* @author 尹以操 E-mail:34782655@qq.com
* @version 创建时间：2017年6月23日 下午10:05:44
* 类说明:
***************************************
*/
public interface UserService {


//	RespMsg getALLUser();
//
//	RespMsg modifyUser(UserInfo userinfo);
//
//	RespMsg insertUser(UserInfo userinfo);
//
//	RespMsg removeUserByPrimaryKey(Integer id);

	//登录
	Object login(LoginVo login);

	//通过username查找用户信息
	UserInfo findByUsername(String username);
}
