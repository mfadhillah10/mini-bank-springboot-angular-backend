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
import com.sti.bootcamp.dao.interfc.TransactionDao;
import com.sti.bootcamp.dto.AccountDto;
import com.sti.bootcamp.dto.CommonResponse;
import com.sti.bootcamp.dto.TransactionDto;
import com.sti.bootcamp.error.ExceptionTemp;
import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Transaction;

@RestController
@SuppressWarnings("rawtypes")
public class ControllerTrx {
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAccount.class);
	
	@Autowired
	private TransactionDao transactionDao;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//get by id
	@GetMapping(value="/transaction/{id}")
	public CommonResponse getById(@PathVariable("id") String id) throws ExceptionTemp {
		LOGGER.info("id : {}", id);
		try {
			Transaction transaction = transactionDao.getById(Integer.parseInt(id));
			return new CommonResponse<TransactionDto>(modelMapper.map(transaction, TransactionDto.class));
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
	
	// create/save
	@PostMapping(value="/transaction")
	public CommonResponse create(@RequestBody TransactionDto transactionDto) throws ExceptionTemp {
		try {
			Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
			transaction.setId(0);
			transaction = transactionDao.save(transaction);
			return new CommonResponse<TransactionDto>(modelMapper.map(transaction, TransactionDto.class));
		} catch (ExceptionTemp e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	// update
	@PutMapping(value="/transaction")
	public CommonResponse update(@RequestBody TransactionDto transaction) throws ExceptionTemp {
		try {
			Transaction checkTransaction = transactionDao.getById(transaction.getId());
			if(checkTransaction == null) {
				return new CommonResponse("404", "Customer Not Found");
			}
			
			if(transaction.getType()!=null) {
				checkTransaction.setType(transaction.getType());
			}
			if(transaction.getAmount()!=null) {
				checkTransaction.setAmount(transaction.getAmount());
			}
			if(transaction.getAmountSign()!=null) {
				checkTransaction.setAmountSign(transaction.getAmountSign());
			}
			
			checkTransaction = transactionDao.save(checkTransaction);
			
			return new CommonResponse<TransactionDto>(modelMapper.map(checkTransaction, TransactionDto.class));
		} catch (ExceptionTemp e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	@GetMapping(value="/transactions")
	public CommonResponse getList(@RequestParam (name="accountNumber", defaultValue="") String transaction) throws ExceptionTemp {
		try {
			List<Transaction> transactions;
			if(!StringUtils.isEmpty(transaction)) {
				Account checkacc = accountDao.getById(Integer.parseInt(transaction));
				if(checkacc==null) {
					throw new ExceptionTemp("Salah");
				}
				transactions = transactionDao.getListByAccount(checkacc);
			}
			else {
				transactions = transactionDao.getList();
			}
			return new CommonResponse<List<TransactionDto>>(transactions.stream().map(trx ->
			modelMapper.map(trx, TransactionDto.class)).collect(Collectors.toList()));
		} catch (ExceptionTemp e) {
			throw e;
		} catch (NumberFormatException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	// get list
	
//	public CommonResponse getAll(@RequestParam (name="account", defaultValue="") String account) throws ExceptionTemp {
//		try {
//			LOGGER.info("customer get list, params : {}", account);
//			if (!StringUtils.isEmpty(account)) {
//				Account checkAccount = accountDao.getById(Integer.parseInt(account));
//				if (checkAccount == null) {
//					throw new ExceptionTemp("Account Not Found");
//				}
//				List<Transaction> transactions = transactionDao.getListByAccount(checkAccount);
//				return new CommonResponse<List<TransactionDto>>(transactions.stream().map(trx ->
//				modelMapper.map(trx, TransactionDto.class)).collect(Collectors.toList()));
//			} else {
//				List<Transaction> transactions = transactionDao.getList();
//				return new CommonResponse<List<TransactionDto>>(transactions.stream().map(trx ->
//				modelMapper.map(trx, TransactionDto.class)).collect(Collectors.toList()));
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
	@DeleteMapping(value="/transaction/{id}")
	public CommonResponse delete(@PathVariable("id") String id) throws ExceptionTemp {
		try {
			Transaction checkTransaction = transactionDao.getById(Integer.parseInt(id));
			if(checkTransaction == null) {
				return new CommonResponse("404", "Customer Not Found");
			}
			transactionDao.delete(checkTransaction);
			return new CommonResponse();
		} catch (ExceptionTemp e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
}
