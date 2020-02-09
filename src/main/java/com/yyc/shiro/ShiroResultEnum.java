package com.yyc.shiro;


/**
 * 
 * @ClassName:  ResultEnum   
 * @Description:TODO(结果集，用在权限管理这块)   
 * @author: yinyicao
 * @date:   2019年6月12日 下午7:09:45   
 *     
 * @Copyright: 2019 www.yinyicao.work. All rights reserved. 
 *
 */
public enum ShiroResultEnum {
	UNKNOW_ERROR(-1,500,"未知错误"),
	ERROR(-2,500),
	UNAUTHORIZED(1,401,"对不起，您无权限进行当前操作"),
	LOGIN_SUCCESS(2,2000,"登录成功！"),
	LOGINED(3,2001,"已登录"),
	UNLOGIN(4,2002,"未登录！"),
	NAMEORPASS_EXCEPTION(5,2003,"登录失败，用户名或密码错误"),
	LOGIN_NAMEORPASS_NULL(6,2004,"登录失败，用户名或密码为空"),
	LOGOUT_SUCCESS(7,2005,"退出登录成功！"),
	;
	private Integer id;//编号
	private Integer status;//状态码编号
	private String desc;//描述
	private Object data;//数据
	
	private ShiroResultEnum() {
	}
	private ShiroResultEnum(Integer id, Integer status) {
		this.id = id;
		this.status = status;
	}
	private ShiroResultEnum(Integer id, Integer status, String desc) {
		this.id = id;
		this.status = status;
		this.desc = desc;
	}
	private ShiroResultEnum(Integer id, Integer status, String desc, Object data) {
		this.id = id;
		this.status = status;
		this.desc = desc;
		this.data = data;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
