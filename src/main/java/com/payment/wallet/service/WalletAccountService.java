package com.payment.wallet.service;

import com.payment.wallet.model.WalletAccount;

public interface WalletAccountService {
	
	public boolean createWalletAccount(WalletAccount account);

	public double getBalance(int accountId);
	
	public WalletAccount getAccountInfo(int accountId);
	
	public double updateBalance(WalletAccount account);
	
}
