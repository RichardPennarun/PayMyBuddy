package com.paymybuddy.api.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(nullable = false, unique = true)
	private String username;

	private String email;

	private String password;
	
	@OneToOne(
			cascade = CascadeType.ALL, 
			orphanRemoval = false)
	@JoinColumn(name = "id", referencedColumnName = "account_user_id")
	private Account account;
	
	@OneToOne(
			cascade = CascadeType.ALL, 
			orphanRemoval = false)
	@JoinColumn(name = "id", referencedColumnName = "bank_account_user_id")
	private BankAccount bankAccount;

	@OneToMany(
			cascade = CascadeType.ALL, 
			orphanRemoval = false, 
			fetch = FetchType.EAGER)
	@JoinColumn(name = "transmitter_id")
	private List<Transaction> transactions = new ArrayList<>();

	@OneToMany(
			cascade = CascadeType.ALL, 
			orphanRemoval = false) 
	@JoinColumn(name = "user_id")
	private List<Connection> connections = new ArrayList<>();


	public User() {
	}
	
	public User(int id, String firstName, String lastName, String username, String email, String password,
			Account account, BankAccount bankAccount, List<Transaction> transactions, List<Connection> connections) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.account = account;
		this.bankAccount = bankAccount;
		this.transactions = transactions;
		this.connections = connections;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public BankAccount getBankAccount() {
		return bankAccount;
	}
	
	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public List<Connection> getConnections() {
		return connections;
	}

	public void setConnections(List<Connection> connections) {
		this.connections = connections;
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, bankAccount, connections, email, firstName, id, lastName, password, transactions, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(account, other.account) && Objects.equals(connections, other.connections)
				&& Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& Objects.equals(transactions, other.transactions) && Objects.equals(username, other.username)
				&& Objects.equals(bankAccount, other.bankAccount);
	}
	
	
	

}
