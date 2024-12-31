package com.primalsoup.topo.brasov_route_topo.Configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.primalsoup.topo.brasov_route_topo.Entity.UserEntity;
import com.primalsoup.topo.brasov_route_topo.Repository.UserRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("utilizator").isEmpty()) {
                UserEntity user = new UserEntity();
                user.setUsername("utilizator");
                user.setPassword(passwordEncoder.encode("parola"));
                user.setRole("USER");
                userRepository.save(user);
            }
        };
    }
}
