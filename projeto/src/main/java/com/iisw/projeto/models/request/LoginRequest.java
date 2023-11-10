package com.iisw.projeto.models.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class LoginRequest {
	
	@Schema(description = "The user's unique username.", example = "johndoe_123")
	private String username;

	@Schema(description = "The user's password.", example = "P@ssw0rd123")
	private String password;
	
	public LoginRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public LoginRequest() {
	
	}
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
