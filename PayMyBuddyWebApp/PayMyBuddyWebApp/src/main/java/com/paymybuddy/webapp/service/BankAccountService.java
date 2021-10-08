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
	
	public void deleteBankAccount(final int id) {
		bankAccountProxy.deleteBankAccount(id);
	}
	
	public BankAccount saveBankAccount(BankAccount BankAccount) {
		BankAccount savedBankAccount;
		
		if (BankAccount.getId() == 0) {
			// If id is null, then it is a new bankAccount.
			savedBankAccount = bankAccountProxy.createBankAccount(BankAccount);
		} else {
			savedBankAccount = bankAccountProxy.updateBankAccount(BankAccount);
		}
		
		return savedBankAccount;
	}

}


