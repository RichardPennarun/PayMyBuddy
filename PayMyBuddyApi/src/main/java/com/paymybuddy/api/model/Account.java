package com.paymybuddy.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "user_id")
	private int userId;

	private Float balance;
	
	public Account() {
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
	
	public Float getBalance() {
		return balance;
	}
	
	public void setBalance(Float balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", userId=" + userId + ", balance=" + balance + "]";
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Account that = (Account) o;
		return id == that.id;
	}

}
