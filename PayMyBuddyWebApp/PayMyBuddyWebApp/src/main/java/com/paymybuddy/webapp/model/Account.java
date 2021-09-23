package com.paymybuddy.webapp.model;

public class Account {

	private int id;
	
	private Integer userId;
	
	private Float balance;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Float getBalance() {
		return balance;
	}
	
	public void setBalance(Float balance) {
		this.balance = balance;
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
