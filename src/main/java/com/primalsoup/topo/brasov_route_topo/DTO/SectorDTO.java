package com.primalsoup.topo.brasov_route_topo.DTO;

import java.util.ArrayList;
import java.util.List;

public class SectorDTO {
    private String name;
    private List<RouteDTO> routes = new ArrayList<>();

    public SectorDTO(String name) {
        this.name = name;
    }

    public void addRoute(RouteDTO route) {
        this.routes.add(route);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RouteDTO> getRoutes() {
        return routes;
    }

    public void setRoutes(List<RouteDTO> routes) {
        this.routes = routes;
    }
}