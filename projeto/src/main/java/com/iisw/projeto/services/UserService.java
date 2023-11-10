package com.iisw.projeto.services;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iisw.projeto.models.embedding.Login;
import com.iisw.projeto.models.entities.User;
import com.iisw.projeto.models.request.UserRegisterRequest;
import com.iisw.projeto.models.response.ExceptionsResponse.ErrosCode;
import com.iisw.projeto.models.response.ExceptionsResponse.NotFound;
import com.iisw.projeto.models.response.UserResponse;
import com.iisw.projeto.repositories.UserRepository;
import com.iisw.projeto.services.exceptions.RegisterException;
import com.iisw.projeto.services.exceptions.ResourceNotFoundException;
import com.iisw.projeto.services.exceptions.TokenException;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AuthenticationService authenticationService;

	protected BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	

	public URI save(User user) {

		if (userRepository.findByLogin_Username(user.getUsername()) != null || user.getUsername() == null) {
			throw new RegisterException(ErrosCode.USERNAME);
		}
		if (userRepository.findByEmail(user.getEmail()) != null
				|| user.getEmail() == null) {
			throw new RegisterException(ErrosCode.EMAIL);
		}


		Login login = user.getLogin();
		login.setUsername(login.getUsername().toLowerCase());
		login.setPassword(passwordEncoder.encode(login.getPassword()));
		user.setLogin(login);
		user = userRepository.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getUser_id())
				.toUri();
		return uri;
	}

	@Transactional
	public URI save(UserRegisterRequest userRequest) {
		User user = convertUserRequestToUser(userRequest);

		if (userRepository.findByLogin_Username(user.getUsername()) != null || user.getUsername() == null) {
			throw new RegisterException(ErrosCode.USERNAME);
		}
		if (userRepository.findByEmail(user.getEmail()) != null
				|| user.getEmail() == null) {
			throw new RegisterException(ErrosCode.EMAIL);
		}

		Login login = user.getLogin();
		// criptografar senha 
		login.setPassword(passwordEncoder.encode(login.getPassword()));

		user.setLogin(login);
		URI newUri = null;

		user = userRepository.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/users/{id}").buildAndExpand(user.getUser_id())
				.toUri();

		String path = uri.getPath().replace("/register", ""); // Remove o segmento "/register" do caminho
		try {
			newUri = new URI(uri.getScheme(), uri.getAuthority(), path, uri.getQuery(), uri.getFragment());
		} catch (Exception e) {

		}


		return newUri;
	}

	@Transactional
	public User saveForService(User user) {
		return userRepository.save(user);
	}

	/**
	 * Validates a Brazilian CPF (Cadastro de Pessoas Físicas).
	 *
	 * @param cpf The CPF to be validated in the format "XXX.XXX.XXX-XX".
	 * @return True if the CPF is valid, false otherwise.
	 */
	public Boolean validateCPF(String cpf) {
		// Define the regular expression to validate a CPF
		String regex = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";

		// Compile the regular expression into a pattern
		Pattern pattern = Pattern.compile(regex);

		// Create a Matcher object for the input string
		Matcher matcher = pattern.matcher(cpf);

		// Check if the input string matches the pattern
		return matcher.matches();
	}

	public User convertUserRequestToUser(UserRegisterRequest userRequest) {
		
		return new User(userRequest.getNome().toUpperCase(), userRequest.getSobrenome().toUpperCase(), userRequest.getEmail().toLowerCase(), userRequest.getBirthday(),
				new Login(userRequest.getLogin().getUsername().toLowerCase(),userRequest.getLogin().getPassword()));
	}


	public UserResponse whoAmI(String authorizationHeader) {

		return new UserResponse(loadUserSession(authorizationHeader));
		//TokenException(ErrosCode.TOKEN );

	}

	public User loadUserSession(String authorizationHeader) throws TokenException {
		if (authorizationHeader != null) {
			String token = authorizationHeader.replace("Bearer ", "");
			var subject = tokenService.getSubject(token);
			var user = authenticationService.loadUserByUsername(subject);
			return (User) user;
		}
		throw new TokenException(ErrosCode.TOKEN );

	}
	
	@Transactional
	public void delete(String authorizationHeader) {
		User user = loadUserSession(authorizationHeader);
		//TokenException(ErrosCode.TOKEN );
		try {
			userRepository.delete(user);
		}catch (Exception e) {
			throw new RegisterException(ErrosCode.DELETE);
		}
	}

	 public User getUserById(String id) {
	        return userRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException(NotFound.USER, id));
	    }
	 
	 
}
