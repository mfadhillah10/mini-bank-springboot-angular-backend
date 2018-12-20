package com.sti.bootcamp.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Transaction;

public interface TrxRepository extends PagingAndSortingRepository<Transaction, Integer> {
	
	Transaction findByAccount(int accountNumber);
	List<Transaction> findByAccount(Account account);

}
