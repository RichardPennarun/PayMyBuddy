package com.paymybuddy.webapp.repository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.paymybuddy.webapp.configuration.CustomProperties;
import com.paymybuddy.webapp.model.Connection;

@Component
public class ConnectionProxy {

    @Autowired
    private CustomProperties props;

    //Get all connections
    public ArrayList<Connection> getConnections() {
        String baseApiUrl = props.getApiUrl();
        String getConnectionsUrl = baseApiUrl + "/connections";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ArrayList<Connection>> response = restTemplate.exchange(
                getConnectionsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ArrayList<Connection>>() {}
                );
        return response.getBody();
    }
	
	// Get a connection by the id
	public Connection getConnection(int id) {
		String baseApiUrl = props.getApiUrl();
		String getConnectionUrl = baseApiUrl + "/connection/" + id;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Connection> response = restTemplate.exchange(
				getConnectionUrl, 
				HttpMethod.GET, 
				null,
				Connection.class
			);
		return response.getBody();
	}
	
	//Add a new connection 
	public Connection createConnection(Connection e) {
		String baseApiUrl = props.getApiUrl();
		String createConnectionUrl = baseApiUrl + "/connection";
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Connection> request = new HttpEntity<Connection>(e);
		ResponseEntity<Connection> response = restTemplate.exchange(
				createConnectionUrl, 
				HttpMethod.POST, 
				request, 
				Connection.class);
		return response.getBody();
	}
	
	//Update a connection
	public Connection updateConnection(Connection e) {
		String baseApiUrl = props.getApiUrl();
		String updateConnectionUrl = baseApiUrl + "/connection/" + e.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Connection> request = new HttpEntity<Connection>(e);
		ResponseEntity<Connection> response = restTemplate.exchange(
				updateConnectionUrl, 
				HttpMethod.PUT, 
				request, 
				Connection.class);
		return response.getBody();
	}
	
	//Delete a connection
	public void deleteConnection(int id) {
		String baseApiUrl = props.getApiUrl();
		String deleteConnectionUrl = baseApiUrl + "/connection/" + id;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteConnectionUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
	}

}