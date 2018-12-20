package com.sti.bootcamp.dao.interfc;

import java.util.List;

import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Transaction;

public interface TransactionDao {
	
	Transaction getById(int id) throws Exception;
	Transaction save(Transaction transaction) throws Exception;
	void delete(Transaction transaction) throws Exception;
	List<Transaction> getList() throws Exception;
	List<Transaction> getListByAccount(Account account) throws Exception;

}
