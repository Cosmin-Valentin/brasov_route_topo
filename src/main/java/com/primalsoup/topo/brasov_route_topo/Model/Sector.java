package com.primalsoup.topo.brasov_route_topo.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Sector {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	@ManyToOne
	@JoinColumn(name = "zone_id")
	private Zone zone;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sector")
	private List<Route> routes = new ArrayList<>();

//	@Column(columnDefinition = "json")
//    @JdbcTypeCode(SqlTypes.JSON)
//	private Map<String, Object> routeDrawingData;
	@Lob
	private String routeDrawingData;
	
	public Sector() {
	}

	public Sector(String name) {
		this.name = name;
	}

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

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	public void addRoute(Route route) {
		this.routes.add(route);
		route.setSector(this);
	}

	public int getRouteCount() {
		return this.routes.size();
	}
	
	public String getRouteDrawingData() {
        return routeDrawingData;
    }

    public void setRouteDrawingData(String routeDrawingData) {
        this.routeDrawingData = routeDrawingData;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Sector sector = (Sector) o;
		return name.equals(sector.name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

}
