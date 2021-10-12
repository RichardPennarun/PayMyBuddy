package com.paymybuddy.api.service;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.api.model.Account;
import com.paymybuddy.api.repository.AccountRepository;

@Service
public class AccountService {

	private static final Logger logger = LogManager.getLogger("UserController");

	@Autowired
	private AccountRepository accountRepository;

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

	public void deleteAccount(final Integer id) {
		accountRepository.deleteById(id);
	}

}
