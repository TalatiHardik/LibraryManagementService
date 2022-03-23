package com.lib.orders.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lib.orders.service.IOrdersService;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/orders")
public class OrdersController implements IOrdersController {
	
	@Autowired
	private IOrdersService ordersService;
	private static final Logger log = LoggerFactory.getLogger(OrdersController.class);

	@Override
	@PostMapping("/process-order/{username}")
	public ResponseEntity<Object> processOrder(@PathVariable String username, @ApiIgnore @RequestHeader("Authorization") String auth) {
		log.info("inside processOrder - controller");
		return ordersService.processOrder(username, auth);
	}

	//show-return-books
	@Override
	@GetMapping("/get-all-books-ordered/{username}")
	public ResponseEntity<Object> getAllBooksOrdered(@PathVariable String username, @ApiIgnore @RequestHeader("Authorization") String auth) {
		log.info("inside getAllBooksOrdered - controller");
		return ordersService.getAllBooksOrdered(username, auth);
	}
	
	@Override
	@DeleteMapping("/return-book/{username}/{bookname}/{orderid}")
	public ResponseEntity<Object> returnBook(@PathVariable String username, @PathVariable String bookname, @PathVariable int orderid,@ApiIgnore @RequestHeader("Authorization") String auth) {		
		log.info("inside returnBook - controller");
		return ordersService.returnBook(username, bookname, orderid, auth);
	}

}
