package com.iisw.projeto.models.request;

import java.time.ZonedDateTime;

import javax.persistence.Embedded;

import com.iisw.projeto.models.embedding.Login;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserRegisterRequest {
	
    
	@Schema(description = "User's name", example = "Tereza")
	private String nome;
	
	@Schema(description = "User's lastname", example = "Maria Lopes")
	private String sobrenome;
	
	@Schema(description = "User's email address", example = "tereza@gmail.com")
	private String email;
	
	@Schema(description = "User's birth date in ISO-8601 format", example = "2012-04-21T18:25:43-03:00")
	private ZonedDateTime birthday;


	@Schema(implementation = Login.class)	
	@Embedded
	private Login login;

	

	public UserRegisterRequest(String nome, String sobrenome, String email, ZonedDateTime birthday, Login login) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.birthday = birthday;
		this.login = login;
	}

	public UserRegisterRequest() {
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public ZonedDateTime getBirthday() {
		return birthday;
	}

	public Login getLogin() {
		return login;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBirthday(ZonedDateTime birthday) {
		this.birthday = birthday;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	
	
	


}
