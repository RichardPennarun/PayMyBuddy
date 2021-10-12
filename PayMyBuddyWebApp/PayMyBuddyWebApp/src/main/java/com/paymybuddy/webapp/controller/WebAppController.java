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

	/*
	 * Authentification
	 */

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(Model model) {
		return "login";
	}

	/*
	 * Page d'accueil nouvel utilisateur
	 */

	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public String register(Model model) {

		// Création d'un nouveau user
		User user = new User();
		model.addAttribute("user", user);

		// Création d'un nouveau account
		Account account = new Account();
		model.addAttribute("account", account);

		// Création d'un nouveau bankAccount
		BankAccount bankAccount = new BankAccount();
		model.addAttribute("bankAccount", bankAccount);

		return "register";
	}

	/*
	 * Enregistrement d'un nouvel utilisateur et création de ses compte et compte
	 * bancaire
	 */
	@PostMapping("/registerUser")
	public ModelAndView saveUser(@ModelAttribute("user") User user,
			@ModelAttribute("bankAccount") BankAccount bankAccount, 
			@ModelAttribute("account") Account account,
			@ModelAttribute("iban") String iban) {

		try {
			// Cryptage du password
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(hashedPassword);

			// Récupération du userId pour compte et compte bancaire
			int newUserId = 0;
			User lastUser = userService.getLastUser();
			if (lastUser != null) {
				newUserId = lastUser.getId() + 1;
			} else {
				newUserId = 1;
			}
			account.setUserId(newUserId);
			bankAccount.setUserId(newUserId);

			// Récupération de l'iban saisi
			bankAccount.setIban(iban);

			userService.saveUser(user);
			bankAccountService.saveBankAccount(bankAccount);
			accountService.saveAccount(account);
			return new ModelAndView("redirect:/");
		} catch (Exception e) {
			logger.error("Unable to register user and/or create accounts", e);
		}
		return new ModelAndView("redirect:/register");
	}

}