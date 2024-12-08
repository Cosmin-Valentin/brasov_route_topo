package com.primalsoup.topo.brasov_route_topo.DTO;

import java.util.ArrayList;
import java.util.List;

public class ZoneDTO {
    private String name;
    private List<SectorDTO> sectors = new ArrayList<>();

    public ZoneDTO(String name) {
        this.name = name;
    }

    public void addSector(SectorDTO sector) {
        this.sectors.add(sector);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SectorDTO> getSectors() {
        return sectors;
    }

    public void setSectors(List<SectorDTO> sectors) {
        this.sectors = sectors;
    }
}