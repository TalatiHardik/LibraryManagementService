package com.lib.orders.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
*/
@Entity
public class CheckedOutBook {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int orderid;
	private String name;
	@Column(columnDefinition = "longblob")
	private byte[] image;
	private LocalDate issue_date;
	private LocalDate return_date;
	private int fine_amount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public LocalDate getIssue_date() {
		return issue_date;
	}
	public void setIssue_date(LocalDate issue_date) {
		this.issue_date = issue_date;
	}
	public LocalDate getReturn_date() {
		return return_date;
	}
	public void setReturn_date(LocalDate return_date) {
		this.return_date = return_date;
	}
	public int getFine_amount() {
		return fine_amount;
	}
	public void setFine_amount(int fine_amount) {
		this.fine_amount = fine_amount;
	}
	public CheckedOutBook(int id, int orderid, String name, byte[] image, LocalDate issue_date, LocalDate return_date,
			int fine_amount) {
		super();
		this.id = id;
		this.orderid = orderid;
		this.name = name;
		this.image = image;
		this.issue_date = issue_date;
		this.return_date = return_date;
		this.fine_amount = fine_amount;
	}
	public CheckedOutBook() {
		super();
	}
	
	
	
	//@JoinColumn(nullable = false, name = "order_id")
	//@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	//private OrdersEntity  order;
	
}
