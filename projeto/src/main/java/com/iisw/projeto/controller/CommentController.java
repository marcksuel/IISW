package com.iisw.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iisw.projeto.models.request.PostRequest;
import com.iisw.projeto.models.response.CommentResponse;
import com.iisw.projeto.models.response.FeedResponse;
import com.iisw.projeto.models.response.PostResponse;
import com.iisw.projeto.services.CommentService;
import com.iisw.projeto.services.PostService;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")

public class CommentController {
	
	@Autowired
	CommentService service;

	
	@Tag(name = "Comment Controller")
	@GetMapping("/comment")
	public ResponseEntity<CommentResponse> postId( @RequestParam("post_id") String id) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.get(id));
	}
	
	@Tag(name = "Comment Controller")
	@PostMapping("/comment")
	public ResponseEntity<Void> create(@RequestHeader("Authorization") String authorizationHeader, @RequestParam("post_id") String id,@RequestBody PostRequest content) {
		service.add(authorizationHeader,id,content);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).build();
	}
	
	@Tag(name = "Comment Controller")
	@PutMapping("/comment")
	public ResponseEntity<Void> edit(@RequestHeader("Authorization") String authorizationHeader, @RequestParam("post_id") String id, @RequestBody PostRequest request) {
		service.edit(authorizationHeader,id,request);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).build();
	}
	
	@Tag(name = "Comment Controller")
	@DeleteMapping("/comment")
	public ResponseEntity<Void> delete(@RequestHeader("Authorization") String authorizationHeader, @RequestParam("post_id") String id) {
		service.delete(authorizationHeader,id);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).build();
	}
	
	//@RequestHeader("Authorization") String authorizationHeader
	
	
	
}
