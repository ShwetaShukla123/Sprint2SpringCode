package com.payment.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.wallet.model.WalletAccount;
import com.payment.wallet.repository.WalletAccountRepository;

@Service
public class WalletAccountServiceImpl implements WalletAccountService{
	
	@Autowired 
	WalletAccountRepository walletAccountRepository;
	
	@Override
	public boolean createWalletAccount(WalletAccount account) {
		boolean flag = false;
		try {
			walletAccountRepository.save(account);
			flag = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public double getBalance(int accountId) {
		double balance = 0;
		try {
			balance = walletAccountRepository.findById(accountId).get().getAccountBalance();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return balance;
	}

	@Override
	public WalletAccount getAccountInfo(int accountId) {
		WalletAccount accountDetails = null;
		try {
			accountDetails = walletAccountRepository.findById(accountId).get();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return accountDetails;
	}

	@Override
	public double updateBalance(WalletAccount account) {
		double totalBalance = 0;
		try {
			walletAccountRepository.save(account);
			totalBalance = this.getBalance(account.getAccountId());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return totalBalance;
	}

}
