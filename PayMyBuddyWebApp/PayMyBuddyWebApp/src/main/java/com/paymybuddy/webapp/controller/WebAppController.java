package com.paymybuddy.webapp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.paymybuddy.webapp.model.Account;
import com.paymybuddy.webapp.model.BankAccount;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.service.AccountService;
import com.paymybuddy.webapp.service.BankAccountService;
import com.paymybuddy.webapp.service.UserService;

@Controller
public class WebAppController {

	private static final Logger logger = LogManager.getLogger("WebAppController");

	@Autowired
	private UserService userService;
	
	@Autowired
	private BankAccountService bankAccountService;
	
	@Autowired
	private AccountService accountService;
	
	

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String home(Model model) {

		Iterable<User> listUser = userService.getUsers();
		// Integer userId = ((User) loggedUser).getId();
		// User user = userService.getUser(userId);
		model.addAttribute("users", listUser);
		logger.info("Home.");
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		logger.info("Page login.");

		return "login";
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public String register(Model model) {

		return "register";
	}

	/*
	 * New user
	 */

	@GetMapping("/registerNewUser")
	public String createUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		
		return "registerNewUser";
	}

	@PostMapping("/registerUser")
	public ModelAndView saveUser(
			@ModelAttribute ("user") User user,
			@ModelAttribute ("iban") String iban) {
		
		// Cryptage du password
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(hashedPassword);

		userService.saveUser(user);
		
		// Création du bankAccount
		BankAccount bankAccount = new BankAccount();
		bankAccount.setUserId(user.getId());
		bankAccount.setIban(iban);
		
		bankAccountService.saveBankAccount(bankAccount);
		//accountService.saveAccount(account);
		return new ModelAndView("redirect:/");
	}


}