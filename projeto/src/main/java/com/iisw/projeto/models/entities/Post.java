package com.iisw.projeto.models.entities;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tb_post")
public class Post implements Serializable{

	private static final long serialVersionUID = 1L;

	@Schema(description = "Automatically generated user_id", example = "ae45b37e-9fa1-4f9e-8bc7-a93d9ce2e7e2")
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	private String post_id;

	@ManyToOne
	@JoinColumn(name ="user_id",nullable = false)
	private User owner;
	
	@Schema(description = "action post moment in ISO-8601 format", example = "2012-04-21T18:25:43-03:00")
	private ZonedDateTime post_time;
	
	private String post;
	@Schema(description = "Number of likes on the post", example = "12")
	private Integer likes;
	
	@OneToMany(mappedBy = "post")
	List<Comment> comments = new ArrayList<>();

	public Post(User owner, String post ) {
		this.owner = owner;
		this.post_time = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
		this.post = post;
		this.likes = 0;
	}
	public Post() {
		likes = 0;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getPost_id() {
		return post_id;
	}

	public ZonedDateTime getPost_time() {
		return post_time;
	}

	public String getPost() {
		return post;
	}

	public Integer getLikes() {
		return likes;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setPost_id(String id) {
		this.post_id = id;
	}

	

	public void setPost_time(ZonedDateTime post_time) {
		this.post_time = post_time;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
}
