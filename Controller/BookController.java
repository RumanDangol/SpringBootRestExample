package com.fusemachine.libraryManagement.Controller;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fusemachine.libraryManagement.entity.Book;








@RestController 
public class BookController {

	private static BigInteger bookId;
	private static Map< BigInteger, Book> books;
	
	private static Book save(Book book) {
		if (books == null) {
		books = new HashMap<BigInteger, Book>();
		bookId = BigInteger.ONE;
			
		}
		/**
		 *  for book update
		 */
		if(book.getId() != null) {
			Book oldGreeting = books.get(book.getId());
			if(oldGreeting == null) {
				return null;
			}
			books.remove(book.getId());
			books.put(book.getId(), book);
			return book;
		}
		
		/**
		 *  create book
		 */
	book.setId(bookId);
	bookId = bookId.add(BigInteger.ONE);
	books.put(book.getId(), book);
	return book;
	}
	private static boolean delete(BigInteger id) {
		Book deletedBook = books.remove(id);
		if(deletedBook == null) {
			return false;
		}
		return true;
	}
	static {
		for(int i = 1; i<10 ; i++) {
			
			
			Book sti = new Book();
			sti.setName("staff"+i);
			sti.setPublisher("publisher"+i);
			
			save(sti);
			}
		
		
	}
	@RequestMapping(value = "/books", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Book>> getBooks(){
		
		Collection<Book> booksAll = books.values();
		return new ResponseEntity<Collection<Book>>(booksAll, HttpStatus.OK);
	}
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> getBook(@PathVariable("id") BigInteger id){

		
		Book book = books.get(id);
		if(book == null) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Book>(book, HttpStatus.OK);
		
	}
	@RequestMapping(value = "/book/delete/{id}", method = RequestMethod.DELETE ,  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> deleteBook(@PathVariable("id") BigInteger id, @RequestBody Book book){
		
		Boolean deleted = delete(id);
		if(!deleted) {
			return new ResponseEntity<Book>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
	}
	@RequestMapping(value = "/book/create", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> createBook(@RequestBody Book book){
		
		Book savedBook = save(book);
		
		return new ResponseEntity<Book>(savedBook, HttpStatus.CREATED);
		
	}
	@RequestMapping(value = "/book/update/{id}", method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Book> updateBook(@RequestBody Book book){
		
		Book updatedBook = save(book);
		if(updatedBook == null) {
			return new ResponseEntity<Book>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Book>(updatedBook, HttpStatus.OK);
		
	}
}
