package com.bioskopPoy.rest.guest.dto;

import java.util.Date;

public class Reserve1CinemaPageDTO {
	private String cinemaName;
	private Date date;
	private int duration;
	
	public Reserve1CinemaPageDTO() {
		
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}
