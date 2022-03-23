package com.teamb.cart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.teamb.cart.model.Book;
import com.teamb.cart.service.CartService;

@RestController
public class CartController implements ICartController {
	
	@Autowired
	private CartService service;
	private static final Logger log = LoggerFactory.getLogger(CartController.class);
	
	@Override
	@PostMapping("/add-to-cart/{username}/{bookname}")
	public ResponseEntity<Object> addToCart(@PathVariable String bookname, @PathVariable String username, @RequestHeader("Authorization") String auth) {
		log.info("inside addToCart - controller");
		return service.addToCart(username, bookname, auth);
	}
	
	@Override
	@GetMapping("/get-cart/{userName}")
	public List<Book> getCart(@PathVariable String userName, @RequestHeader("Authorization") String auth){	
		log.info("inside getCart - controller");
		return service.getCart(userName, auth);
	}
	
	@Override
	@DeleteMapping("/remove-book/{username}/{bookName}")
	public ResponseEntity<Object> removeBookFromCart(@PathVariable String bookName, @PathVariable String username, @RequestHeader("Authorization") String auth) {
		log.info("inside removeBookFromCart - controller");
		return service.removeBookFromCart(username, bookName, auth);
	}
	
	@Override
	@DeleteMapping("/check-out/{username}")
	public void checkOut(@PathVariable String username, @RequestHeader("Authorization") String auth) {
		log.info("inside checkOut - controller");
		service.checkOut(username, auth);
	}
	
	@Override
	@GetMapping("/get-cart-size/{userName}")
    public ResponseEntity<Object> getCartSize(@PathVariable String userName, @RequestHeader("Authorization") String auth) {
		log.info("inside getCartSize - controller");
		return new ResponseEntity<>((service.getCart(userName, auth).size()), HttpStatus.OK); 
    }
	
}	
