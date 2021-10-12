package com.paymybuddy.api.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.api.model.Connection;
import com.paymybuddy.api.service.ConnectionService;

@RestController
public class ConnectionController {

	private static final Logger logger = LogManager.getLogger("UserController");

	@Autowired
	ConnectionService connectionService;

	// Get all connections, returns a list of all connections
	@GetMapping("/connections")
	public ArrayList<Connection> getConnections() {
		ArrayList<Connection> connections = connectionService.getConnections();
		return connections;
	}

	// Create - Add a new connection
	@PostMapping("/connection")
	public Connection createConnection(@RequestBody Connection connection) {
		return connectionService.saveConnection(connection);
	}

	// - Get one connection
	@GetMapping("/connection/{id}")
	public Connection getConnection(@PathVariable("id") final Integer id) {
		Optional<Connection> connection = connectionService.getConnection(id);
		if (connection.isPresent()) {
			return connection.get();
		} else {
			return null;
		}
	}

	// Delete an connection
	@DeleteMapping("/connection/{id}")
	public void deleteConnection(@PathVariable("id") final Integer id) {
		connectionService.deleteConnection(id);
	}

}
