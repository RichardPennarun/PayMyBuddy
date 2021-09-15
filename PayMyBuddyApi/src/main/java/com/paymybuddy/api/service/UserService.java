package com.paymybuddy.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import com.paymybuddy.api.model.User;
import com.paymybuddy.api.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	// Get all users
	public ArrayList<User> getUsers() {
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
        
        return users;
    }

	public Optional<User> getUser(final Integer id) {
		return userRepository.findById(id);
	}

	public void deleteUser(final Integer id) {
		userRepository.deleteById(id);
	}

	public User saveUser(User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}

}
