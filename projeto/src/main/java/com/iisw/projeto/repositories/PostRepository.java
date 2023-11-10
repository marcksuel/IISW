package com.iisw.projeto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iisw.projeto.models.entities.Post;


@Repository
public interface PostRepository extends JpaRepository<Post, String> {

	Optional<Post> findById(String id);
   

}
