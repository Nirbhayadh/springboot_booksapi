package com.nirbhay.books.booksapi.controller;


import com.nirbhay.books.booksapi.domain.Book;
import com.nirbhay.books.booksapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping(path= "/book/{isbn}")
    public ResponseEntity<Book> createBook(
            @PathVariable final String isbn,
            @RequestBody final Book book){
        book.setIsbn(isbn);
        final Book savedBook = bookService.createBook(book);
        final ResponseEntity<Book> response= new ResponseEntity<Book>(savedBook, HttpStatus.CREATED);
        return response;
    }

    @GetMapping(path="/books/{isbn}")
    public ResponseEntity<Book> retrieveBook(@PathVariable final String isbn){
        final Optional<Book> foundBook = bookService.findById(isbn);
        return foundBook.map(book -> new ResponseEntity<Book>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<Book>(HttpStatus.NOT_FOUND));


    }
}
