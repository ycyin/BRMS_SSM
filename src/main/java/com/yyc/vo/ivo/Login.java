package com.yyc.vo.ivo;

public class Login {

	private String loginname;
	private String password;
	private Boolean rememberme;

	
	public Boolean getRememberme() {
		return rememberme;
	}

	public void setRememberme(Boolean rememberme) {
		this.rememberme = rememberme;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}