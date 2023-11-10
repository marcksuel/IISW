package com.iisw.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.iisw.projeto.models.embedding.Token;
import com.iisw.projeto.models.entities.User;
import com.iisw.projeto.models.request.LoginRequest;
import com.iisw.projeto.models.request.UserRegisterRequest;
import com.iisw.projeto.models.response.UserResponse;
import com.iisw.projeto.services.TokenService;
import com.iisw.projeto.services.UserService;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")

public class UserController {

	@Autowired
	private UserService service;
	@Autowired
	private TokenService tokenService;
	
	
	@Tag(name = "User Controller - Free route")
	@PostMapping("/login")
	public ResponseEntity<Token> login(@RequestBody @JsonInclude(JsonInclude.Include.NON_NULL) LoginRequest login) {
		HttpHeaders responseHeaders = new HttpHeaders();
		String authorization = "Bearer " + tokenService.createToken(login);
		Token token = new Token(authorization);
		responseHeaders.set("Authorization", authorization);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).headers(responseHeaders).body(token);

	}

	@Tag(name = "User Controller - Free route")
	@PostMapping("/register")
	public ResponseEntity<Void> register(
			@RequestBody @JsonInclude(JsonInclude.Include.NON_NULL) UserRegisterRequest user) {
		return ResponseEntity.created(service.save(user)).contentType(MediaType.APPLICATION_JSON).build(); // codigo 201
	}

	@Tag(name = "User Controller - Free route")
	@GetMapping
	public ResponseEntity<String> home() {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("Bem vindo");
	}

	@Tag(name = "User Controller")
	@GetMapping("/whoAmI")
	public ResponseEntity<UserResponse> whoAmI(@RequestHeader("Authorization") String authorizationHeader) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.whoAmI(authorizationHeader));
	}
	
	@Tag(name = "User Controller")
	@DeleteMapping("/user/delete")
	public ResponseEntity<Void> delete(@RequestHeader("Authorization") String authorizationHeader) {
		service.delete(authorizationHeader);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).build();
	}
	


}
