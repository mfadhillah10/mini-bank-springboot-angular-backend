package com.sti.bootcamp.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.sti.bootcamp.dao.interfc.TransactionDao;
import com.sti.bootcamp.dao.repository.TrxRepository;
import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Transaction;

public class TrxDaoImpl extends BaseImpl implements TransactionDao {
	
	@Autowired
	private TrxRepository repository;

	@Override
	public Transaction getById(int id) throws Exception {
		return repository.findByAccount(id);
	}

	@Override
	public Transaction save(Transaction transaction) throws Exception {
		return repository.save(transaction);
	}

	@Override
	public void delete(Transaction transaction) throws Exception {
		// TODO Auto-generated method stub
		repository.delete(transaction);
	}

	@Override
	public List<Transaction> getList() throws Exception {
		// TODO Auto-generated method stub
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = critB.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);
		query.select(root);
		
		TypedQuery<Transaction> q = em.createQuery(query);
		return q.getResultList();
	}
	
	@Override
	public List<Transaction> getListByAccount(Account account) throws Exception {
		return repository.findByAccount(account);
	}

}
