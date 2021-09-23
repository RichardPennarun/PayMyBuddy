package com.paymybuddy.api.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.api.model.Account;
import com.paymybuddy.api.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	// Get all accounts
	public ArrayList<Account> getAccounts() {
        ArrayList<Account> accounts = (ArrayList<Account>) accountRepository.findAll();
        
        return accounts;
    }

	public Optional<Account> getAccount(final Integer id) {
		return accountRepository.findById(id);
	}

	public Account saveAccount(Account account) {
		Account savedAccount = accountRepository.save(account);
		return savedAccount;
	}
	
	

}
