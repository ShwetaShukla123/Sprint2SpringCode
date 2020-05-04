package com.payment.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payment.wallet.model.WalletAccount;

@Repository("walletAccountRepository")
public interface WalletAccountRepository extends JpaRepository<WalletAccount, Integer>{
	
}
