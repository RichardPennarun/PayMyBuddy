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

import com.paymybuddy.api.model.BankAccount;
import com.paymybuddy.api.service.BankAccountService;

@RestController
public class BankAccountController {

	@Autowired
	BankAccountService bankAccountService;

	// - Get one bankAccount

	@GetMapping("/bankAccount/{id}")
	public BankAccount getBankAccount(@PathVariable("id") final Integer id) {
		Optional<BankAccount> bankAccount = bankAccountService.getBankAccount(id);
		if (bankAccount.isPresent()) {
			return bankAccount.get();
		} else {
			return null;
		}
	}

	// Get all bankAccounts, returns a list of all bankAccounts
	@GetMapping("/bankAccounts")
	public ArrayList<BankAccount> getBankAccounts() {
		ArrayList<BankAccount> bankAccounts = bankAccountService.getBankAccounts();
		return bankAccounts;

	}

	// Create - Add a new bankAccount
	@PostMapping("/bankAccount")
	public BankAccount createBankAccount(@RequestBody BankAccount bankAccount) {
		return bankAccountService.saveBankAccount(bankAccount);
	}

	// Update an existing bankAccount
	@PutMapping("/bankAccount/{id}")
	public BankAccount updateBankAccount(@PathVariable("id") final Integer id, 
			@RequestBody BankAccount bankAccount) {
		Optional<BankAccount> b = bankAccountService.getBankAccount(id);
		if (b.isPresent()) {
			BankAccount currentBankAccount = b.get();
			currentBankAccount.setIban(bankAccount.getIban());
			bankAccountService.saveBankAccount(currentBankAccount);
			return currentBankAccount;
		} else {
			return null;
		}
	}

}
