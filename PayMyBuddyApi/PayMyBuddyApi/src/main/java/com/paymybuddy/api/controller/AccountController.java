package com.paymybuddy.api.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.api.model.Account;
import com.paymybuddy.api.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	AccountService accountService;

	// Get all accounts, returns a list of all accounts
	@GetMapping("/accounts")
	public ArrayList<Account> getAccounts() {
		ArrayList<Account> accounts = accountService.getAccounts();
		return accounts;

	}
	
	// Create - Add a new account
	@PostMapping("/account")
	public Account createAccount(@RequestBody Account account) {
		return accountService.saveAccount(account);
	}
	
	
	//- Get one account 
	@GetMapping("/account/{id}")
	public Account getAccount(@PathVariable("id") final Integer id) {
		Optional<Account> account = accountService.getAccount(id);
		if(account.isPresent()) {
			return account.get();
		} else {
			return null;
		}
	}
	
	//Update an existing account
	@PutMapping("/account/{id}")
	public Account updateAccount(@PathVariable("id") final Integer id, @RequestBody Account account) {
		Optional<Account> e = accountService.getAccount(id);
		if(e.isPresent()) {
			Account currentAccount = e.get();
			
			Integer userId = account.getUserId();
			if(userId != null) {
				currentAccount.setUserId(userId);
			}
			Float balance = account.getBalance();
			if(balance != null) {
				currentAccount.setBalance(balance);
			}
			accountService.saveAccount(currentAccount);
			return currentAccount;
		} else {
			return null;
		}
	}

}
