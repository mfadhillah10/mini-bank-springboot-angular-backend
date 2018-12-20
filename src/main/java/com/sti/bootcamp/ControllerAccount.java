package com.sti.bootcamp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sti.bootcamp.dao.interfc.AccountDao;
import com.sti.bootcamp.dao.interfc.CustomerDao;
import com.sti.bootcamp.model.Account;

@RestController
@RequestMapping("/acc")
public class ControllerAccount {
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	// create/save
	@PostMapping("/account")
	public Account create(@RequestBody Account account) throws Exception {
		accountDao.save(account);
		return account;
	}
	
	//get by id
	@GetMapping("/account")
	public String show(@RequestParam (value="account_number", defaultValue="") String accountnumber) {
		try {
			Account show= accountDao.getById(Integer.parseInt(accountnumber));
			if (show == null) {
				return "Data not found";
			} else {
				return "Account Number :"+show.getAccountNumber();
			}	
		} catch (Exception e) {
			return "Wrong input format";
		}
	}
	
	// getList
	@GetMapping("/accounts")
	public List<Account> getAll() {
		try {
			List<Account> list = accountDao.getList();
			return list;
		} catch (Exception e) {
			return null;
		}
	}
	
	// delete
	@DeleteMapping("/delete/{id}")
	public Account hapus(@PathVariable("id") int id) throws Exception {
		Account account = new Account();
		account.setAccountNumber(id);
		accountDao.delete(account);
		return account;
	}
	
	//delete
	@DeleteMapping("/account/{custnumber}")
	public void delete(Account account) throws Exception {
		accountDao.delete(account);
	}
	
	@GetMapping(value="/list")
	public List<Account> getList(@RequestParam(name="customer", defaultValue="") String customer) throws NumberFormatException, Exception{
		if(!StringUtils.isEmpty(customer)) {
			PostCustomer checkCustomer = customerDao.getById(Integer.parseInt(customer));
			if(checkCustomer==null) {
				throw new Exception("customer tidak ditemukan");
			}
			return accountDao.getListByCustomer(checkCustomer);
		}else {
			return accountDao.getList();
		}
	}

}