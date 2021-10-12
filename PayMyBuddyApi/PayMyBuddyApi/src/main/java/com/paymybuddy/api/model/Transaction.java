package com.paymybuddy.api.model;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "date")
	private Timestamp transactionDate;

	@Column(name = "transmitter_id")
	private int transmitterId;

	@Column(name = "beneficiary_id")
	private int beneficiaryId;

	@Column(name = "beneficiary_username")
	private String beneficiaryUsername;

	private double amount;

	private String description;
	

	public Transaction() {
	}
	
	

	public Transaction(int id, Timestamp transactionDate, int transmitterId, int beneficiaryId,
			String beneficiaryUsername, double amount, String description) {
		super();
		this.id = id;
		this.transactionDate = transactionDate;
		this.transmitterId = transmitterId;
		this.beneficiaryId = beneficiaryId;
		this.beneficiaryUsername = beneficiaryUsername;
		this.amount = amount;
		this.description = description;
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

	@Override
	public int hashCode() {
		return Objects.hash(amount, beneficiaryId, transactionDate, description, id, transmitterId);
	}	

}
