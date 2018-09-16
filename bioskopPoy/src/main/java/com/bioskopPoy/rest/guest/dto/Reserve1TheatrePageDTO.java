package com.bioskopPoy.rest.guest.dto;

import java.util.Date;

public class Reserve1TheatrePageDTO {
	private String theatreName;
	private Date date;
	private int duration;
	
	public Reserve1TheatrePageDTO() {
		
	}
	
	public String getTheatreName() {
		return theatreName;
	}

	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
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
