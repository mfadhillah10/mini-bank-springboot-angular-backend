package com.sti.bootcamp.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.sti.bootcamp.PostCustomer;
import com.sti.bootcamp.dao.interfc.AccountDao;
import com.sti.bootcamp.dao.repository.AccountRepository;
import com.sti.bootcamp.model.Account;

public class AccountDaoImpl extends BaseImpl implements AccountDao{
	@Autowired
	private AccountRepository repository;
	
	@Override
	public Account getById(int cust_number) throws Exception {
		return repository.findByAccountNumber(cust_number);
	}

	@Override
	public Account save(Account customer) throws Exception {
		return repository.save(customer);
	}

	@Override
	public void delete(Account customer) throws Exception {
		repository.delete(customer);
	}

	@Override
	public List<Account> getList() throws Exception {
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Account> query = critB.createQuery(Account.class);
		Root<Account> root = query.from(Account.class);
		query.select(root);
		
		TypedQuery<Account> q = em.createQuery(query);
		return q.getResultList();
	}
	
	@Override
	public List<Account> getListByCustomer(PostCustomer customer) throws Exception {
		return repository.findByCustomer(customer);
	}

	
	

}
