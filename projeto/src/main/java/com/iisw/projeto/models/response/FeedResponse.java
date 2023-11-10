package com.iisw.projeto.models.response;


import java.time.ZonedDateTime;

public class FeedResponse {
    private String postId;
    private ZonedDateTime postTime;
    private String post;
    private Integer likes;
    private UserResponse owner;
    private Integer numberOfComments;

    // Construtores, getters e setters

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public ZonedDateTime getPostTime() {
        return postTime;
    }

    public void setPostTime(ZonedDateTime postTime) {
        this.postTime = postTime;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public UserResponse getOwner() {
        return owner;
    }

    public void setOwner(UserResponse owner) {
        this.owner = owner;
    }

    public Integer getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(Integer numberOfComments) {
        this.numberOfComments = numberOfComments;
    }
}
