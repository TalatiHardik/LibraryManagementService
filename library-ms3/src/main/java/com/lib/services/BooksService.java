package com.lib.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lib.clients.AuthClient;
import com.lib.exceptions.BookNotFoundException;
import com.lib.models.Book;
import com.lib.repositories.BooksRepository;

import feign.FeignException;

@Service
public class BooksService implements IBooksService {

	@Autowired
	private BooksRepository booksRepo;
	@Autowired
	private AuthClient authClient;
	private static final Logger log = LoggerFactory.getLogger(BooksService.class);
	
	@Override
	public ResponseEntity<Object> getBooks() {
		log.info("inside getBooks - service");
		return new ResponseEntity<>(booksRepo.findAll(), HttpStatus.OK);
	}

	@Override
	public Book findById(String bookid, String auth) {
		log.info("inside findById - service");
		try {
			if(authClient.validateToken(auth.substring(7)).getStatusCodeValue()==200) {
				return booksRepo.findById(bookid).orElseThrow(BookNotFoundException::new);
			}
		} catch(FeignException e) {
			log.error("inside findById - service");
		}
		return null;
	}

	@Override
	public boolean decreaseQuantity(String bookid, String auth) {
		log.info("inside decreaseQuantity - service");
		try {
			if(authClient.validateToken(auth.substring(7)).getStatusCodeValue()==200) {
				Book book = booksRepo.findById(bookid).get();
				int status = book.getQuantity();
				if(status==0) {
					return false;
				}
				book.setQuantity(status-1);
				booksRepo.save(book);
				return true;
			}
		} catch(FeignException e) {
			log.error("inside decreaseQuantity - service");
		}
		return false;
	}

	@Override
	public void increaseQuantity(String bookid, String auth) {
		log.info("inside increaseQuantity - service");
		try {
			if(authClient.validateToken(auth.substring(7)).getStatusCodeValue()==200) {
				Book book = booksRepo.findById(bookid).get();
				book.setQuantity(book.getQuantity()+1);
				booksRepo.save(book);
			}
		} catch(FeignException e) {
			log.error("inside increaseQuantity - service");
		}
	}

	@Override
	public ResponseEntity<Object> findByAuthor(String author) {
		log.info("inside findByAuthor - service");
		return new ResponseEntity<>(booksRepo.findByAuthor(author), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> findByGenre(String genre) {
		log.info("inside findByGenre - service");
		return new ResponseEntity<>(booksRepo.findByGenre(genre), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> findByAuthorAndGenre(String author, String genre) {
		log.info("inside findByAuthorAndGenre - service");
		return new ResponseEntity<>(booksRepo.findByAuthorAndGenre(author, genre), HttpStatus.OK);
	}

}
