package com.payment.wallet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.wallet.exception.InvalidAccountException;
import com.payment.wallet.model.WalletAccount;
import com.payment.wallet.model.WalletTransaction;
import com.payment.wallet.repository.WalletAccountRepository;
import com.payment.wallet.repository.WalletTransactionRepository;

@Service
public class WalletTransactionServiceImpl implements WalletTransactionService{
	
	@Autowired 
	WalletTransactionRepository walletTransactionRepository;
	
	@Autowired
	WalletAccountRepository walletAccountRepository;
	
	@Autowired 
	WalletAccountService walletAccountService;
	
	@Override
	public boolean createTransaction(WalletTransaction transaction) {
		boolean flag = false;
		try {
			int recipientAccountId = transaction.getReceiverAccountId();
			double transferAmount = transaction.getAmount();
			if(!walletAccountRepository.existsById(recipientAccountId)) {
				throw new InvalidAccountException("Invalid Recipient Account...");
			}
			WalletAccount account = walletAccountService.getAccountInfo(recipientAccountId);
			double prevBalance = account.getAccountBalance();
			double recieverFinalBalance  = prevBalance + transferAmount;
			account.setAccountBalance(recieverFinalBalance);
			recieverFinalBalance = walletAccountService.updateBalance(account);
			
			int senderAccountId = transaction.getAccountId();
			WalletAccount receiverAccount = walletAccountService.getAccountInfo(senderAccountId);
			double senderFinalBalance  = prevBalance - transferAmount;
			receiverAccount.setAccountBalance(senderFinalBalance);
			recieverFinalBalance = walletAccountService.updateBalance(receiverAccount);
			walletTransactionRepository.save(transaction);
			flag = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<WalletTransaction> getTransactionList(int accountId) {
		List<WalletTransaction> list = new ArrayList<WalletTransaction>();
		try {
			List<WalletTransaction> listAll = walletTransactionRepository.findAll();
			for(WalletTransaction transaction: listAll) {
				if(transaction.getAccountId()==accountId) {
					list.add(transaction);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}