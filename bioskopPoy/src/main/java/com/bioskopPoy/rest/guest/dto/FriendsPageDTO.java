package com.bioskopPoy.rest.guest.dto;

public class FriendsPageDTO {
	private String nameAndSurname;
	private long numberOfVisits;
	private long id; 
	
	public FriendsPageDTO() {
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNameAndSurname() {
		return nameAndSurname;
	}


	public void setNameAndSurname(String nameAndSurname) {
		this.nameAndSurname = nameAndSurname;
	}


	public long getNumberOfVisits() {
		return numberOfVisits;
	}

	public void setNumberOfVisits(long numberOfVisits) {
		this.numberOfVisits = numberOfVisits;
	}
}
