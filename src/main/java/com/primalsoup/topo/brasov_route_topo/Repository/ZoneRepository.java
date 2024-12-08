package com.primalsoup.topo.brasov_route_topo.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.primalsoup.topo.brasov_route_topo.Model.Zone;

public interface ZoneRepository extends CrudRepository<Zone, Long> {
    Optional<Zone> findByName(String name);
}