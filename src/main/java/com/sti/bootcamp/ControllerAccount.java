package com.sti.bootcamp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sti.bootcamp.dao.interfc.AccountDao;
import com.sti.bootcamp.dao.interfc.CustomerDao;
import com.sti.bootcamp.dto.CommonResponse;
import com.sti.bootcamp.error.ExceptionTemp;
import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.dto.AccountDto;

@RestController
@SuppressWarnings("rawtypes")
public class ControllerAccount {
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAccount.class);
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// create/save
	@PostMapping(value="/account")
	public CommonResponse create(@RequestBody AccountDto accountDto) throws ExceptionTemp {
		try {
			Account account = modelMapper.map(accountDto, Account.class);
			account.setAccountNumber(0);
			account.setCustomer(customerDao.getById(account.getCustomer().getCustomernumber()));
			account = accountDao.save(account);
			return new CommonResponse<AccountDto>(modelMapper.map(account, AccountDto.class));
		} catch (ExceptionTemp e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	//get by id
	@GetMapping(value="/account/{account}")
	public CommonResponse getById(@PathVariable("account") String accountnumber) throws ExceptionTemp {
		LOGGER.info("accountNumber : {}", accountnumber);
		try {
			Account account = accountDao.getById(Integer.parseInt(accountnumber));
			return new CommonResponse<AccountDto>(modelMapper.map(account, AccountDto.class));
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
	
	// update
	@PutMapping(value="/account")
	public CommonResponse update(@RequestBody AccountDto account) throws ExceptionTemp {
		try {
			Account checkAccount = accountDao.getById(account.getAccountNumber());
			if(checkAccount == null) {
				return new CommonResponse("404", "Customer Not Found");
			}
			
			if(account.getOpenDate()!=null) {
				checkAccount.setOpenDate(account.getOpenDate());
			}
			if(account.getBalance()!=null) {
				checkAccount.setBalance(account.getBalance());
			}
			if(account.getCustomer()!=null) {
				checkAccount.setCustomer(modelMapper.map(account.getCustomer(), PostCustomer.class));
			}
			
			checkAccount = accountDao.save(checkAccount);
			
			return new CommonResponse<AccountDto>(modelMapper.map(checkAccount, AccountDto.class));
		} catch (ExceptionTemp e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}

	}
	@GetMapping(value="/accounts")
	public CommonResponse getAll(@RequestParam(name ="cust_number", defaultValue="") String account) throws ExceptionTemp{
		try {
			List<Account> accounts;
			if (!StringUtils.isEmpty(account)) {
				PostCustomer checkcus = customerDao.getById(Integer.parseInt(account));
				if (checkcus==null) {
					throw new ExceptionTemp("salah");
				}
				accounts = accountDao.getListByCustomer(checkcus);
			}
			else {
				accounts = accountDao.getList();
			}
			return new CommonResponse<List<AccountDto>>(accounts.stream().map(acc ->
			modelMapper.map(acc, AccountDto.class)).collect(Collectors.toList()));
		} catch (ExceptionTemp e) {
			throw e;
		} catch (NumberFormatException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	// getList
	
//	public CommonResponse getAll(@RequestParam (name="customer", defaultValue="") String customer) throws ExceptionTemp {
//		try {
//			LOGGER.info("account get list, params : {}", customer);
//			if (!StringUtils.isEmpty(customer)) {
//				PostCustomer checkCustomer = customerDao.getById(Integer.parseInt(customer));
//				if (checkCustomer == null) {
//					throw new ExceptionTemp("Customer Not Found");
//				}
//				List<Account> accounts = accountDao.getListByCustomer(checkCustomer);
//				return new CommonResponse<List<AccountDto>>(accounts.stream().map(acc ->
//				modelMapper.map(acc, AccountDto.class)).collect(Collectors.toList()));
//			} else {
//				List<Account> accounts = accountDao.getList();
//				return new CommonResponse<List<AccountDto>>(accounts.stream().map(acc ->
//				modelMapper.map(acc, AccountDto.class)).collect(Collectors.toList()));
//			}
//		} catch (ExceptionTemp e) {
//			throw e;
//		} catch(NumberFormatException e) {
//			return new CommonResponse("01", e.getMessage());
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage());
//			return new CommonResponse("06", e.getMessage());
//		}
//	}
	
	// delete
	@DeleteMapping(value="/account/{id}")
	public CommonResponse delete(@PathVariable("id") Integer id) throws ExceptionTemp {
		try {
			Account checkAccount = accountDao.getById(id);
			if(checkAccount == null) {
				return new CommonResponse("404", "Customer Not Found");
			}
			accountDao.delete(checkAccount);
			return new CommonResponse();
		} catch (ExceptionTemp e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
}