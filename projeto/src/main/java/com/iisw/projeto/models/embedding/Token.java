package com.iisw.projeto.models.embedding;


import io.swagger.v3.oas.annotations.media.Schema;

public class Token {

	@Schema(description = "Bearer token to be used for authentication.", example = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
	private String token;
	
	
	public Token(String token	) {
		this.token = token;

	}

	public String getToken() {
		return token;
	}
	
	
	public void setToken(String token) {
		this.token = token;
	}

}
