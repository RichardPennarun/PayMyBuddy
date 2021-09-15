package com.paymybuddy.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "transaction_date")
	private String date;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
	public String toString() {
		return "Transaction [id=" + id + ", date=" + date + ", transmitterId=" 
				+ transmitterId + ", beneficiaryId=" + beneficiaryId + 
				", amount=" + amount + ", description=" + description + "]";

	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Transaction that = (Transaction) o;
		return id == that.id;
	}

}
