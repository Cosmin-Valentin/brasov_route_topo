package com.primalsoup.topo.brasov_route_topo.Service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.primalsoup.topo.brasov_route_topo.Entity.UserEntity;
import com.primalsoup.topo.brasov_route_topo.Repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public void registerUser(String username, String password) {
		if(userRepository.findByUsername(username).isPresent()) {
			throw new IllegalArgumentException("Acest nume este deja luat.");
		}
		
		UserEntity user = new UserEntity();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setRole("USER");
		userRepository.save(user);
	}
	
}
