package com.primalsoup.topo.brasov_route_topo.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailsConfig {

	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
	    UserDetails user = User.builder()
	        .username("utilizator")
	        .password(passwordEncoder.encode("parola"))
	        .roles("USER")
	        .build();

	    return new InMemoryUserDetailsManager(user);
	}
}