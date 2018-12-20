package com.sti.bootcamp.dao.interfc;

import java.util.List;

import com.sti.bootcamp.PostCustomer;

public interface CustomerDao {
	
	PostCustomer getById(int cust_number) throws Exception;
	PostCustomer save(PostCustomer customer) throws Exception;
//	PostCustomer update(PostCustomer customer) throws Exception;
	void delete(PostCustomer customer) throws Exception;
	List<PostCustomer> getList() throws Exception;

}
