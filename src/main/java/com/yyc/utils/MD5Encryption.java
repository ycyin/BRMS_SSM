package com.yyc.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * shiro MD5加密
 */
public class MD5Encryption {

	/**
	 * 
	 * create by yyc
	 * 
	 * password:待加密的密码 salt:加密的盐 return:盐值加密后的密码
	 */
	public static String Encryption(String salt, String password) {
		ByteSource saltSource = null;
		if (salt != null) {
			saltSource = ByteSource.Util.bytes(salt);
		}
		// 2次hash计算加密后的密码
		String md = new SimpleHash("MD5", password, saltSource, 2).toString();
		return md;
	}
}