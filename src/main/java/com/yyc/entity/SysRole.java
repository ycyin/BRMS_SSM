package com.yyc.entity;

public class SysRole {
    Integer id;
    String role;//角色标识程序中判断使用,如"admin",这个是唯一的:
    String description; // 角色描述,UI界面显示使用
    private Byte available; // 是否可用,如果不可用将不会添加给用户
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Byte getAvailable() {
		return available;
	}

	public void setAvailable(Byte available) {
		this.available = available;
	}
}