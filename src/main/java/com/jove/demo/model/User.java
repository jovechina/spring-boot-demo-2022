package com.jove.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	@Column(name="userId")
	private int userId;
	
	@Size(min=2, max=30,message = "用户名长度不正确")
	@NotBlank(message = "用户名不能为空")
	@Column(name="userName")
	private String userName;
	
	@Size(min=4, max=30,message = "密码长度不正确")
	@NotBlank(message = "密码不能为空")
	private String password;
	private String role;
	
	@Transient
	private String Token;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
