package com.sti.bootcamp.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sti.bootcamp.PostCustomer;
import com.sti.bootcamp.model.Account;

public interface AccountRepository extends PagingAndSortingRepository<Account, Integer> {
	Account findByAccountNumber(int accountNumber);
	List<Account> findByCustomer(PostCustomer customer);
}
