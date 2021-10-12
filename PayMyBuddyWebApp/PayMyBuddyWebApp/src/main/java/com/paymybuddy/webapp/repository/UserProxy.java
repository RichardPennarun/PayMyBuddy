package com.paymybuddy.webapp.repository;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.paymybuddy.webapp.configuration.CustomProperties;
import com.paymybuddy.webapp.model.MyUserDetails;
import com.paymybuddy.webapp.model.User;

@Repository
public class UserProxy {

	private static final Logger logger = LogManager.getLogger("UserProxy");

	@Autowired
	private CustomProperties props;

	// Get a user by its email (authentication)
	public User findByEmail(String email) {
		String baseApiUrl = props.getApiUrl();
		String getUserUrl = baseApiUrl + "/user/" + email;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User> response = restTemplate.exchange(getUserUrl, HttpMethod.GET, null, User.class);
		logger.info("User found by its email.");
		return response.getBody();
	}

	// Get the last user registered)
	public User getLastUser() {
		String baseApiUrl = props.getApiUrl();
		String getUserUrl = baseApiUrl + "/lastUser";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User> response = restTemplate.exchange(getUserUrl, HttpMethod.GET, null, User.class);
		return response.getBody();
	}

	// Get user by ID
	public User getUser(int id) {
		String baseApiUrl = props.getApiUrl();
		String getUserUrl = baseApiUrl + "/user/id/" + id;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User> response = restTemplate.exchange(getUserUrl, HttpMethod.GET, null, User.class);
		return response.getBody();
	}

	// GET ALL users
	public ArrayList<User> getUsers() {
		String baseApiUrl = props.getApiUrl();
		String getUsersUrl = baseApiUrl + "/users";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ArrayList<User>> response = restTemplate.exchange(getUsersUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<ArrayList<User>>() {
				});
		return response.getBody();
	}

	// CREATE a new user
	public User createUser(User e) {
		String baseApiUrl = props.getApiUrl();
		String createUserUrl = baseApiUrl + "/user";
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<User> request = new HttpEntity<User>(e);
		ResponseEntity<User> response = restTemplate.exchange(createUserUrl, HttpMethod.POST, request, User.class);
		return response.getBody();
	}

	// UPDATE a user 
	public User updateUser(User e) {
		String baseApiUrl = props.getApiUrl();
		String updateUserUrl = baseApiUrl + "/user/" + e.getId();
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<User> request = new HttpEntity<User>(e);
		ResponseEntity<User> response = restTemplate.exchange(updateUserUrl, HttpMethod.PUT, request, User.class);
		return response.getBody();
	}

	//DELETE a user
	public void deleteUser(int id) {
		String baseApiUrl = props.getApiUrl();
		String deleteUserUrl = baseApiUrl + "/user/" + id;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(deleteUserUrl, HttpMethod.DELETE, null, Void.class);
	}

}