package com.primalsoup.topo.brasov_route_topo.DTO;

public class RouteDTO {
    private String name;
    private String difficulty;

    public RouteDTO(String name, String difficulty) {
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
}