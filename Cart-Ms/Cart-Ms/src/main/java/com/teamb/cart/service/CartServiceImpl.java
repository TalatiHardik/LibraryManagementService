package com.teamb.cart.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.teamb.cart.feigns.AuthClient;
import com.teamb.cart.feigns.BookClient;
import com.teamb.cart.model.Book;
import com.teamb.cart.model.Cart;
import com.teamb.cart.model.Response;
import com.teamb.cart.repository.CartRepository;

import feign.FeignException;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private BookClient client;
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private AuthClient authClient;
	private static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);
	
	@Override
	public ResponseEntity<Object> addToCart(String userName, String name, String auth) {
		log.info("inside addToCart - service");
		try {
			if(authClient.validateToken(auth.substring(7)).getStatusCodeValue()==200) {
				client.findById(name, auth);
				cartRepo.save(new Cart(0, userName, name));
				return new ResponseEntity<>(new Response("Successful"), HttpStatus.ACCEPTED);
			}
		} catch(FeignException e) {
			log.error("inside addToCart - service");
		}
		return new ResponseEntity<>(new Response("book doesn't exist in inventory"), HttpStatus.BAD_REQUEST);
	}

	@Override
	public List<Book> getCart(String userName, String auth) {
		log.info("inside getCart - service");
		try {
			if(authClient.validateToken(auth.substring(7)).getStatusCodeValue()==200) {
				List<Cart> cart = cartRepo.findByUserName(userName);
				List<Book> books = new ArrayList<>();
				for(Cart c: cart) {
					books.add(client.findById(c.getBookName(), auth));
				}		
				return books;
			}
		} catch(FeignException e) {
			log.error("inside getCart - service");
		}
		return new ArrayList<Book>();
	}

	@Override
	public ResponseEntity<Object> removeBookFromCart(String userName, String bookName, String auth) {	
		log.info("inside removeBookFromCart - service");
		try {
			if(authClient.validateToken(auth.substring(7)).getStatusCodeValue()==200) {
				client.findById(bookName, auth);
				List<Cart> crts = cartRepo.findByUserNameAndBookName(userName, bookName);
				if(!crts.isEmpty()) {
					cartRepo.delete(crts.get(0));
					return new ResponseEntity<>(new Response("successfully deleted !!!!"), HttpStatus.ACCEPTED);
				}
				return new ResponseEntity<>(new Response("book doesn't exist in cart"), HttpStatus.NOT_FOUND);
			} 
		} catch(FeignException e) {
			log.error("inside removeBookFromCart - service");
		}
		return new ResponseEntity<>(new Response("book doesn't exist in inventory"), HttpStatus.BAD_REQUEST);
	}

	@Override
	public void checkOut(String username, String auth) {
		log.info("inside checkOut - service");
		try {
			if(authClient.validateToken(auth.substring(7)).getStatusCodeValue()==200) {
				cartRepo.delAllByUserName(username);
			}
		} catch(FeignException e) {
			log.error("inside checkOut - service");
		}
	}

}
