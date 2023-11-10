package com.iisw.projeto.services;

import java.security.Permissions;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.iisw.projeto.models.entities.Comment;
import com.iisw.projeto.models.entities.Post;
import com.iisw.projeto.models.entities.User;
import com.iisw.projeto.models.request.PostRequest;
import com.iisw.projeto.models.response.ExceptionsResponse.ErrosCode;
import com.iisw.projeto.models.response.ExceptionsResponse.NotAvaible;
import com.iisw.projeto.models.response.ExceptionsResponse.NotFound;
import com.iisw.projeto.models.response.CommentResponse;
import com.iisw.projeto.models.response.FeedResponse;
import com.iisw.projeto.models.response.PostResponse;
import com.iisw.projeto.models.response.UserResponse;
import com.iisw.projeto.repositories.CommentRepository;
import com.iisw.projeto.repositories.PostRepository;
import com.iisw.projeto.services.exceptions.FailException;
import com.iisw.projeto.services.exceptions.PermissionException;
import com.iisw.projeto.services.exceptions.ResourceNotFoundException;
import com.iisw.projeto.services.exceptions.TokenException;

@Service
public class CommentService {

	@Autowired
	UserService userService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	CommentRepository repository;
	
	
	
	@Transactional
	public void add(String authorizationHeader, String id ,PostRequest request) {
		if(request.getContent()==null || request.getContent().length()>255) {
			throw new PermissionException(ErrosCode.POST);
		}
		User user = userService.loadUserSession(authorizationHeader);
		//TokenException(ErrosCode.TOKEN )
		Post post = postService.findByid(id);
		//ResourceNotFoundException(NotFound.POST, id));
		
		Comment comment = new Comment(user.getUser_id(),request.getContent());
		comment.setPost(post);
		
		try {
			repository.save(comment);
		}catch (Exception e) {
			throw new FailException(NotAvaible.COMMENT);
		}
		
	}
	
	public Comment findByid(String id) {
		return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NotFound.COMMENT, id));
	}
	
	public CommentResponse get(String id) {
		Comment comment =  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NotFound.COMMENT, id));
		User user = userService.getUserById(id);
		
		return new CommentResponse(comment, user);
	}
	
	@Transactional
	public void delete(String authorizationHeader, String id) {
		
		User user = userService.loadUserSession(authorizationHeader);
		//TokenException(ErrosCode.TOKEN )
		Comment comment = findByid(id);
		
		if(user.getUser_id().equals(comment.getUser_id())) {
			repository.delete(comment);
		}else {
			throw new PermissionException(ErrosCode.POST_PERMISSION);
		}
		
	}
	
	@Transactional
	public void edit(String authorizationHeader,  String id, PostRequest request) {
		if(request.getContent()==null || request.getContent().length()>255) {
			throw new PermissionException(ErrosCode.POST);
		}
		User user = userService.loadUserSession(authorizationHeader);
		//TokenException(ErrosCode.TOKEN )
		Comment comment = findByid(id);
		
		if(user.getUser_id().equals(comment.getUser_id())) {
			comment.setComment(request.getContent());
			repository.save(comment);
		}else {
			throw new PermissionException(ErrosCode.POST_PERMISSION);
		}
		
	}
}
