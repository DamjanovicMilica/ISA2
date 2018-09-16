package com.bioskopPoy.rest.guest.dto;

public class CinemasPageDTO {

	private String name;
	private String distance;
	private long id;
	
	public CinemasPageDTO() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
