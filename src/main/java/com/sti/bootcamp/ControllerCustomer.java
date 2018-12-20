package com.sti.bootcamp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sti.bootcamp.dao.interfc.CustomerDao;


@RestController
@RequestMapping("/cust")
public class ControllerCustomer {
	
	@Autowired
	private CustomerDao customerDao;
	
	// create/save
	@PostMapping("/customer")
	public PostCustomer create(@RequestBody PostCustomer customer) throws Exception {
		customerDao.save(customer);
		return customer;
	}
	
	@PutMapping("/update")
	public PostCustomer update(@RequestBody PostCustomer customer) throws Exception {
		customerDao.save(customer);
		return customer;
	}
	
	//get by id
	@GetMapping("/customer")
	public String hello(@RequestParam(value = "custnumber", defaultValue = "") String cust_number) {
		try {
			PostCustomer customer = customerDao.getById(Integer.valueOf(cust_number));
			if (customer == null) {
				return "Data not found";
			}
			else {
				return "Hello "+customer.getFirstname()+" "+customer.getLastname();
			}
		} catch (NumberFormatException e) {
			return "Wrong input format";
		} catch (Exception e) {
			return String.format("There is a problem on system: %s", e.getMessage());
		}
	}
	
	//getList
	@GetMapping("/customers")
	public List<PostCustomer> getAll() {
		try {
			List<PostCustomer> list = customerDao.getList();
			return list;
		} catch (Exception e) {
			return null;
		}
	}
	
	// delete
	@DeleteMapping("/customer/{custnumber}")
	public void delete(PostCustomer customer) throws Exception {
		customerDao.delete(customer);
	}
}
