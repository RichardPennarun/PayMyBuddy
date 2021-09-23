package com.paymybuddy.api.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.api.model.User;
import com.paymybuddy.api.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	// Get all users, returns a list of all users
	@GetMapping("/users")
	public ArrayList<User> getUsers() {
		ArrayList<User> users = userService.getUsers();
		return users;

	}
	
	// Create - Add a new user
	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	
	
	//- Get one user 
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") final Integer id) {
		Optional<User> user = userService.getUser(id);
		if(user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}
	
	//Update an existing user
	@PutMapping("/user/{id}")
	public User updateUser(@PathVariable("id") final Integer id, @RequestBody User user) {
		Optional<User> e = userService.getUser(id);
		if(e.isPresent()) {
			User currentUser = e.get();
			
			String firstName = user.getFirstName();
			if(firstName != null) {
				currentUser.setFirstName(firstName);
			}
			String lastName = user.getLastName();
			if(lastName != null) {
				currentUser.setLastName(lastName);
			}
			String username = user.getUsername();
			if(username != null) {
				currentUser.setUsername(username);
			}
			String mail = user.getEmail();
			if(mail != null) {
				currentUser.setEmail(mail);
			}
			String password = user.getPassword();
			if(password != null) {
				currentUser.setPassword(password);
			}
			userService.saveUser(currentUser);
			return currentUser;
		} else {
			return null;
		}
	}
	
	
	// Delete an user
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable("id") final Integer id) {
		userService.deleteUser(id);
	}
	

}
