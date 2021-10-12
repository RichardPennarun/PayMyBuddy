package com.paymybuddy.api.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.paymybuddy.api.model.User;
import com.paymybuddy.api.service.UserService;

@RestController
public class UserController {

	private static final Logger logger = LogManager.getLogger("UserController");

	@Autowired
	UserService userService;

	// Get user by email
	@GetMapping("/user/{email}")
	public User findByEmail(@PathVariable("email") final String email) {
		User user = userService.findByEmail(email);
		//logger.info("User found by username or email.");
		return user;
	}

	// Get user by its id
	@GetMapping("/user/id/{id}")
	public User getUser(@PathVariable("id") final Integer id) {
		Optional<User> user = userService.getUser(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}

	// Get all users, returns a list of all users
	@GetMapping("/users")
	public ArrayList<User> getUsers() {
		ArrayList<User> users = userService.getUsers();
		return users;
	}

	// Get last user created
	@GetMapping("/lastUser")
	public User findFirstByOrderByIdDesc() {
		User user = userService.findFirstByOrderByIdDesc();
		return user;
	}

	// Register a new user
	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

}
