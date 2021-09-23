package com.paymybuddy.api.model;

import java.sql.Date;
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

	@Column(name = "transaction_date")
	private Timestamp transactionDate;

	@Column(name = "transmitter_id")
	private int transmitterId;

	@Column(name = "beneficiary_id")
	private int beneficiaryId;

	private Float amount;

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

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		return Objects.equals(amount, other.amount) && beneficiaryId == other.beneficiaryId
				&& Objects.equals(transactionDate, other.transactionDate) && Objects.equals(description, other.description) && id == other.id
				&& transmitterId == other.transmitterId;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", date=" + transactionDate + ", transmitterId=" + transmitterId + ", beneficiaryId="
				+ beneficiaryId + ", amount=" + amount + ", description=" + description + "]";
	}

	

	

}
