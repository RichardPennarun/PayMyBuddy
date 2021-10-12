package com.paymybuddy.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.webapp.model.BankAccount;
import com.paymybuddy.webapp.repository.BankAccountProxy;

@Service
public class BankAccountService {
	
	@Autowired
	private BankAccountProxy bankAccountProxy;
	
	public BankAccount getBankAccount(final int id) {
		return bankAccountProxy.getBankAccount(id);
	}
	
	public Iterable<BankAccount> getBankAccounts() {
		return bankAccountProxy.getBankAccounts();
	}
	
	public BankAccount saveBankAccount(BankAccount BankAccount) {
		BankAccount savedBankAccount;
		
		if (BankAccount.getId() == 0) {
			savedBankAccount = bankAccountProxy.createBankAccount(BankAccount);
		} else {
			savedBankAccount = bankAccountProxy.updateBankAccount(BankAccount);
		}
		
		return savedBankAccount;
	}

}


