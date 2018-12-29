package com.sti.bootcamp.dao.interfc;

import java.util.List;

import com.sti.bootcamp.PostCustomer;
import com.sti.bootcamp.error.ExceptionTemp;
import com.sti.bootcamp.model.Account;

public interface AccountDao {
	
	Account getById(int custnumber) throws ExceptionTemp;
	Account save(Account customer) throws ExceptionTemp;
	void delete(Account customer) throws ExceptionTemp;
	List<Account> getList() throws ExceptionTemp;
	List<Account> getListByCustomer(PostCustomer customer) throws ExceptionTemp;

}
