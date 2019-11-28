package com.yyc.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**************************************
* @author 尹以操 E-mail:34782655@qq.com
* @version 创建时间：2017年6月23日 下午10:14:21
* 类说明:
***************************************
*/

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMyBatis {

	private static Logger logger = LoggerFactory.getLogger(TestMyBatis.class);
	
	@Resource
	private UserService userService;
	
	@Test
	public void test1(){
	}
}
