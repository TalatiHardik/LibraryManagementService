package com.teamb.cart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
*/

@Entity
public class Cart {	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String userName;	
	private String bookName;	
	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//List<Book> books=new ArrayList<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Cart(int id, String userName, String bookName) {
		super();
		this.id = id;
		this.userName = userName;
		this.bookName = bookName;
	}
	public Cart() {
		super();
	}
	
}
