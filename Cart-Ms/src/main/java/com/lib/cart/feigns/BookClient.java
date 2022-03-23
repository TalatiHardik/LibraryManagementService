package com.lib.cart.feigns;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.lib.cart.model.Book;

@FeignClient(name = "show-books-ms")
public interface BookClient {
	
	@GetMapping("/books/bookName/{bookName}")
	Book findById(@PathVariable String bookName, @RequestHeader(value="Authorization") String auth);

}
