package com.yyc.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明: shiro MD5加密工具类
 ***************************************
 */
public class MD5Encryption {

	/**
	 * password:待加密的密码 salt:加密的盐 return:盐值加密后的密码
	 */
	public static String Encryption(String salt, String password) {
		ByteSource saltSource = null;
		if (salt != null) {
			saltSource = ByteSource.Util.bytes(salt);
		}
		// 2次hash计算加密后的密码
		return new SimpleHash("MD5", password, saltSource, 2).toString();
	}
}