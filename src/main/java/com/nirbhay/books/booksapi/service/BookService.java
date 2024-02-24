package com.nirbhay.books.booksapi.service;

import com.nirbhay.books.booksapi.domain.Book;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface BookService {
    Book createBook(Book book);
    Optional<Book> findById(String isbn);
}
