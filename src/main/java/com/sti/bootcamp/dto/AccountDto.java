package com.sti.bootcamp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sti.bootcamp.PostCustomer;

public class AccountDto {
	
	private int accountNumber;
	@JsonFormat(pattern="yyyy-MM-dd")
	private String openDate;
	private String balance;
	private CustomerDto customer;
	
	public AccountDto() {}
	
	
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
	public CustomerDto getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}
}
