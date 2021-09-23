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
import com.paymybuddy.webapp.model.User;

@Component
public class UserProxy {

    @Autowired
    private CustomProperties props;

    /**
    * Get all users
    * @return An iterable of all users
    */

    public ArrayList<User> getUsers() {
        String baseApiUrl = props.getApiUrl();
        String getUsersUrl = baseApiUrl + "/users";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ArrayList<User>> response = restTemplate.exchange(
                getUsersUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<User>>() {}
                );

        return response.getBody();
    }
	
	/**
	 * Get an user by the id
	 * @param id The id of the user
	 * @return The user which matches the id
	 */
	public User getUser(int id) {
		String baseApiUrl = props.getApiUrl();
		String getUserUrl = baseApiUrl + "/user/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<User> response = restTemplate.exchange(
				getUserUrl, 
				HttpMethod.GET, 
				null,
				User.class
			);
		
		//log.debug("Get User call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Add a new user 
	 * @param e A new user (without an id)
	 * @return The user full filled (with an id)
	 */
	public User createUser(User e) {
		String baseApiUrl = props.getApiUrl();
		String createUserUrl = baseApiUrl + "/user";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<User> request = new HttpEntity<User>(e);
		ResponseEntity<User> response = restTemplate.exchange(
				createUserUrl, 
				HttpMethod.POST, 
				request, 
				User.class);
		
		//log.debug("Create User call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Update an user - using the PUT HTTP Method.
	 * @param e Existing user to update
	 */
	public User updateUser(User e) {
		String baseApiUrl = props.getApiUrl();
		String updateUserUrl = baseApiUrl + "/user/" + e.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<User> request = new HttpEntity<User>(e);
		ResponseEntity<User> response = restTemplate.exchange(
				updateUserUrl, 
				HttpMethod.PUT, 
				request, 
				User.class);
		
		//log.debug("Update User call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Delete an user using exchange method of RestTemplate
	 * instead of delete method in order to log the response status code.
	 * @param e The user to delete
	 */
	public void deleteUser(int id) {
		String baseApiUrl = props.getApiUrl();
		String deleteUserUrl = baseApiUrl + "/user/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteUserUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		//log.debug("Delete User call " + response.getStatusCode().toString());
	}

}