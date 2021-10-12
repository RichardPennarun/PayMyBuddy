package com.paymybuddy.webapp.controller;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.paymybuddy.webapp.model.Account;
import com.paymybuddy.webapp.model.BankAccount;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.service.AccountService;
import com.paymybuddy.webapp.service.BankAccountService;
import com.paymybuddy.webapp.service.CustomUserDetailsService;
import com.paymybuddy.webapp.service.UserService;

@Controller
public class ProfileController {

	private static final Logger logger = LogManager.getLogger("WebAppController");

	@Autowired
	private UserService userService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private BankAccountService bankAccountService;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	/*
	 * Page Profile
	 */
	@GetMapping("/profile")
	public String getProfile(Model model) {

		// Récupération du user
		Integer userId = customUserDetailsService.getCurrentlyLoggedInUserId();
		User user = userService.getUser(userId);
		model.addAttribute("user", user);

		// Récupération de son compte
		Account account = user.getAccount();
		model.addAttribute("account", account);

		// Récupération de son compte bancaire
		BankAccount bankAccount = user.getBankAccount();
		model.addAttribute("bankAccount", bankAccount);

		return "profile";
	}

	/*
	 * Formulaire de modification du compte bancaire Saisie du nouvel iban
	 */
	@GetMapping("/updateBankAccount/{id}")
	public String updateBankAccount(@PathVariable("id") final int id, Model model) {

		// Récupération du compte bancaire à modifier par son id
		BankAccount bankAccount = bankAccountService.getBankAccount(id);
		model.addAttribute("bankAccount", bankAccount);
		return "formUpdateBankAccount";
	}

	/*
	 * Enregistrement du bank_account modifié
	 */
	@PostMapping("/saveBankAccount")
	public ModelAndView saveBankAccount(@ModelAttribute BankAccount bankAccount) {
		try {
			bankAccountService.saveBankAccount(bankAccount);
			return new ModelAndView("redirect:/profile");

		} catch (Exception e) {
			logger.error("Unable to save new iban", e);
		}
		return new ModelAndView("redirect:/profile");
	}

	/*
	 * Formulaire de modification du compte dans le cas d'un retrait Saisie du
	 * montant du retrait
	 */
	@GetMapping("/updateAccountWithdrawal/{id}")
	public String updateAccountWithdrawal(@PathVariable("id") final int id, Model model) {

		// Récupération du compte à débiter par son id
		Account account = accountService.getAccount(id);
		model.addAttribute("account", account);
		return "formUpdateAccountWithdrawal";
	}

	/*
	 * Enregistrement du nouveau solde du compte débité/nouveau solde
	 */
	@PostMapping("/saveAccountWithdrawal")
	public ModelAndView saveAccountWithdrawal(@ModelAttribute("account") Account account,
			@ModelAttribute("withdrawalAmount") double withdrawalAmount) {

		try {
			// Récupération de la balance de l'utilisateur
			Integer userId = customUserDetailsService.getCurrentlyLoggedInUserId();
			User user = userService.getUser(userId);
			Account currentAccount = user.getAccount();
			double currentBalance = currentAccount.getBalance();

			// Calcul de la nouvelle balance en fonction du montant saisi
			double newBalance = currentBalance - withdrawalAmount;
			account.setBalance(newBalance);

			accountService.saveAccount(account);
			return new ModelAndView("redirect:/profile");

		} catch (Exception e) {
			logger.error("Unable to save account withdrawal", e);
		}
		return new ModelAndView("redirect:/profile");
	}

	/*
	 * Formulaire de modification du compte dans le cas d'un depot Saisie du montant
	 * du dépôt
	 */
	@GetMapping("/updateAccountDeposit/{id}")
	public String updateAccountDeposit(@PathVariable("id") final int id, Model model) {

		// Récupération du compte bancaire à modifier par son id
		Account account = accountService.getAccount(id);
		model.addAttribute("account", account);
		return "formUpdateAccountDeposit";
	}

	/*
	 * Enregistrement du nouveau solde du compte crédité
	 */
	@PostMapping("/saveAccountDeposit")
	public ModelAndView saveAccountDeposit(@ModelAttribute("account") Account account,
			@ModelAttribute("depositAmount") double depositAmount) {

		try {
			// Récupération de la balance de l'utilisateur
			Integer userId = customUserDetailsService.getCurrentlyLoggedInUserId();
			User user = userService.getUser(userId);
			Account currentAccount = user.getAccount();
			double currentBalance = currentAccount.getBalance();

			// Calcul de la nouvelle balance en fonction du montant saisi
			double newBalance = currentBalance + depositAmount;
			account.setBalance(newBalance);

			accountService.saveAccount(account);
			return new ModelAndView("redirect:/profile");

		} catch (Exception e) {
			logger.error("Unable to save account deposit", e);
		}
		return new ModelAndView("redirect:/profile");
		
	}

}
