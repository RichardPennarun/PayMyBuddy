package com.paymybuddy.api.service;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.paymybuddy.api.model.User;
import com.paymybuddy.api.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {
	
    private static final Logger logger = LogManager.getLogger("UserServiceImpl");

	@Autowired
	private UserRepository userRepository;
	
	// Get all users
	public ArrayList<User> getUsers() {
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
        
        return users;
    }

	
	  public Optional<User> getUser(final Integer id) { 
			        //logger.info("User found by username or id.");
					  return userRepository.findById(id);
  }
	 

	public void deleteUser(final Integer id) {
		userRepository.deleteById(id);
	}

	public User saveUser(User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}
	
	@Override
    public User findByEmail(String email) {
        logger.info("User was successfully fetched.");
        return userRepository.findByEmail(email);
    }
	
	
}
