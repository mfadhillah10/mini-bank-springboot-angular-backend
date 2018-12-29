package com.sti.bootcamp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDto {
	
	@JsonProperty("custnumber")
	private int customernumber;
	private String firstname;
	private String lastname;
	@JsonFormat(pattern="yyyy-MM-dd")
	private String birthdate;
	private String username;
	private String password;
	private String phonenumber;
	private String phonetype;
	
	public CustomerDto() {}
	public CustomerDto(String firstName, String lastName, String birthDate, String username, String password, String phoneNumber, String phoneType) {
		this.firstname = firstName;
		this.lastname = lastName;
		this.birthdate = birthDate;
		this.username = username;
		this.password = password;
		this.phonenumber = phoneNumber;
		this.phonetype = phoneType;
	}
	
	public int getCustomernumber() {
		return customernumber;
	}
	public void setCustomernumber(int customernumber) {
		this.customernumber = customernumber;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getPhonetype() {
		return phonetype;
	}
	public void setPhonetype(String phonetype) {
		this.phonetype = phonetype;
	}
}
