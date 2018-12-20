package com.sti.bootcamp.dao.interfc;

import java.util.List;

import com.sti.bootcamp.PostCustomer;
import com.sti.bootcamp.model.Account;

public interface AccountDao {
	
	Account getById(int cust_number) throws Exception;
	Account save(Account customer) throws Exception;
	void delete(Account customer) throws Exception;
	List<Account> getList() throws Exception;
	List<Account> getListByCustomer(PostCustomer customer) throws Exception;

}
