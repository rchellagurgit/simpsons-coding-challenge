package com.mycoding.springboot.scc.model;

import org.hibernate.validator.constraints.NotBlank;

public class SearchCriteria {

	@NotBlank(message = "Simpson Character can't empty!")
	String firstName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstname(String firstName) {
		this.firstName = firstName;
	}
	@NotBlank(message = "Simpson Character can't empty!")
	String lastName;

	public String getLastName() {
		return lastName;
	}

	public void setLastname(String lastName) {
		this.lastName = lastName;
	}
}
