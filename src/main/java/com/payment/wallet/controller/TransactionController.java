package com.payment.wallet.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payment.wallet.exception.LowBalanceException;
import com.payment.wallet.model.WalletTransaction;
import com.payment.wallet.service.WalletTransactionService;

@RestController
public class TransactionController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private WalletTransactionService transactionService;
	
	@PostMapping("/transaction")
	public ResponseEntity<String> transferFund(@RequestBody WalletTransaction transaction) {
		try {
			if(transaction.getAccountBalance() < transaction.getAmount()) {
				throw new LowBalanceException("Low Balance Exception...");
			}
			transactionService.createTransaction(transaction);
		}catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Transaction Created", HttpStatus.CREATED);
	}
	
	@GetMapping("/transactionList/{accountId}")
	public List<WalletTransaction> getTransactionList(@PathVariable int accountId){
		List<WalletTransaction> list = new ArrayList<WalletTransaction>();
		try {
			list = transactionService.getTransactionList(accountId);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
}
