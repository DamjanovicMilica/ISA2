package com.bioskopPoy.rest.guest.dto;

import com.bioskopPoy.model.UserRoles;

public class LoginResponseDTO {

	private long id; 
	private UserRoles role; 
	
	public LoginResponseDTO() {}

	public LoginResponseDTO(long id, UserRoles guest) {
		// TODO Auto-generated constructor stub
		this.id = id; 
		this.role = guest; 
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserRoles getRole() {
		return role;
	}

	public void setRole(UserRoles role) {
		this.role = role;
	}
}
