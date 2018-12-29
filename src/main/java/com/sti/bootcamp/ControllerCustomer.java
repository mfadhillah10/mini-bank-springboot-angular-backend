package com.sti.bootcamp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.sti.bootcamp.dao.interfc.CustomerDao;
import com.sti.bootcamp.error.ExceptionTemp;
import com.sti.bootcamp.dto.CommonResponse;
import com.sti.bootcamp.dto.CustomerDto;


@RestController
@SuppressWarnings("rawtypes")
public class ControllerCustomer {
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerCustomer.class);
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// create/save
	@PostMapping(value="/customer")
	public CommonResponse create(@RequestBody CustomerDto customerDto) throws ExceptionTemp {
		try {
			PostCustomer customer = modelMapper.map(customerDto, PostCustomer.class);
			customer.setCustomernumber(0);
			customer = customerDao.save(customer);
			return new CommonResponse<CustomerDto>(modelMapper.map(customer, CustomerDto.class));
		} catch (ExceptionTemp e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	// update
	@PutMapping(value="/customer")
	public CommonResponse update(@RequestBody CustomerDto customer) throws ExceptionTemp {
		try {
			PostCustomer checkCustomer = customerDao.getById(customer.getCustomernumber());
			if(checkCustomer == null) {
				return new CommonResponse("404", "Customer Not Found");
			}
			
			if(customer.getBirthdate()!=null) {
				checkCustomer.setBirthdate(customer.getBirthdate());
			}
			if(customer.getFirstname()!=null) {
				checkCustomer.setFirstname(customer.getFirstname());
			}
			if(customer.getLastname()!=null) {
				checkCustomer.setLastname(customer.getLastname());
			}
			if(customer.getUsername()!=null) {
				checkCustomer.setUsername(customer.getUsername());
			}
			if(customer.getPassword()!=null) {
				checkCustomer.setPassword(customer.getPassword());
			}
			if(customer.getPhonenumber()!=null) {
				checkCustomer.setPhonenumber(customer.getPhonenumber());
			}
			if(customer.getPhonetype()!=null) {
				checkCustomer.setPhonetype(customer.getPhonetype());
			}
			
			checkCustomer =  customerDao.save(checkCustomer);
			
			return new CommonResponse<CustomerDto>(modelMapper.map(checkCustomer, CustomerDto.class));
		} catch (ExceptionTemp e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}

	}
	
	//get by id
	@GetMapping("/customer/{customer}")
	public CommonResponse getById(@PathVariable("customer") String customerNumber) throws ExceptionTemp {
		LOGGER.info("customerNumber : {}", customerNumber);
		try {
			PostCustomer customer = customerDao.getById(Integer.parseInt(customerNumber));
			return new CommonResponse<CustomerDto>(modelMapper.map(customer, CustomerDto.class));
		} catch (ExceptionTemp e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("01", e.getMessage());
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", "Parameter should be number");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

	
	//getList
	@GetMapping(value="/customers")
	public CommonResponse getList(@RequestParam(name="customer", defaultValue="") String customer) throws ExceptionTemp {
		try {
			LOGGER.info("customer get list, params : {}", customer);
			List<PostCustomer> customers = customerDao.getList();
			return new CommonResponse<List<CustomerDto>>(customers.stream().map(cust ->
			modelMapper.map(cust, CustomerDto.class)).collect(Collectors.toList()));
		} catch (ExceptionTemp e) {
			throw e;
		} catch(NumberFormatException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	// delete
	@DeleteMapping(value="/customer/{customer}")
	public CommonResponse delete(@PathVariable("customer") Integer custnumber) throws ExceptionTemp {
		try {
			PostCustomer checkCustomer = customerDao.getById(custnumber);
			if(checkCustomer == null) {
				return new CommonResponse("404", "Customer Not Found");
			}
			customerDao.delete(checkCustomer);
			return new CommonResponse();
		} catch (ExceptionTemp e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
}
