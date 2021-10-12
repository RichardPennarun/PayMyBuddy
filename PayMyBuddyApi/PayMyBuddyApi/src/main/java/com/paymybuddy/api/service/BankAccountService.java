package com.paymybuddy.api.service;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.api.model.BankAccount;
import com.paymybuddy.api.repository.BankAccountRepository;

@Service
public class BankAccountService {

	private static final Logger logger = LogManager.getLogger("UserController");

	@Autowired
	private BankAccountRepository bankAccountRepository;

	public ArrayList<BankAccount> getBankAccounts() {
		ArrayList<BankAccount> bankAccounts = (ArrayList<BankAccount>) bankAccountRepository.findAll();

		return bankAccounts;
	}

	public Optional<BankAccount> getBankAccount(final Integer id) {
		return bankAccountRepository.findById(id);
	}

	public void deleteBankAccount(final Integer id) {
		bankAccountRepository.deleteById(id);
	}

	public BankAccount saveBankAccount(BankAccount bankAccount) {
		BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
		return savedBankAccount;
	}

}
