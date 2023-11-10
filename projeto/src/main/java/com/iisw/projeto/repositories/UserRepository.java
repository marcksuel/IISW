package com.iisw.projeto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iisw.projeto.models.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findById(String id);
    User findByLogin_Username(String username);
    User findByEmail(String email);

}
