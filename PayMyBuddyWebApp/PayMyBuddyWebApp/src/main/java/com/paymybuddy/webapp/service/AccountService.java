package com.paymybuddy.webapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.webapp.model.Account;
import com.paymybuddy.webapp.repository.AccountProxy;

@Service
public class AccountService {

    @Autowired
    private AccountProxy accountProxy;

    public ArrayList<Account> getAccounts() {
        return accountProxy.getAccounts();
    }
	
	public Account getAccount(final int id) {
		return accountProxy.getAccount(id);
	}
	
	public Account saveAccount(Account Account) {
		Account savedAccount;
		
		// Functional rule : Last name must be capitalized.
		//Account.setLastName(Account.getLastName());

		if(Account.getId() == 0) {
			// If id is null, then it is a new Account.
			savedAccount = accountProxy.createAccount(Account);
		} else {
			savedAccount = accountProxy.updateAccount(Account);
		}
		
		return savedAccount;
	}


}
