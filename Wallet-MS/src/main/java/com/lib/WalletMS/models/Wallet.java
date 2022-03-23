package com.lib.WalletMS.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@Entity
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int walletId;
	
	private String username;
	private double balance;
	
	@OneToMany(cascade = {CascadeType.ALL} , orphanRemoval = true , fetch = FetchType.LAZY)
	@JoinColumn(name = "walletId")
	private List<FinedOrder> orders;

	
	public Wallet() {
		super();
	}

	public Wallet(int walletId, String username, double balance, List<FinedOrder> orders) {
		super();
		this.walletId = walletId;
		this.username = username;
		this.balance = balance;
		this.orders = orders;
	}

	public int getWalletId() {
		return walletId;
	}

	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<FinedOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<FinedOrder> orders) {
		this.orders = orders;
	}
	
	public void addOrders(FinedOrder order) {
		orders.add(order);
	}

	@Override
	public String toString() {
		return "Wallet [walletId=" + walletId + ", username=" + username + ", balance=" + balance + ", orders=" + orders
				+ "]";
	}
	
	
	
	
	
}
