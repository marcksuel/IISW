package com.iisw.projeto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iisw.projeto.models.entities.Jwt;

@Repository
public interface JwtRepository extends JpaRepository<Jwt, String>{

	
}
