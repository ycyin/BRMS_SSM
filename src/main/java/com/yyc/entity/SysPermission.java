package com.yyc.entity;

public class SysPermission {
    private Integer id;//主键.
	private String name;//权限名称.
	private String permission; //权限字符串
	private String url;//资源路径.
	private byte available;//是否可用
	private String description;//权限描述


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public byte getAvailable() {
		return available;
	}

	public void setAvailable(byte available) {
		this.available = available;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}