package com.teamb.cart.model;

/*
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
*/

public class Book {	

	private String name;
	private String author;
	private String genre;
	private String description;
	private byte[] image;
	private int quantity;
	

		public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
		public Book(String name, String author, String genre, String description, byte[] image, int quantity) {
		super();
		this.name = name;
		this.author = author;
		this.genre = genre;
		this.description = description;
		this.image = image;
		this.quantity = quantity;
	}
		public Book() {
		super();
	}
	
}
