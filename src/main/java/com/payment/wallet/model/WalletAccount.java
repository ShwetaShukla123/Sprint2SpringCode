package com.payment.wallet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="WalletAccount")
@Table
public class WalletAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length=5, name="accountId")
	int accountId;
	
	@Column(name = "accountBalance")
	double accountBalance;
	
	public WalletAccount() {
	}

	public WalletAccount(int accountId, double accountBalance, Status status) {
		super();
		this.accountId = accountId;
		this.accountBalance = accountBalance;
		this.status = status;
	}
	
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	Status status;

}
