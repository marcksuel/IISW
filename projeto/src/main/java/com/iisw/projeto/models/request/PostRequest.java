package com.iisw.projeto.models.request;

import java.io.Serializable;

public class PostRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String content;
	
	public PostRequest(String content) {
		this.content = content;
	}
	
	public PostRequest() {
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
