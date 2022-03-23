package com.lib.controllers;

import org.springframework.http.ResponseEntity;

import com.lib.models.Book;

/**
 * inventory of library
 * @author team-b
 * @version latest
 */
public interface IBooksController {

	/**
	 * method finds a specific book
	 * @param bookid only parameter of findById method
	 * @return specific book based on book id
	 */
	Book findById(String bookName, String auth);

	/**
	 * method retrieves all the books available
	 * @return response entity with a specific status 
	 */
	ResponseEntity<Object> getAllBooks();
	
	boolean decreaseQuantity(String bookid, String auth);
	
	void increaseQuantity(String bookid, String auth);
	
	ResponseEntity<Object> findByAuthor(String author);
	
	ResponseEntity<Object> findByGenre(String genre);
	
	ResponseEntity<Object> findByAuthorAndGenre(String author, String genre);

}