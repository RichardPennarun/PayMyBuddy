package com.paymybuddy.webapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.UserProxy;

@Service
public class UserService {

    @Autowired
    private UserProxy userProxy;

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
		
		// Functional rule : Last name must be capitalized.
		//User.setLastName(User.getLastName());

		if(User.getId() == 0) {
			// If id is null, then it is a new User.
			savedUser = userProxy.createUser(User);
		} else {
			savedUser = userProxy.updateUser(User);
		}
		
		return savedUser;
	}

}