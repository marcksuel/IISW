package com.iisw.projeto.models.entities;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iisw.projeto.models.embedding.Login;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tb_user")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Schema(description = "Automatically generated user_id", example = "ae45b37e-9fa1-4f9e-8bc7-a93d9ce2e7e2")
	@Id
	@GeneratedValue(generator = "uuid2")
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

	@Schema(implementation = Login.class)
	@Embedded
	private Login login;
	
	
	@Schema(implementation = Post.class)
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Post> posts = new ArrayList<>();;

	public User(String nome, String sobrenome, String email, ZonedDateTime birthday, Login login) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.birthday = birthday;
		this.login = login;
	}
	
	public User() {
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

	public Login getLogin() {
		return login;
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

	public void setLogin(Login login) {
		this.login = login;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	// UserDetails
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return getLogin().getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return getLogin().getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	

	

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
