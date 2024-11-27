package com.primalsoup.topo.brasov_route_topo.Service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.primalsoup.topo.brasov_route_topo.Model.Route;
import com.primalsoup.topo.brasov_route_topo.Model.Sector;
import com.primalsoup.topo.brasov_route_topo.Repository.RouteRepository;

@Service
public class RouteService {
	private final RouteRepository routeRepository;

	public RouteService(RouteRepository routeRepository) {
		this.routeRepository = routeRepository;
	}

	public Collection<Sector> getAllSectors() {
		return routeRepository.findAllSectors();
	}

	public void addRouteToSector(String sectorName, Route route) {
		routeRepository.saveRouteToSector(sectorName, route);
	}
}
