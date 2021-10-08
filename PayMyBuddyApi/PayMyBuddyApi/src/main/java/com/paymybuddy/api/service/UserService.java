package com.paymybuddy.api.service;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.paymybuddy.api.model.User;


public interface UserService {
	

	ArrayList<User> getUsers();
	
	Optional<User> getUser(final Integer id);
	
	User saveUser(User user);
	
	void deleteUser(final Integer id);

	//User findByUsernameOrEmail(final String usernameOrEmail);
	
	User findByEmail(String email) ;
}
