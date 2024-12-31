package com.primalsoup.topo.brasov_route_topo.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.primalsoup.topo.brasov_route_topo.Model.Sector;

public interface SectorRepository extends CrudRepository<Sector, Long> {
    Optional<Sector> findByName(String name);
    Optional<Sector> findById(Long id);
}