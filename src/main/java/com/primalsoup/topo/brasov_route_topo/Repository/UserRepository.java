package com.primalsoup.topo.brasov_route_topo.Repository;

import com.primalsoup.topo.brasov_route_topo.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}