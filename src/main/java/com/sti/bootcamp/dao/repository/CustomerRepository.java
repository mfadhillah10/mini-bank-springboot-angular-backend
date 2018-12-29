package com.sti.bootcamp.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.sti.bootcamp.PostCustomer;

public interface CustomerRepository extends PagingAndSortingRepository<PostCustomer, Integer> {
	
	PostCustomer findByCustomernumber(int custnumber);

}
