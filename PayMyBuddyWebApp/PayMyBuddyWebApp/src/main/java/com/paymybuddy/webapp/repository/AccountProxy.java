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
import com.paymybuddy.webapp.model.Account;

@Component
public class AccountProxy {

    @Autowired
    private CustomProperties props;

    //Get all accounts
    public ArrayList<Account> getAccounts() {
        String baseApiUrl = props.getApiUrl();
        String getAccountsUrl = baseApiUrl + "/accounts";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ArrayList<Account>> response = restTemplate.exchange(
                getAccountsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<Account>>() {}
                );
        return response.getBody();
    }
	
	//Get account by id
	public Account getAccount(int id) {
		String baseApiUrl = props.getApiUrl();
		String getAccountUrl = baseApiUrl + "/account/" + id;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Account> response = restTemplate.exchange(
				getAccountUrl, 
				HttpMethod.GET, 
				null,
				Account.class
			);
		return response.getBody();
	}
	
	//Add a new account 
	public Account createAccount(Account e) {
		String baseApiUrl = props.getApiUrl();
		String createAccountUrl = baseApiUrl + "/account";
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Account> request = new HttpEntity<Account>(e);
		ResponseEntity<Account> response = restTemplate.exchange(
				createAccountUrl, 
				HttpMethod.POST, 
				request, 
				Account.class);
		return response.getBody();
	}
	
	//Update an account
	public Account updateAccount(Account e) {
		String baseApiUrl = props.getApiUrl();
		String updateAccountUrl = baseApiUrl + "/account/" + e.getId();
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Account> request = new HttpEntity<Account>(e);
		ResponseEntity<Account> response = restTemplate.exchange(
				updateAccountUrl, 
				HttpMethod.PUT, 
				request, 
				Account.class);
		return response.getBody();
	}

}
