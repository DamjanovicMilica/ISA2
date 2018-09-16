package com.bioskopPoy.rest.guest.dto;

public class HomePageCinemasDTO {

	private String date; 
	private String cinemaName; 
	
	public HomePageCinemasDTO() {
		
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
}
