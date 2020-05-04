package com.payment.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payment.wallet.model.WalletTransaction;

@Repository("walletTransactionRepository")
public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Integer>{

}
