package com.primalsoup.topo.brasov_route_topo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.primalsoup.topo.brasov_route_topo.Model.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
	boolean existsByNameAndSectorName(String name, String sectorName);

	@Query("SELECT r FROM Route r ORDER BY r.id DESC")
	List<Route> findRoutesByCreatedAtDate();
}