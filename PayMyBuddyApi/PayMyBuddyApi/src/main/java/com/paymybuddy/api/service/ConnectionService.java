package com.paymybuddy.api.service;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.api.model.Connection;
import com.paymybuddy.api.repository.ConnectionRepository;

@Service
public class ConnectionService {

	private static final Logger logger = LogManager.getLogger("UserController");

	@Autowired
	private ConnectionRepository connectionRepository;

	public ArrayList<Connection> getConnections() {
		ArrayList<Connection> connections = (ArrayList<Connection>) connectionRepository.findAll();

		return connections;
	}

	public Optional<Connection> getConnection(final Integer id) {
		return connectionRepository.findById(id);
	}

	public void deleteConnection(final Integer id) {
		connectionRepository.deleteById(id);
	}

	public Connection saveConnection(Connection connection) {
		Connection savedConnection = connectionRepository.save(connection);
		return savedConnection;
	}

}
