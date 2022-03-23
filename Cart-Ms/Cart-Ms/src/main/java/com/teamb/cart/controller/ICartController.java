package com.teamb.cart.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.teamb.cart.model.Book;

/**
 * user's cart
 * @author team-b
 * @version latest
 */
public interface ICartController {

	/**
	 * cart for adding books
	 * @param bookname name of the book
	 * @param username name of the user
	 * @param auth authorization token to allow only authorized user
	 * @return response whether book is added in the cart or not
	 */
	ResponseEntity<Object> addToCart(String username, String bookname, String auth);

	/**
	 * cart of the user
	 * @param userName userName retrieves all books in the cart
	 * @return list of books in user's cart
	 */
	List<Book> getCart(String userName, String auth);

	/**
	 * removes book from the cart
	 * @param bookName name of the book
	 * @param username name of the user
	 * @param auth authorization token to allow only authorized user
	 * @return response whether book is removed
	 */
	ResponseEntity<Object> removeBookFromCart(String bookName, String username, String auth);

	/**
	 * method will empty the cart for placing order
	 * @param username user for whom transaction happens
	 */
	void checkOut(String username, String auth);

	/**
	 * quantity in the cart
	 * @param userName user's cart
	 * @param auth authorization token to allow only authorized user
	 * @return size of the cart
	 */
	ResponseEntity<Object> getCartSize(String userName, String auth);

}