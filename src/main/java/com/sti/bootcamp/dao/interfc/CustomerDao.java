package com.sti.bootcamp.dao.interfc;

import java.util.List;

import com.sti.bootcamp.PostCustomer;
import com.sti.bootcamp.error.ExceptionTemp;

public interface CustomerDao {
	
	PostCustomer getById(int custnumber) throws ExceptionTemp;
	PostCustomer save(PostCustomer customer) throws ExceptionTemp;
	void delete(PostCustomer customer) throws ExceptionTemp;
	List<PostCustomer> getList() throws ExceptionTemp;

}
