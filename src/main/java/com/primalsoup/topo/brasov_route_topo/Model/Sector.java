package com.primalsoup.topo.brasov_route_topo.Model;

import java.util.ArrayList;
import java.util.List;

public class Sector {
	
	private String name;
	private List<Route> routes = new ArrayList<>();
	
	public Sector(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Route> getRoutes() {
		return routes;
	}
	
	public void addRoute(Route route) {
		routes.add(route);
	}
	
}