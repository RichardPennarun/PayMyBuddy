package com.paymybuddy.webapp.controller;

import java.sql.Timestamp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.paymybuddy.webapp.model.Account;
import com.paymybuddy.webapp.model.Connection;
import com.paymybuddy.webapp.model.Transaction;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.service.AccountService;
import com.paymybuddy.webapp.service.CustomUserDetailsService;
import com.paymybuddy.webapp.service.TransactionService;
import com.paymybuddy.webapp.service.UserService;

@Controller
public class TransferController {

	private static final Logger logger = LogManager.getLogger("WebAppController");

	@Autowired
	private UserService userService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	/*
	 * Page Transfer
	 */
	@GetMapping("/transfer")
	public String transfer(Model model) {

		// Récupère le CurrentlyLoggedInUser
		Integer userId = customUserDetailsService.getCurrentlyLoggedInUserId();
		User user = userService.getUser(userId);

		// Affiche la liste de ses transactions
		Iterable<Transaction> listTransaction = user.getTransactions();
		model.addAttribute("transactions", listTransaction);

		// Récupère son compte pour afficher son solde
		Account account = user.getAccount();
		model.addAttribute("accounts", account);

		// Affiche la liste de ses connections pour effectuer un paiement
		Iterable<Connection> listConnection = user.getConnections();
		model.addAttribute("connections", listConnection);

		// Création d'une nouvelle transaction dont on récupère
		// les éléments par formulaire
		Transaction transaction = new Transaction();
		model.addAttribute("transaction", transaction);

		return "transfer";
	}

	/*
	 * Enregistrement de la nouvelle transaction et des nouveaux soldes
	 * émetteur/bénéficiaire
	 */
	@PostMapping("/saveTransaction")
	public ModelAndView saveTransaction(@ModelAttribute("transaction") Transaction transaction) {

		try {
			// Ajout de l'émetteur de la transaction
			Integer userId = customUserDetailsService.getCurrentlyLoggedInUserId();
			User user = userService.getUser(userId);
			transaction.setTransmitterId(user.getId());

			// Horodatage de la transaction
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			transaction.setTransactionDate(timestamp);

			// Récupération du username du bénéficiaire
			User beneficiary = userService.getUser(transaction.getBeneficiaryId());
			transaction.setBeneficiaryUsername(beneficiary.getUsername());

			// Calcul nouveau solde du bénéficiaire
			Account beneficiaryAccount = beneficiary.getAccount();
			double beneficiaryBalance = (beneficiaryAccount.getBalance()) + (transaction.getAmount());
			beneficiaryAccount.setBalance(beneficiaryBalance);

			// Calcul nouveau solde de l'émetteur (-5% pour l'app)
			Account transmitterAccount = user.getAccount();
			double transmitterBalance = (transmitterAccount.getBalance()) - (transaction.getAmount())
					- (((transaction.getAmount()) * 5) * 0.01);
			transmitterAccount.setBalance(transmitterBalance);

			accountService.saveAccount(transmitterAccount);
			accountService.saveAccount(beneficiaryAccount);
			transactionService.saveTransaction(transaction);
			return new ModelAndView("redirect:/transfer");
		
		} catch (Exception e) {
			logger.error("Unable to save transaction", e);
		}
		return new ModelAndView("redirect:/transfer");
	}

}
