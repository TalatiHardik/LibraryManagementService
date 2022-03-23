package com.lib.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lib.models.Book;
import com.lib.services.IBooksService;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/books")	
public class BooksController implements IBooksController {
	
	@Autowired
	private IBooksService booksService;
	private static final Logger log = LoggerFactory.getLogger(BooksController.class);
	
	@Override
	@GetMapping("/bookName/{bookName}")
	public Book findById(@PathVariable String bookName,@ApiIgnore @RequestHeader(value="Authorization") String auth) {
		log.info("inside findById - controller");
		return booksService.findById(bookName, auth);
	}
	
	@Override
	@GetMapping("/get-all-books")
	public ResponseEntity<Object> getAllBooks() {
		log.info("inside getFullBooks - controller");
		return booksService.getBooks();
	}

	@Override
	@PutMapping("/decrease-quantity/{bookid}")
	public boolean decreaseQuantity(@PathVariable String bookid,@ApiIgnore @RequestHeader(value="Authorization") String auth) {
		log.info("inside decreaseQuantity - controller");
		return booksService.decreaseQuantity(bookid, auth);
	}

	@Override
	@PutMapping("/increase-quantity/{bookid}")
	public void increaseQuantity(@PathVariable String bookid,@ApiIgnore @RequestHeader(value="Authorization") String auth) {
		log.info("inside increaseQuantity - controller");
		booksService.increaseQuantity(bookid, auth);
	}

	@Override
	@GetMapping("/filter/author/{author}")
	public ResponseEntity<Object> findByAuthor(@PathVariable String author) {
		log.info("inside findByAuthor - controller");
		return booksService.findByAuthor(author);
	}

	@Override
	@GetMapping("/filter/genre/{genre}")
	public ResponseEntity<Object> findByGenre(@PathVariable String genre) {
		log.info("inside findByGenre - controller");
		return booksService.findByGenre(genre);
	}

	@Override
	@GetMapping("/filter/author+genre/{author}/{genre}")
	public ResponseEntity<Object> findByAuthorAndGenre(@PathVariable String author, @PathVariable String genre) {
		log.info("inside findByAuthorAndGenre - controller");
		return booksService.findByAuthorAndGenre(author, genre);
	}
	
}
