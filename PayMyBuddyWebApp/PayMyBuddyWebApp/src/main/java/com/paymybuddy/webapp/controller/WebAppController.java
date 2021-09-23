package com.paymybuddy.webapp.controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.paymybuddy.webapp.model.Connection;
import com.paymybuddy.webapp.model.Transaction;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.service.ConnectionService;
import com.paymybuddy.webapp.service.TransactionService;
import com.paymybuddy.webapp.service.UserService;

@Controller
public class WebAppController {

	@Autowired
	UserService userService;

	@Autowired
	TransactionService transactionService;

	@Autowired
	ConnectionService connectionService;

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
	
		

	@GetMapping("/transfer")
	public String transfer(Model model) {
		
		User user = userService.getUser(2);
		
		Iterable<Transaction> listTransaction = user.getTransactions();
		model.addAttribute("transactions", listTransaction);

		Iterable<Connection> listConnection = connectionService.getConnections();
		model.addAttribute("connections", listConnection);
		
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
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		transaction.setTransactionDate(timestamp);
		transaction.setTransmitterId(2);
		transactionService.saveTransaction(transaction);
		return new ModelAndView("redirect:/transfer");
	}

	@GetMapping("/connections")
	public String createConnection(Model model) {

		Iterable<Connection> listConnection = connectionService.getConnections();
		model.addAttribute("connections", listConnection);

		Connection e = new Connection();
		model.addAttribute("connection", e);
		return "connections";
	}

	@PostMapping("/saveConnection")
	public ModelAndView saveConnection(@ModelAttribute Connection connection) {
		connection.setUserId(2);
		ArrayList<User> listUser = (ArrayList<User>) 
				userService.getUsers();
		for (User user : listUser) {
			if (user.getEmail().equals(connection.getConnectionEmail())) {
				connection.setConnectionId(user.getId());
				connection.setConnectionUsername(user.getUsername());
			}
		}
		connectionService.saveConnection(connection);
		return new ModelAndView("redirect:/connections");
	}

	/*
	 * @GetMapping("/users") public String home(Model model) { ArrayList<User> users
	 * = userService.getUsers(); model.addAttribute("users", users); return "home";
	 * }
	 */

	/*
	 * @GetMapping("/createUser") public String createUser(Model model) { User e =
	 * new User(); model.addAttribute("user", e); return "formNewUser"; }
	 * 
	 * @PostMapping("/saveUser") public ModelAndView saveUser(@ModelAttribute User
	 * user) { if (user.getId() != 0) { } userService.saveUser(user); return new
	 * ModelAndView("redirect:/"); }
	 * 
	 * @GetMapping("/updateUser/{id}") public String updateUser(@PathVariable("id")
	 * final int id, Model model) { User e = userService.getUser(id);
	 * model.addAttribute("user", e); return "formUpdateUser"; }
	 * 
	 * @GetMapping("/deleteUser/{id}") public ModelAndView
	 * deleteUser(@PathVariable("id") final int id) { userService.deleteUser(id);
	 * return new ModelAndView("redirect:/"); }
	 */

}