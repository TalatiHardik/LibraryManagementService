package com.lib.orders.controller;

import org.springframework.http.ResponseEntity;

/**
 * for processing orders
 * @author team-b
 * @version latest
 */
public interface IOrdersController {

	/**
	 * method processes user's order
	 * @param username name of the user
	 * @param auth authorization token to allow only authorized user
	 * @return status of order
	 */
	ResponseEntity<Object> processOrder(String username, String auth);

	/**
	 * method retrieves all the orders placed
	 * @param username name of the user
	 * @param auth authorization token to allow only authorized user
	 * @return all books ordered by the user
	 */
	ResponseEntity<Object> getAllBooksOrdered(String username, String auth);

	/**
	 * method to return a book
	 * @param username name of the user
	 * @param bookname book to be returned
	 * @param orderid 
	 * @param auth authorization token to allow only authorized user
	 * @return status of order
	 */
	ResponseEntity<Object> returnBook(String username, String bookname, int orderid, String auth);
	
	/**
	 * method retrieves all the orders placed
	 * @param username name of the user
	 * @param auth authorization token to allow only authorized user
	 * @return all books ordered by the user
	 */
	//ResponseEntity<Object> getOrderDetails(String username, String auth);

	/**
	 * method retrieves all the orders placed
	 * @param username name of the user
	 * @param auth authorization token to allow only authorized user
	 * @return all books ordered by the user
	 */
	//ResponseEntity<Object> getAllOrders(String username, String auth);

}