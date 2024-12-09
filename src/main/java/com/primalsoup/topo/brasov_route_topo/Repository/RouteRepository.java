package com.primalsoup.topo.brasov_route_topo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.primalsoup.topo.brasov_route_topo.Model.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
	boolean existsByNameAndSectorName(String name, String sectorName);
}