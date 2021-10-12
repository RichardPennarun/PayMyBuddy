package com.paymybuddy.webapp.controller;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.paymybuddy.webapp.model.Connection;
import com.paymybuddy.webapp.model.User;
import com.paymybuddy.webapp.service.ConnectionService;
import com.paymybuddy.webapp.service.CustomUserDetailsService;
import com.paymybuddy.webapp.service.UserService;

@Controller
public class ConnectionController {

	private static final Logger logger = LogManager.getLogger("WebAppController");

	@Autowired
	private UserService userService;

	@Autowired
	private ConnectionService connectionService;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	/*
	 * Page Connections
	 */
	@GetMapping("/connections")
	public String createConnection(Model model) {

		// Affiche la liste des connections du CurrentlyLoggedInUser
		Integer userId = customUserDetailsService.getCurrentlyLoggedInUserId();
		User user = userService.getUser(userId);
		Iterable<Connection> listConnection = user.getConnections();
		model.addAttribute("connections", listConnection);

		// Création d'une nouvelle connection dont on récupère l'email par formulaire
		Connection connection = new Connection();
		model.addAttribute("connection", connection);
		return "connections";
	}

	/*
	 * Enregistrement de la nouvelle connection
	 */
	@PostMapping("/saveConnection")
	public ModelAndView saveConnection(@ModelAttribute Connection connection) {

		try {
			// Ajout de l'user à la connection
			int userId = customUserDetailsService.getCurrentlyLoggedInUserId();
			connection.setUserId(userId);

			// Récupération de l'id et du username de la connection
			ArrayList<User> listUser = (ArrayList<User>) userService.getUsers();
			for (User user : listUser) {
				if (user.getEmail().equals(connection.getConnectionEmail())) {
					connection.setConnectionId(user.getId());
					connection.setConnectionUsername(user.getUsername());
				}
			}

			connectionService.saveConnection(connection);
			return new ModelAndView("redirect:/connections");

		} catch (Exception e) {
			logger.error("Unable to save new connection", e);
		}
		return new ModelAndView("redirect:/connections");

	}

}