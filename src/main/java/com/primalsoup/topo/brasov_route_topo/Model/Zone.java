package com.primalsoup.topo.brasov_route_topo.Model;

import java.util.ArrayList;
import java.util.List;

public class Zone {

	private String name;
	private List<Sector> sectors = new ArrayList<>();

	public Zone(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public List<Sector> getSectors() {
		return sectors;
	}
	
	public void addSector(Sector sector) {
		sectors.add(sector);
	}
}
