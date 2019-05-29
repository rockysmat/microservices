package com.kswaughs.services;

import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kswaughs.services.booksvc.Book;

@Component 
public class BookCommandLineListener {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(BookCommandLineListener.class);
	
	@Autowired
	private BookServiceClient bookSvcClient;
	
	@Scheduled(fixedDelay=3000)
	public void run() throws Exception {
		try (Scanner scanner = new Scanner(System.in)) {
			while(true) {
				LOGGER.info("Enter your command");
                String text = scanner.nextLine();

                LOGGER.info("you entered :{}", text);
                String[] args = text.split("\\s+");

                LOGGER.info("args :{}", Arrays.toString(args));
                
                if ("ADD".equalsIgnoreCase(args[0])) {

                    String name = args[1];
                    String author = args[2];
                    String price = args[3];

                    String status = bookSvcClient.addBook(name, author, price);

                    LOGGER.info("Book Added Status :{}", status);
                } else if ("GET".equalsIgnoreCase(args[0])) {
                    String name = args[1];

                    Book book = bookSvcClient.getBook(name);

                    if(book != null) {
                        LOGGER.info("GET Book Details : Name:{}, Author:{}, Price:{}", 
                                book.getName(), book.getAuthor(), book.getPrice());
                    }
                } else {
                    
                    LOGGER.info("Invalid Command."); 
                }            
			}
		}
	}
}
