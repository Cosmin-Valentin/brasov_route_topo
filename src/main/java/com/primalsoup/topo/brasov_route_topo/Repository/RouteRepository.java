package com.primalsoup.topo.brasov_route_topo.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.primalsoup.topo.brasov_route_topo.Model.Route;
import com.primalsoup.topo.brasov_route_topo.Model.Sector;

@Repository
public class RouteRepository {
	
	private final Map<String, Sector> sectorMap = new HashMap<>();

	public Collection<Sector> findAllSectors() {
		return sectorMap.values();
	}

	public void saveRouteToSector(String sectorName, Route route) {
		Sector sector = sectorMap.get(sectorName);
		
		if(sector == null) {
			sector = new Sector(sectorName);
			sectorMap.put(sectorName, sector);
		}
		
		sector.addRoute(route);
	}
}
