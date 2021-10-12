package com.paymybuddy.api.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.api.model.Transaction;
import com.paymybuddy.api.service.TransactionService;

@RestController
public class TransactionController {

	private static final Logger logger = LogManager.getLogger("UserController");

	@Autowired
	TransactionService transactionService;

	// Get all transactions, returns a list of all transactions
	@GetMapping("/transactions")
	public ArrayList<Transaction> getTransactions() {
		ArrayList<Transaction> transactions = transactionService.getTransactions();
		return transactions;
	}

	// Create - Add a new transaction
	@PostMapping("/transaction")
	public Transaction createTransaction(@RequestBody Transaction transaction) {
		return transactionService.saveTransaction(transaction);
	}

	// - Get one transaction
	@GetMapping("/transaction/{id}")
	public Transaction getTransaction(@PathVariable("id") final Integer id) {
		Optional<Transaction> transaction = transactionService.getTransaction(id);
		if (transaction.isPresent()) {
			return transaction.get();
		} else {
			return null;
		}
	}

}
