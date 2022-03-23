package com.lib.orders.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
*/
@Entity
public class OrdersEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int orderid;
	private String username;
	private String checkedBookName;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCheckedBookName() {
		return checkedBookName;
	}
	public void setCheckedBookName(String checkedBookName) {
		this.checkedBookName = checkedBookName;
	}
	public OrdersEntity(int id, int orderid, String username, String checkedBookName) {
		super();
		this.id = id;
		this.orderid = orderid;
		this.username = username;
		this.checkedBookName = checkedBookName;
	}
	public OrdersEntity() {
		super();
	}
	
	
	
	//@OneToMany
	//private List<CheckedOutBook> booknames=new ArrayList<CheckedOutBook>();

}

