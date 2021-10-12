package com.paymybuddy.webapp.model;

import java.util.Objects;

public class BankAccount {

	private int id;

	private int userId;

	private String iban;

	public BankAccount() {
	}

	public BankAccount(int id, Integer userId, String iban) {
		super();
		this.id = id;
		this.userId = userId;
		this.iban = iban;
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

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	@Override
	public int hashCode() {
		return Objects.hash(iban, id, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		return Objects.equals(iban, other.iban) && id == other.id && Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", userId=" + userId + ", iban=" + iban + "]";
	}

}