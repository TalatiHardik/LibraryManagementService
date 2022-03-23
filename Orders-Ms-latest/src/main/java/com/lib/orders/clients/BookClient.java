package com.lib.orders.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.lib.orders.models.Book;

@FeignClient(name = "show-books-ms")
public interface BookClient {
	
	@GetMapping("/books/bookName/{bookName}")
	Book findById(@PathVariable String bookName, @RequestHeader(value="Authorization") String auth);
	
	@PutMapping("/books/decrease-quantity/{bookid}")
	boolean decreaseQuantity(@PathVariable String bookid, @RequestHeader(value="Authorization") String auth);
	
	@PutMapping("/books/increase-quantity/{bookid}")
	void increaseQuantity(@PathVariable String bookid, @RequestHeader(value="Authorization") String auth);

}
