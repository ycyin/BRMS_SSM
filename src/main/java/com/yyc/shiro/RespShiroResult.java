package com.yyc.shiro;

/**
 * 
 * @ClassName:  RespShiroResult   
 * @Description:TODO(shiro权限这块的返回数据封装)   
 * @author: yinyicao
 * @date:   2019年6月12日 下午7:21:28   
 *     
 * @Copyright: 2019 www.yinyicao.work. All rights reserved. 
 *
 */
public class RespShiroResult {
	public Integer id; // 消息的唯一编号
	public Integer status; // 处理结果
	public String desc; // 描述
	public Object data; // 返回内容
	
	public RespShiroResult(ShiroResultEnum resEnum,Object data) {
		super();
		this.id = resEnum.getId();
		this.status = resEnum.getStatus();
		this.desc = resEnum.getDesc();
		this.data = data;
	}
	
	public RespShiroResult(ShiroResultEnum resEnum) {
		super();
		this.id = resEnum.getId();
		this.status = resEnum.getStatus();
		this.desc = resEnum.getDesc();
		this.data = resEnum.getData();
	}
	public RespShiroResult(Integer id, Integer status, String desc) {
		super();
		this.id = id;
		this.status = status;
		this.desc = desc;
	}
	
	public RespShiroResult(ShiroResultEnum resEnum, String desc, Object data) {
		super();
		this.id = resEnum.getId();
		this.status = resEnum.getStatus();
		this.desc = desc;
		this.data = data;
	}
	
	public RespShiroResult(Integer id, Integer status, String desc, Object data) {
		super();
		this.id = id;
		this.status = status;
		this.desc = desc;
		this.data = data;
	}
	
	
}
