package com.iisw.projeto.models.response;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.iisw.projeto.models.entities.Comment;
import com.iisw.projeto.models.entities.Post;
import com.iisw.projeto.models.entities.User;

import io.swagger.v3.oas.annotations.media.Schema;

public class PostResponse {
	@Schema(description = "Automatically generated user_id", example = "ae45b37e-9fa1-4f9e-8bc7-a93d9ce2e7e2")
	private String post_id;
	
		
	@Schema(implementation = UserResponse.class)
	private UserResponse user;
	
	@Schema(description = "action post moment in ISO-8601 format", example = "2012-04-21T18:25:43-03:00")
	private ZonedDateTime post_time;
	
	private String content;
	
    private Integer likes;
    
    private Integer numberOfComments;

    List<CommentResponse> comments = new ArrayList<>();
	
	public PostResponse(Post post, List<CommentResponse> comments) {
		this.post_id = post.getPost_id();
		this.post_time = post.getPost_time();
		this.content = post.getPost();
		this.user = new UserResponse(post.getOwner());
		this.comments = comments;
	}
	
	
	public PostResponse() {
	}


	public String getPost_id() {
		return post_id;
	}

	public UserResponse getUser() {
		return user;
	}

	public ZonedDateTime getPost_time() {
		return post_time;
	}

	public String getContent() {
		return content;
	}

	public Integer getLikes() {
		return likes;
	}

	public Integer getNumberOfComments() {
		return numberOfComments;
	}

	public List<CommentResponse> getComments() {
		return comments;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}

	public void setUser(UserResponse user) {
		this.user = user;
	}

	public void setPost_time(ZonedDateTime post_time) {
		this.post_time = post_time;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public void setNumberOfComments(Integer numberOfComments) {
		this.numberOfComments = numberOfComments;
	}

	public void setComments(List<CommentResponse> comments) {
		this.comments = comments;
	}
	
	
}
