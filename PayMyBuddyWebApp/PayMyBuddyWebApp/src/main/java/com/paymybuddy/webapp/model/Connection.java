package com.paymybuddy.webapp.model;

import java.util.Objects;


public class Connection {

	private int id;
	
	private int userId;
	
	private int connectionId;
	
	private String connectionEmail;
	
	private String connectionUsername;
	
	public Connection( ) {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getConnectionId() {
		return connectionId;
	}

	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}

	public String getConnectionEmail() {
		return connectionEmail;
	}

	public void setConnectionEmail(String connectionEmail) {
		this.connectionEmail = connectionEmail;
	}

	public String getConnectionUsername() {
		return connectionUsername;
	}

	public void setConnectionUsername(String connectionUsername) {
		this.connectionUsername = connectionUsername;
	}

}
