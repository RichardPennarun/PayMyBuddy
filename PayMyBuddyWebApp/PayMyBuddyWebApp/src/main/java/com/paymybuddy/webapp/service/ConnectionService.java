package com.paymybuddy.webapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.webapp.model.Connection;
import com.paymybuddy.webapp.repository.ConnectionProxy;

@Service
public class ConnectionService {

    @Autowired
    private ConnectionProxy connectionProxy;

    public ArrayList<Connection> getConnections() {
        return connectionProxy.getConnections();
    }
	
	public Connection getConnection(final int id) {
		return connectionProxy.getConnection(id);
	}
	
	public void deleteConnection(final int id) {
		connectionProxy.deleteConnection(id);
	}
	
	public Connection saveConnection(Connection Connection) {
		Connection savedConnection;
		
		// Functional rule : Last name must be capitalized.
		//Connection.setLastName(Connection.getLastName());

		if(Connection.getId() == 0) {
			// If id is null, then it is a new Connection.
			savedConnection = connectionProxy.createConnection(Connection);
		} else {
			savedConnection = connectionProxy.updateConnection(Connection);
		}
		
		return savedConnection;
	}

}