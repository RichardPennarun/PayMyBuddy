package com.paymybuddy.webapp.model;

import java.sql.Timestamp;


public class Transaction {

	private int id;

	private Timestamp transactionDate;

	private int transmitterId;

	private int beneficiaryId;

	private String beneficiaryUsername;
	
	private double amount;
	
	private String description;
	
	public Transaction() {
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Timestamp getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	public int getTransmitterId() {
		return transmitterId;
	}
	
	public void setTransmitterId(int transmitterId) {
		this.transmitterId = transmitterId;
	}
	
	public int getBeneficiaryId() {
		return beneficiaryId;
	}
	
	public void setBeneficiaryId(int beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}
	
	public String getBeneficiaryUsername() {
		return beneficiaryUsername;
	}
	
	public void setBeneficiaryUsername(String beneficiaryUsername) {
		this.beneficiaryUsername = beneficiaryUsername;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

}
