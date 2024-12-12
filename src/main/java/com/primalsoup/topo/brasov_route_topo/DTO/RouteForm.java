package com.primalsoup.topo.brasov_route_topo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RouteForm {

    private String zone;
    private String newZone;
    private String sector;
    private String newSector;

    @NotBlank(message = "Numele rutei este necesar")
    @Size(max = 50, message = "Păstrează numele sub 50 de caractere")
    private String name;

    @NotBlank(message = "Dificultatea rutei este necesară")
    private String difficulty;
    
    @NotNull(message = "Lungimea rutei este necesară")
    private Integer length;
    
    @NotNull(message = "Numărul de bucle este necesar")
	private Integer quickDraws;
    
    @NotNull(message = "Frumusețea rutei este necesară")
	private Integer rating;
    
    public RouteForm() {
    }

    public RouteForm(String zone, String newZone, String sector, String newSector, String name, String difficulty, Integer length, Integer quickDraws, Integer rating) {
        this.zone = zone;
        this.newZone = newZone;
        this.sector = sector;
        this.newSector = newSector;
        this.name = name;
        this.difficulty = difficulty;
        this.length = length;
        this.quickDraws = quickDraws;
        this.rating = rating;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getNewZone() {
        return newZone;
    }

    public void setNewZone(String newZone) {
        this.newZone = newZone;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getNewSector() {
        return newSector;
    }

    public void setNewSector(String newSector) {
        this.newSector = newSector;
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
    
    public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getQuickDraws() {
		return quickDraws;
	}

	public void setQuickDraws(Integer quickDraws) {
		this.quickDraws = quickDraws;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

    @Override
	public String toString() {
		return "RouteForm [zone=" + zone + ", newZone=" + newZone + ", sector=" + sector + ", newSector=" + newSector
				+ ", name=" + name + ", difficulty=" + difficulty + ", length=" + length + ", quickDraws=" + quickDraws
				+ ", rating=" + rating + "]";
	}

	public ZoneDTO toZoneDTO() {
        ZoneDTO zoneDTO = new ZoneDTO(zone != null ? zone : newZone);
        if (sector != null || newSector != null) {
            SectorDTO sectorDTO = new SectorDTO(sector != null ? sector : newSector);
            sectorDTO.addRoute(new RouteDTO(name, difficulty, length, quickDraws, rating));
            zoneDTO.addSector(sectorDTO);
        }
        return zoneDTO;
    }
}