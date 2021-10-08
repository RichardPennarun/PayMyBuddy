package com.paymybuddy.webapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class WebApp {

	private static final Logger logger = LogManager.getLogger(WebApp.class);

	public static void main(String[] args) {
		logger.info("Initializing Pay My Buddy WebApp");
		SpringApplication.run(WebApp.class, args);
	}

}
