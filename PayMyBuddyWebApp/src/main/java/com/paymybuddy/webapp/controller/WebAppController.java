package com.paymybuddy.webapp.controller;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.paymybuddy.webapp.model.Transaction;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.service.TransactionService;
import com.paymybuddy.webapp.service.UserService;

@Controller
public class WebAppController {

	@Autowired
	UserService userService;
	
	@Autowired
	TransactionService transactionService;
	
	@RolesAllowed("USER")
	@RequestMapping("/*")
	public String getUser() {
		return "home";
	}

	@RolesAllowed({ "USER", "ADMIN" })
	@RequestMapping("/admin")
	public String getAdmin() {
		return "home";
	}

	//@GetMapping("/users")
	//public String home(Model model) {
	//	ArrayList<User> users = userService.getUsers();
	//	model.addAttribute("users", users);
	//	return "home";
	//}
	
	@GetMapping("/transfer")
	public String transfer(Model model) {
		Iterable<Transaction> listTransaction = transactionService.getTransactions();
		model.addAttribute("transactions", listTransaction);
		
		Transaction e = new Transaction(); 
		model.addAttribute("transaction", e);
		return "transfer";
	}
	
	/*
	 * @GetMapping("/createTransaction") public String createTransaction(Model
	 * model) { Transaction e = new Transaction(); model.addAttribute("transaction",
	 * e); return "transfer"; }
	 */
	
	@PostMapping("/saveTransaction")
	public ModelAndView saveTransaction(@ModelAttribute Transaction transaction) {
		//if(user.getId() != 0) {
			// User from update form has the password field not filled,
			// so we fill it with the current password.
			//User current = userService.getUser(user.getId());
			//user.setPassword(current.getPassword());
		//}
		transactionService.saveTransaction(transaction);
		return new ModelAndView("redirect:/");	
	}
	
	

	
	@GetMapping("/createUser")
	public String createUser(Model model) {
		User e = new User();
		model.addAttribute("user", e);
		return "formNewUser";
	}
	
	@PostMapping("/saveUser")
	public ModelAndView saveUser(@ModelAttribute User user) {
		if(user.getId() != 0) {
			// User from update form has the password field not filled,
			// so we fill it with the current password.
			//User current = userService.getUser(user.getId());
			//user.setPassword(current.getPassword());
		}
		userService.saveUser(user);
		return new ModelAndView("redirect:/");	
	}
	
	@GetMapping("/updateUser/{id}")
	public String updateUser(@PathVariable("id") final int id, Model model) {
		User e = userService.getUser(id);		
		model.addAttribute("user", e);	
		return "formUpdateUser";		
	}
	
	@GetMapping("/deleteUser/{id}")
	public ModelAndView deleteUser(@PathVariable("id") final int id) {
		userService.deleteUser(id);
		return new ModelAndView("redirect:/");		
	}

}