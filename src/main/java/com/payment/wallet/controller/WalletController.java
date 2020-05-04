package com.payment.wallet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.wallet.model.Status;
import com.payment.wallet.model.WalletAccount;
import com.payment.wallet.service.WalletAccountService;

@RestController
public class WalletController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private WalletAccountService walletAccountService;
	
	@RequestMapping(value = "/create")
	public ResponseEntity<String> saveWalletUser() {
		WalletAccount account = new WalletAccount();
		account.setStatus(Status.ACTIVE);
		account.setAccountBalance(0);
		try {
		walletAccountService.createWalletAccount(account);
		}catch (Exception ex) {
			return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Account Created", HttpStatus.CREATED);

	}
	
	@GetMapping("/addMoney/{accountId}/{amount}")
	public double addMoney(@PathVariable int accountId, @PathVariable double amount) {
		double finalBalance = 0;
		try {
			WalletAccount account = walletAccountService.getAccountInfo(accountId);
			double prevBalance = account.getAccountBalance();
			finalBalance  = prevBalance + amount;
			account.setAccountBalance(finalBalance);
			finalBalance = walletAccountService.updateBalance(account);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return finalBalance;
	}
	
	@GetMapping("/getAccountInfo/{accountId}")
	public ResponseEntity<WalletAccount> getAccountInfo(@PathVariable int accountId){
		WalletAccount accountDetails = null;
		try {
			accountDetails = walletAccountService.getAccountInfo(accountId);
		}
		catch(Exception ex) {
			return new ResponseEntity<WalletAccount>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<WalletAccount>(accountDetails, HttpStatus.OK);
	}
	
	@GetMapping("/getBalance/{accountId}")
	public double getBalance(@PathVariable int accountId) {
		double balance = 0;
		try {
			balance = walletAccountService.getBalance(accountId);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return balance;
	}
	
}