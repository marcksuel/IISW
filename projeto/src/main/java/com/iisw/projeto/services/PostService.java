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
import com.iisw.projeto.repositories.PostRepository;
import com.iisw.projeto.services.exceptions.FailException;
import com.iisw.projeto.services.exceptions.PermissionException;
import com.iisw.projeto.services.exceptions.ResourceNotFoundException;
import com.iisw.projeto.services.exceptions.TokenException;

@Service
public class PostService {

	@Autowired
	UserService userService;

	@Autowired
	PostRepository repository;
	
	public Page<FeedResponse> feed(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = repository.findAll(pageable);

        return posts.map(post -> {
            FeedResponse postResponse = new FeedResponse();
            postResponse.setPostId(post.getPost_id());
            postResponse.setPostTime(post.getPost_time());
            postResponse.setPost(post.getPost());
            postResponse.setLikes(post.getLikes());

            UserResponse userResponse = new UserResponse(post.getOwner());
            postResponse.setOwner(userResponse);

            int numberOfComments = post.getComments().size();
            postResponse.setNumberOfComments(numberOfComments);

            return postResponse;
        });
    }
	
	public PostResponse postId(String id) {
		Post post = findByid(id);
	    List<CommentResponse> comments = new ArrayList<>();
		if(!post.getComments().isEmpty()) {
			for(Comment x: post.getComments()) {
				User user = userService.getUserById(x.getUser_id());
				comments.add(new CommentResponse(x, user));
			}
		}
		return new PostResponse(post,comments);
	}
	
	@Transactional
	public void add(String authorizationHeader, PostRequest request) {
		if(request.getContent()==null || request.getContent().length()>255) {
			throw new PermissionException(ErrosCode.POST);
		}
		User user = userService.loadUserSession(authorizationHeader);
		//TokenException(ErrosCode.TOKEN )
		Post post = new Post(user,request.getContent());
		try {
			repository.save(post);
		}catch (Exception e) {
			throw new FailException(NotAvaible.POST);
		}
		
	}
	
	public Post findByid(String id) {
		return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NotFound.POST, id));
	}
	
	@Transactional
	public void delete(String authorizationHeader, String id) {
		
		User user = userService.loadUserSession(authorizationHeader);
		//TokenException(ErrosCode.TOKEN )
		Post post = findByid(id);
		
		if(user.getPosts().contains(post)) {
			repository.delete(post);
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
		Post post = findByid(id);
		
		if(user.getPosts().contains(post)) {
			post.setPost(request.getContent());
			repository.save(post);
		}else {
			throw new PermissionException(ErrosCode.POST_PERMISSION);
		}
		
	}
	
	@Transactional
	public void create(String authorizationHeader, PostRequest request) {

		if(request.getContent()==null || request.getContent().length()>255) {
			throw new PermissionException(ErrosCode.POST + request.getContent());
		}
		User user = userService.loadUserSession(authorizationHeader);
		repository.save(new  Post(user,request.getContent()));
	}
}
