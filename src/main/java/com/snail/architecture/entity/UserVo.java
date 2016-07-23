package com.snail.architecture.entity;

import java.io.Serializable;

/**
 * 用户登录信息
 * @author snail
 *
 */
public class UserVo implements Serializable{
	private static final long serialVersionUID = 8026030346406690045L;
	public String username;
	public String password;
	public boolean rememberMe;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isRememberMe() {
		return rememberMe;
	}
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	} 
	
}
