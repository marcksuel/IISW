package com.iisw.projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.iisw.projeto.repositories.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;
	
		 
	@Override
	public void run(String... args) throws Exception { 
		

	}

}