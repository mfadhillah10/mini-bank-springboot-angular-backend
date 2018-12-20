package com.sti.bootcamp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sti.bootcamp.PostCustomer;

@Entity
@Table(name="account")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column
	private int accountNumber;
	@Column
	private String openDate;
	@Column
	private String balance;
	@ManyToOne
	@JoinColumn(name="cust_number")
	private PostCustomer customer;
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public PostCustomer getCustomer() {
		return customer;
	}
	public void setCustomer(PostCustomer customer) {
		this.customer = customer;
	}
}