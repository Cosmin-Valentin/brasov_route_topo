package com.primalsoup.topo.brasov_route_topo.Model;

public class Route {

	private String name;
	private String difficulty;

	public Route(String name, String difficulty) {
		this.name = name;
		this.difficulty = difficulty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Route route = (Route) o;
		return name.equals(route.name);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
