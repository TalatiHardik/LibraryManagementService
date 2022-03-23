package com.lib.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lib.models.Book;

@Repository
public interface BooksRepository extends JpaRepository<Book, String> {
	
	/*
	@Query(value="select b from Book b where b.status>0")
	List<Book> getAvailableBooks();
	*/
	
	List<Book> findByAuthor(String author);
	
	List<Book> findByGenre(String genre);
	
	List<Book> findByAuthorAndGenre(String author, String genre);
	
}
