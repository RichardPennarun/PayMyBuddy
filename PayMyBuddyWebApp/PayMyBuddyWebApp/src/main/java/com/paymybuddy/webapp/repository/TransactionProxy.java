package com.paymybuddy.webapp.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.paymybuddy.webapp.configuration.CustomProperties;
import com.paymybuddy.webapp.model.Transaction;

@Component
public class TransactionProxy {

    @Autowired
    private CustomProperties props;

    // Get all transactions
    public ArrayList<Transaction> getTransactions() {
        String baseApiUrl = props.getApiUrl();
        String getTransactionsUrl = baseApiUrl + "/transactions";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ArrayList<Transaction>> response = restTemplate.exchange(
                getTransactionsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<Transaction>>() {}
                );
        return response.getBody();
    }
	
	//Get a transaction by the id
	public Transaction getTransaction(int id) {
		String baseApiUrl = props.getApiUrl();
		String getTransactionUrl = baseApiUrl + "/transaction/" + id;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Transaction> response = restTemplate.exchange(
				getTransactionUrl, 
				HttpMethod.GET, 
				null,
				Transaction.class
			);
		return response.getBody();
	}
	
	// Add a new transaction 
	public Transaction createTransaction(Transaction e) {
		String baseApiUrl = props.getApiUrl();
		String createTransactionUrl = baseApiUrl + "/transaction";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Transaction> request = new HttpEntity<Transaction>(e);
		ResponseEntity<Transaction> response = restTemplate.exchange(
				createTransactionUrl, 
				HttpMethod.POST, 
				request, 
				Transaction.class);
		return response.getBody();
	}

}
