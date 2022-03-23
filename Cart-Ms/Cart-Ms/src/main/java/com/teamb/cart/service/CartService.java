package com.teamb.cart.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.teamb.cart.model.Book;

/**
 * provides services for user's cart
 * @author team-b
 * @version latest
 */
public interface CartService {
	
	/**
	 * method adds book to the cart
	 * @param userName book gets added to this user
	 * @param name name of the book
	 * @return status whether book is added or not
	 */
	ResponseEntity<Object> addToCart(String userName, String name, String auth);

	/**
	 * cart of the user
	 * @param userName retrieves all books in the cart
	 * @return list of books in user's cart
	 */
	List<Book> getCart(String userName, String auth);

	/**
	 * method removes book from the cart
	 * @param userName book gets removed from this user
	 * @param bookName name of the book
	 * @return status whether book is removed
	 */
	ResponseEntity<Object> removeBookFromCart(String userName, String bookName, String auth);

	/**
	 * method will empty the cart for placing order
	 * @param username user for whom transaction happens
	 */
	void checkOut(String username, String auth);	

}
