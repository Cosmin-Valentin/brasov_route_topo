package com.primalsoup.topo.brasov_route_topo.DTO;

public class RouteDTO {
    private String name;
    private String difficulty;
    private Integer length;
	private Integer quickDraws;
	private Integer rating;

	public RouteDTO(String name, String difficulty, Integer length, Integer quickDraws, Integer rating) {
        this.name = name;
        this.difficulty = difficulty;
        this.length = length;
        this.quickDraws = quickDraws;
        this.rating = rating;
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

}