package com.kswaughs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.kswaughs.services.booksvc.AddBookRequest;
import com.kswaughs.services.booksvc.AddBookResponse;
import com.kswaughs.services.booksvc.Book;
import com.kswaughs.services.booksvc.GetBookRequest;
import com.kswaughs.services.booksvc.GetBookResponse;
import com.kswaughs.services.booksvc.ObjectFactory;

@Component
public class BookServiceClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceClient.class);
	
	@Autowired
	private WebServiceTemplate webServiceTemplate;
	public String addBook(String name, String author, String price) {
		ObjectFactory factory = new ObjectFactory();
		AddBookRequest req = factory.createAddBookRequest();
		Book book = new Book();
		book.setAuthor(author);
		book.setName(name);
        book.setPrice(price);

        req.setBook(book);
        
        LOGGER.info("Client sending book[Name={},", book.getName());
        AddBookResponse resp = (AddBookResponse) webServiceTemplate.marshalSendAndReceive(req);
        LOGGER.info("Client received status='{}'", resp.getStatus());
        
        return resp.getStatus();
	}
	
	public Book getBook(String name) {
		ObjectFactory factory = new ObjectFactory();
		GetBookRequest req = factory.createGetBookRequest();
		req.setName(name);
		LOGGER.info("Client sending book[Name={},", name);
		GetBookResponse resp = (GetBookResponse) webServiceTemplate.marshalSendAndReceive(req);
		LOGGER.info("Client received book='{}'", resp.getBook());
		return resp.getBook();
	}
}

