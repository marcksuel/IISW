package com.iisw.projeto.models.response;

import java.time.ZonedDateTime;

import org.hibernate.annotations.GenericGenerator;

import com.iisw.projeto.models.entities.User;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserResponse {
	
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String user_id;

	@Schema(description = "User's name", example = "Tereza")
	private String nome;
	
	@Schema(description = "User's lastname", example = "Maria Lopes")
	private String sobrenome;
	
	@Schema(description = "User's email address", example = "tereza@gmail.com")
	private String email;
	
	@Schema(description = "User's birth date in ISO-8601 format", example = "2012-04-21T18:25:43-03:00")
	private ZonedDateTime birthday;

	public UserResponse(User user) {
		this.user_id = user.getUser_id();
		this.nome = user.getNome();
		this.sobrenome = user.getSobrenome();
		this.email = user.getEmail();
		this.birthday = user.getBirthday();
	}	
	
	public String getUser_id() {
		return user_id;
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

	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	
	

}
