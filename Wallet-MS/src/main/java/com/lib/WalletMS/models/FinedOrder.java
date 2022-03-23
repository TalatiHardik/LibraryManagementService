package com.lib.WalletMS.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FinedOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column(nullable = false, updatable = false)
	private int finedId;
	
	private int orderId;
	private int walletId;
	private String bookName;
	private double amount;
	private String transaction;
	private LocalDate dateoftransaction;
	
	
	
	
	public FinedOrder() {
		super();
	}
	public FinedOrder(int finedId, int orderId, int walletId, String bookName, double amount, String transaction,
			LocalDate dateoftransaction) {
		this.finedId = finedId;
		this.orderId = orderId;
		this.walletId = walletId;
		this.bookName = bookName;
		this.amount = amount;
		this.transaction = transaction;
		this.dateoftransaction = dateoftransaction;
	}
	
	public FinedOrder(int orderId, int walletId, String bookName, double amount, String transaction,
			LocalDate dateoftransaction) {
		this.orderId = orderId;
		this.walletId = walletId;
		this.bookName = bookName;
		this.amount = amount;
		this.transaction = transaction;
		this.dateoftransaction = dateoftransaction;
	}
	
	public FinedOrder(int orderId, int walletId, double amount, String transaction, LocalDate dateoftransaction) {
		super();
		this.orderId = orderId;
		this.walletId = walletId;
		this.amount = amount;
		this.transaction = transaction;
		this.dateoftransaction = dateoftransaction;
	}
	public FinedOrder(int finedId, int orderId, int walletId, double amount, String transaction,
			LocalDate dateoftransaction) {
		super();
		this.finedId = finedId;
		this.orderId = orderId;
		this.walletId = walletId;
		this.amount = amount;
		this.transaction = transaction;
		this.dateoftransaction = dateoftransaction;
	}
	public int getFinedId() {
		return finedId;
	}
	public void setFinedId(int finedId) {
		this.finedId = finedId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getWalletId() {
		return walletId;
	}
	public void setWalletId(int walletId) {
		this.walletId = walletId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTransaction() {
		return transaction;
	}
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	public LocalDate getDateoftransaction() {
		return dateoftransaction;
	}
	public void setDateoftransaction(LocalDate dateoftransaction) {
		this.dateoftransaction = dateoftransaction;
	}
	@Override
	public String toString() {
		return "FinedOrder [finedId=" + finedId + ", orderId=" + orderId + ", walletId=" + walletId + ", bookName="
				+ bookName + ", amount=" + amount + ", transaction=" + transaction + ", dateoftransaction="
				+ dateoftransaction + "]";
	}
	
	
	
	
	
	

}
