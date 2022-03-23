package com.lib.services;

import org.springframework.http.ResponseEntity;

import com.lib.models.Book;

/**
 * provides inventory related services for library
 * @author team-b
 * @version latest
 */
public interface IBooksService {
	
	/**
	 * method retrieves all the books
	 * @return list of books 
	 */
	ResponseEntity<Object> getBooks();
	
	/**
	 * method finds a specific book
	 * @param bookid only parameter of findById method
	 * @return specific book based on book id
	 */
	Book findById(String bookid, String auth);
	
	boolean decreaseQuantity(String bookid, String auth);
	
	void increaseQuantity(String bookid, String auth);
	
	ResponseEntity<Object> findByAuthor(String author);
	
	ResponseEntity<Object> findByGenre(String genre);
	
	ResponseEntity<Object> findByAuthorAndGenre(String author, String genre);

}
