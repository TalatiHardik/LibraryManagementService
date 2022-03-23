package com.lib.orders.service;

import org.springframework.http.ResponseEntity;

/**
 * provides services for orders
 * @author team-b
 * @version latest
 */
public interface IOrdersService {
			
	/**
	 * method processes user's order
	 * @param username name of the user
	 * @return placed book
	 */
	ResponseEntity<Object> processOrder(String username, String auth);
	
	/**
	 * method to return a book
	 * @param username name of the user
	 * @param bookname book to be returned
	 * @param orderid 
	 */
	ResponseEntity<Object> returnBook(String username, String bookname, int orderid, String auth);
	
	/**
	 * method retrieves all the orders placed
	 * @param username name of the user
	 * @return all the orders placed
	 */
	ResponseEntity<Object> getAllBooksOrdered(String username, String auth);
	
	/**
	 * method retrieves all the orders placed
	 * @param username name of the user
	 * @return all the orders placed
	 */
	//public List<CheckedOutBook> getOrderDetails(String username);

	/**
	 * method retrieves all the orders placed
	 * @param username name of the user
	 * @return all the orders placed
	 */
	//public List<OrdersEntity> getAllOrders(String username);

}
