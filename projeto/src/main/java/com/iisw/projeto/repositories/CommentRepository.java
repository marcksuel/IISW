package com.iisw.projeto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iisw.projeto.models.entities.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

	Optional<Comment> findById(String id);
   

}
