package com.paymybuddy.api.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "connections")
public class Connection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "connection_id")
	private int connectionId;
	
	@Column(name = "connection_email")
	private String connectionEmail;
	
	@Column(name = "connection_username")
	private String connectionUsername;
	
	
	public Connection() {
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

	@Override
	public int hashCode() {
		return Objects.hash(connectionUsername, connectionEmail, connectionId, id, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Connection other = (Connection) obj;
		return Objects.equals(connectionUsername, other.connectionUsername)
				&& Objects.equals(connectionEmail, other.connectionEmail) && connectionId == other.connectionId
				&& id == other.id && userId == other.userId;
	}

	@Override
	public String toString() {
		return "Connection [id=" + id + ", userId=" + userId + ", connectionId=" + connectionId + ", connectionEmail="
				+ connectionEmail + ", connectionUsername=" + connectionUsername + "]";
	}
	
	
	
	
	
}
