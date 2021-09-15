package com.paymybuddy.webapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.webapp.model.Transaction;
import com.paymybuddy.webapp.repository.TransactionProxy;

@Service
public class TransactionService {

    @Autowired
    private TransactionProxy transactionProxy;

    public ArrayList<Transaction> getTransactions() {
        return transactionProxy.getTransactions();
    }
	
	public Transaction getTransaction(final int id) {
		return transactionProxy.getTransaction(id);
	}
	
	public Transaction saveTransaction(Transaction Transaction) {
		Transaction savedTransaction;
			savedTransaction = transactionProxy.createTransaction(Transaction);
		
		return savedTransaction;
	}

}
