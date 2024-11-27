package com.primalsoup.topo.brasov_route_topo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RouteForm {
	
	private String sector;

	@NotBlank(message = "Numele rutei este necesar")
	@Size(max = 50, message = "Pastreaza numele sub 50 de caractere")
	private String name;

	@NotBlank(message = "Dificultatea rutei este necesara")
	private String difficulty;

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

	public String getSector() {
		return sector;
	}

}
