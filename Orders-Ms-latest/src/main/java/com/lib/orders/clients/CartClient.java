package com.lib.orders.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.lib.orders.models.Book;

@FeignClient(name = "cart-ms")
public interface CartClient {

	@GetMapping("/get-cart/{userName}")
	List<Book> getCart(@PathVariable String userName, @RequestHeader(value="Authorization") String auth);
	
	@DeleteMapping("/check-out/{username}")
	void checkOut(@PathVariable String username, @RequestHeader(value="Authorization") String auth);
	
}
