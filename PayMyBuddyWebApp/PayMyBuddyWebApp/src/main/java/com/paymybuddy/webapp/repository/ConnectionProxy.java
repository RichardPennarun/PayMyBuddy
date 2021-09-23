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

    /**
    * Get all connections
    * @return An iterable of all connections
    */

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
	
	/**
	 * Get an connection by the id
	 * @param id The id of the connection
	 * @return The connection which matches the id
	 */
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
		
		//log.debug("Get Connection call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Add a new connection 
	 * @param e A new connection (without an id)
	 * @return The connection full filled (with an id)
	 */
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
		
		//log.debug("Create Connection call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Update an connection - using the PUT HTTP Method.
	 * @param e Existing connection to update
	 */
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
		
		//log.debug("Update Connection call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	/**
	 * Delete an connection using exchange method of RestTemplate
	 * instead of delete method in order to log the response status code.
	 * @param e The connection to delete
	 */
	public void deleteConnection(int id) {
		String baseApiUrl = props.getApiUrl();
		String deleteConnectionUrl = baseApiUrl + "/connection/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteConnectionUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		//log.debug("Delete Connection call " + response.getStatusCode().toString());
	}

}