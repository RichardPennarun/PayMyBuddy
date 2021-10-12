package com.paymybuddy.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.paymybuddy.webapp.configuration.CustomProperties;
import com.paymybuddy.webapp.model.BankAccount;


@Component
public class BankAccountProxy {


    @Autowired
    private CustomProperties props;

	//Get all bankAccounts
	public Iterable<BankAccount> getBankAccounts() {
		String baseApiUrl = props.getApiUrl();
		String getBankAccountsUrl = baseApiUrl + "/bankAccounts";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<BankAccount>> response = restTemplate.exchange(
				getBankAccountsUrl, 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<Iterable<BankAccount>>() {}
			);
		return response.getBody();
	}
	
	//Get an bankAccount by its id
	public BankAccount getBankAccount(int id) {
		String baseApiUrl = props.getApiUrl();
		String getBankAccountUrl = baseApiUrl + "/bankAccount/" + id;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BankAccount> response = restTemplate.exchange(
				getBankAccountUrl, 
				HttpMethod.GET, 
				null,
				BankAccount.class
			);
		return response.getBody();
	}
	
	//Add a new bankAccount 
	public BankAccount createBankAccount(BankAccount e) {
		String baseApiUrl = props.getApiUrl();
		String createBankAccountUrl = baseApiUrl + "/bankAccount";
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<BankAccount> request = new HttpEntity<BankAccount>(e);
		ResponseEntity<BankAccount> response = restTemplate.exchange(
				createBankAccountUrl, 
				HttpMethod.POST, 
				request, 
				BankAccount.class);
		return response.getBody();
	}
	
	//Update an bankAccount 
	public BankAccount updateBankAccount(BankAccount e) {
		String baseApiUrl = props.getApiUrl();
		String updateBankAccountUrl = baseApiUrl + "/bankAccount/" + e.getId();
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<BankAccount> request = new HttpEntity<BankAccount>(e);
		ResponseEntity<BankAccount> response = restTemplate.exchange(
				updateBankAccountUrl, 
				HttpMethod.PUT, 
				request, 
				BankAccount.class);
		return response.getBody();
	}

}