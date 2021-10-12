package com.paymybuddy.api.service;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.api.model.Transaction;
import com.paymybuddy.api.repository.TransactionRepository;

@Service
public class TransactionService {

	private static final Logger logger = LogManager.getLogger("UserController");

	@Autowired
	private TransactionRepository transactionRepository;

	// Get all transactions
	public ArrayList<Transaction> getTransactions() {
		ArrayList<Transaction> transactions = (ArrayList<Transaction>) transactionRepository.findAll();

		return transactions;
	}

	public Optional<Transaction> getTransaction(final Integer id) {
		return transactionRepository.findById(id);
	}

	public Transaction saveTransaction(Transaction transaction) {
		Transaction savedTransaction = transactionRepository.save(transaction);
		return savedTransaction;
	}

	public void deleteTransaction(final Integer id) {
		transactionRepository.deleteById(id);
	}

}
