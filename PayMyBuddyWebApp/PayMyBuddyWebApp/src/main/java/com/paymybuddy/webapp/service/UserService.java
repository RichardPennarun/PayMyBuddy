package com.paymybuddy.webapp.service;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.UserProxy;

@Service("userService")
public class UserService {

    private static final Logger logger = LogManager.getLogger("UserServiceImpl");
    
    @Autowired
    private UserProxy userProxy;

    
    public User getLastUser() {
    	return userProxy.getLastUser();
    }
	
    public ArrayList<User> getUsers() {
        return userProxy.getUsers();
    }
	
	public User getUser(final int id) {
		return userProxy.getUser(id);
	}
	
	public void deleteUser(final int id) {
		userProxy.deleteUser(id);
	}
	
	public User saveUser(User User) {
		User savedUser;

		if(User.getId() == 0) {
			savedUser = userProxy.createUser(User);
		} else {
			savedUser = userProxy.updateUser(User);
		}
		
		return savedUser;
	}
}