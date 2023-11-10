package com.iisw.projeto.models.entities;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Comment post")
@Entity
@Table(name = "tb_comment")
public class Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Schema(description = "Automatically generated user_id", example = "ae45b37e-9fa1-4f9e-8bc7-a93d9ce2e7e2")
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String comment_id;
	
	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
	@Schema(implementation = Post.class)
	private Post post;
	
	@Schema(description = "User_id owner comment", example = "ae45b37e-9fa1-4f9e-8bc7-a93d9ce2e7e2")
	private String user_id;
	
	@Schema(description = "action comment moment in ISO-8601 format", example = "2012-04-21T18:25:43-03:00")
	private ZonedDateTime comment_time;
	
	private String comment;
	
	public Comment(String user_id, String comment) {
		this.user_id = user_id;
		this.comment_time = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
		this.comment = comment;
	}

	public Comment() {
	}

	public String getComment_id() {
		return comment_id;
	}

	public Post getPost() {
		return post;
	}

	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getUser_id() {
		return user_id;
	}

	public ZonedDateTime getComment_time() {
		return comment_time;
	}

	public String getComment() {
		return comment;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setComment_time(ZonedDateTime comment_time) {
		this.comment_time = comment_time;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
