package com.bioskopPoy.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRoles {
	GUEST(0);
	
	private int value;
	
	private UserRoles(int value) {
		this.value = value;
	}
	
	 @JsonValue
	 public int getValue() {
	        return value;
	 }
	 
	 public void setValue(int value) {
		 this.value = value;
	 }
	 
}