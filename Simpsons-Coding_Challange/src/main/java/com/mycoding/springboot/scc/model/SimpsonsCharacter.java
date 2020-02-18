package com.mycoding.springboot.scc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SIMPSONS_CHARACTER")
public class SimpsonsCharacter {

	@Id
	@Column(name = "id")   
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "picture")
	String picture;

	@Column(name = "age")
	int age;

	@Column(name = "quote")
	String quote;
	
	
	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "SimpsonsCharacter{" + ", id='" + id + '\'' + "firstName='" + firstName + '\'' + ", lastName='"
				+ lastName + '\'' + ", picture='" + picture + '\'' + ", age='" + age + '\'' + '}';
	}

}
