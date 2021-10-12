package com.paymybuddy.webapp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paymybuddy.webapp.model.MyUserDetails;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.repository.UserProxy;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger logger = LogManager.getLogger("CustomUserDetailsService");

	private UserProxy userProxy;

	@Autowired
	public CustomUserDetailsService(UserProxy userProxy) {
		this.userProxy = userProxy;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userProxy.findByEmail(username);
		logger.info("The user with the following email " + username + " was successfully fetched.");
		return new MyUserDetails(user);
	}
	
	    // Retrieves the logged in user by its id.
    public Integer getCurrentlyLoggedInUserId() {
        logger.info("The logged in user was successfully fetched.");
        return getAuthentication().getId();
    }

    // Retrieves the logged in user by its email.
    public String getCurrentlyLoggedInUserEmail() {
        logger.info("The logged in user was successfully fetched.");
        return getAuthentication().getUsername();
    }

    // Retrieves the logged in user.
    public User getCurrentlyLoggedInUser() {
        logger.info("The logged in user was successfully fetched.");
        return getAuthentication().getUser();
    }

    // Retrieves the currently authenticated principal via a static call to the SecurityContextHolder.
    private MyUserDetails getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (MyUserDetails) authentication.getPrincipal();
    }
}


