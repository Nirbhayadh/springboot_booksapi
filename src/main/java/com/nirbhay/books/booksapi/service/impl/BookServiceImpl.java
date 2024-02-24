package com.nirbhay.books.booksapi.service.impl;

import com.nirbhay.books.booksapi.domain.Book;
import com.nirbhay.books.booksapi.domain.BookEntity;
import com.nirbhay.books.booksapi.repository.BookRepository;
import com.nirbhay.books.booksapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    @Autowired
    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository= bookRepository;
    }

    @Override
    public Book createBook(final Book book) {
        final BookEntity bookEntity= bookToBookEntity(book);
        final BookEntity savedBookEntity= bookRepository.save(bookEntity);
        return bookEntityToBook(savedBookEntity);
    }



    private BookEntity bookToBookEntity(Book book){
        return BookEntity.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .build();
    }

    private Book bookEntityToBook(BookEntity bookEntity){
        return Book.builder()
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .isbn(bookEntity.getIsbn())
                .build();
    }

    @Override
    public Optional<Book> findById(String isbn) {
        final Optional<BookEntity> foundbook= bookRepository.findById(isbn);
        return foundbook.map(book ->bookEntityToBook(book));
    }

    @Override
    public List<Book> listBooks() {
        final List<BookEntity> foundBooks= bookRepository.findAll();
        return foundBooks.stream().map(book -> bookEntityToBook(book)).collect(Collectors.toList());
    }
}
