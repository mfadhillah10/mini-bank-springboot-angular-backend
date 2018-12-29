package com.sti.bootcamp.dao.interfc;

import java.util.List;

import com.sti.bootcamp.error.ExceptionTemp;
import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Transaction;

public interface TransactionDao {
	
	Transaction getById(int id) throws ExceptionTemp;
	Transaction save(Transaction transaction) throws ExceptionTemp;
	void delete(Transaction transaction) throws ExceptionTemp;
	List<Transaction> getList() throws ExceptionTemp;
	List<Transaction> getListByAccount(Account account) throws ExceptionTemp;

}
