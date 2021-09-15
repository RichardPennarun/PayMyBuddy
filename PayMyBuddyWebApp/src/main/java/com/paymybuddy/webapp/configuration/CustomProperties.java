package com.paymybuddy.webapp.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="com.paymybuddy.webapp")
public class CustomProperties {
	
	private String apiUrl;
	
	public String getApiUrl() {
		return apiUrl;
	}
	
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

}
