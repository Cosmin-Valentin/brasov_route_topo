package com.primalsoup.topo.brasov_route_topo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String difficulty;

	@ManyToOne
	@JoinColumn(name = "sector_id") // Creați o legătură între Route și Sector
	private Sector sector;

	// Constructor implicit pentru JPA
	public Route() {
	}

	public Route(String name, String difficulty) {
		this.name = name;
		this.difficulty = difficulty;
	}

	// Getter și setter pentru id, name, difficulty și sector
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Route route = (Route) o;
		return name.equals(route.name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
