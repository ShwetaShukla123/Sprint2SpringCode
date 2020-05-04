package com.payment.wallet.service;

import java.util.List;

import com.payment.wallet.model.WalletTransaction;

public interface WalletTransactionService {
	
	public boolean createTransaction(WalletTransaction transaction);
	
	public List<WalletTransaction> getTransactionList(int accountId);

}
