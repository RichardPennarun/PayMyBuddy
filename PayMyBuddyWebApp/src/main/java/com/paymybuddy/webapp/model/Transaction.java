package com.paymybuddy.webapp.model;

public class Transaction {
	
	private int id;
	
	private String date;
	
	private int transmitterId;
	
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
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
