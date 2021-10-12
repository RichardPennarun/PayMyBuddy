package com.paymybuddy.webapp.model;

import java.util.Objects;

public class Account {

	private int id;

	private int userId;

	private double balance;

	public Account() {

	}

	public Account(int id, Integer userId, Float balance) {
		super();
		this.id = id;
		this.userId = userId;
		this.balance = balance;
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double transmitterBalance) {
		this.balance = transmitterBalance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(balance, id, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Double.doubleToLongBits(balance) == Double.doubleToLongBits(other.balance) && id == other.id
				&& Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", userId=" + userId + ", balance=" + balance + "]";
	}

}
