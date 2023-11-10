package com.iisw.projeto.models.response;

import java.time.ZonedDateTime;

import com.iisw.projeto.models.entities.Comment;
import com.iisw.projeto.models.entities.User;

import io.swagger.v3.oas.annotations.media.Schema;

public class CommentResponse {
	@Schema(description = "Automatically generated user_id", example = "ae45b37e-9fa1-4f9e-8bc7-a93d9ce2e7e2")
	private String comment_id;
	
		
	@Schema(implementation = UserResponse.class)
	private UserResponse user;
	
	@Schema(description = "action comment moment in ISO-8601 format", example = "2012-04-21T18:25:43-03:00")
	private ZonedDateTime comment_time;
	
	private String content;
	
	public CommentResponse(Comment comment, User user) {
		this.comment_id = comment.getComment_id();
		this.comment_time = comment.getComment_time();
		this.content = comment.getComment();
		this.user = new UserResponse(user);
	}

	public String getComment_id() {
		return comment_id;
	}

	public UserResponse getUser() {
		return user;
	}

	public ZonedDateTime getComment_time() {
		return comment_time;
	}

	public String getContent() {
		return content;
	}

	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}

	public void setUser(UserResponse user) {
		this.user = user;
	}

	public void setComment_time(ZonedDateTime comment_time) {
		this.comment_time = comment_time;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
