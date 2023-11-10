package com.iisw.projeto.models.embedding;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "User login information")
@Embeddable
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "The user's unique username.", example = "johndoe_123")
	@Column(nullable = false, unique = true)
	private String username;

    @Schema(description = "BCrypt-encrypted password")
	private String password;

	public Login(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Login() {
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

	@Override
	public String toString() {
		return "Login [username=" + username + ", password=" + password + "]";
	}

}
