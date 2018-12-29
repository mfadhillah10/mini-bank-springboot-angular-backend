package com.sti.bootcamp.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import com.sti.bootcamp.PostCustomer;
import com.sti.bootcamp.dao.interfc.CustomerDao;
import com.sti.bootcamp.dao.repository.CustomerRepository;
import com.sti.bootcamp.error.ExceptionTemp;

public class CustomerDaoImpl extends BaseImpl implements CustomerDao {
	
	@Autowired
	private CustomerRepository repository;

	@Override
	public PostCustomer getById(int custnumber) throws ExceptionTemp {
		return repository.findByCustomernumber(custnumber);
	}

	@Override
	public PostCustomer save(PostCustomer customer) throws ExceptionTemp {
		return repository.save(customer);
	}

	@Override
	public void delete(PostCustomer customer) throws ExceptionTemp {
		repository.delete(customer);
	}

	@Override
	public List<PostCustomer> getList() throws ExceptionTemp {
		CriteriaBuilder critB = em.getCriteriaBuilder();
		CriteriaQuery<PostCustomer> query = critB.createQuery(PostCustomer.class);
		Root<PostCustomer> root = query.from(PostCustomer.class);
		query.select(root);
		
		TypedQuery<PostCustomer> q = em.createQuery(query);
		return q.getResultList();
	}
}
