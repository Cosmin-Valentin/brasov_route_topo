package com.primalsoup.topo.brasov_route_topo.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.primalsoup.topo.brasov_route_topo.Model.Route;

@Repository
public class RouteRepository {
	private final Map<String, Route> routeMap = new HashMap<>();
	
	public Collection<Route> findAll() {
		return routeMap.values();
	}
	
	public void save(Route route) {
		routeMap.put(route.getName(), route);
	}
}
