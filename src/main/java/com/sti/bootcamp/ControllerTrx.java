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
import com.sti.bootcamp.dao.interfc.TransactionDao;
import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Transaction;

@RestController
@RequestMapping("/trx")
public class ControllerTrx {
	
	@Autowired
	private TransactionDao transactionDao;
	
	@Autowired
	private AccountDao accountDao;
	
	//get by id
	@GetMapping("/transaction")
	public String transaction(@RequestParam (value="id", defaultValue="")String id) {
		try {
			Transaction transaction = transactionDao.getById(Integer.parseInt(id));
			if (transaction == null) {
				return "Data not found";
			}
			else {
				return "ID: "+transaction.getId();
			}
		} catch (Exception e) {
			return null;
		}
	}
	
	// create/save
	@PostMapping("/transaction")
	public Transaction create(@RequestBody Transaction transaction) throws Exception {
		transactionDao.save(transaction);
		return transaction;
	}
	
	// get list
	@GetMapping("/transactions")
	public List<Transaction> getAll() {
		try {
			List<Transaction> list = transactionDao.getList();
			return list;
		} catch (Exception e) {
			return null;
		}
	}
	
	// delete
	@DeleteMapping("/delete/{id}")
	public Transaction hapus(@PathVariable("id") int id) throws Exception {
		Transaction transaction = new Transaction();
		transaction.setId(id);
		transactionDao.delete(transaction);
		return transaction;
	}
	
	// delete
	@DeleteMapping("/transaction/{id}")
	public void delete(Transaction transaction) throws Exception {
		transactionDao.delete(transaction);
	}
	
	// find by account
	@GetMapping(value="/list")
	public List<Transaction> getList(@RequestParam(name="account", defaultValue="") String accountNumber) throws NumberFormatException, Exception{
		if(!StringUtils.isEmpty(accountNumber)) {
			Account checkAccount = accountDao.getById(Integer.parseInt(accountNumber));
			if(checkAccount==null) {
				throw new Exception("account tidak ditemukan");
			}
			return transactionDao.getListByAccount(checkAccount);
		}else {
			return transactionDao.getList();
		}
	}

}
