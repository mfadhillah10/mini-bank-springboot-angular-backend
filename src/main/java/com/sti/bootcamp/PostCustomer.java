package com.sti.bootcamp;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class PostCustomer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column(name = "custnumber")
	private int customernumber;
	
	@Column
	private String firstname;
	
	@Column
	private String lastname;
	
	@Column
	private String birthdate;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String phonenumber;
	
	@Column
	private String phonetype;
	
	
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
	public int getCustnumber() {
		return customernumber;
	}
	public void setCustnumber(int cust_number) {
		this.customernumber = cust_number;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String first_name) {
		this.firstname = first_name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLast_name(String last_name) {
		this.lastname = last_name;
	}
}
