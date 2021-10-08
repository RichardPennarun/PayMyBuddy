package com.paymybuddy.api.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bank_accounts")
public class BankAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "bank_account_user_id")
	private int userId;
	
	private String iban;

	public BankAccount() {
		
	}
	
	public BankAccount(int id, int userId, String iban) {
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
	public String toString() {
		return "BankAccount [id=" + id + ", userId=" + userId + ", iban=" + iban + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, iban, userId);
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
		return id == other.id && Objects.equals(iban, other.iban) && userId == other.userId;
	}
	
	

}
