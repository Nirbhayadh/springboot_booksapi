package com.nirbhay.books.booksapi.service.impl;

import com.nirbhay.books.booksapi.TestData;
import com.nirbhay.books.booksapi.domain.Book;
import com.nirbhay.books.booksapi.domain.BookEntity;
import com.nirbhay.books.booksapi.repository.BookRepository;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.nirbhay.books.booksapi.TestData.testBookEntity;
import static javax.management.Query.eq;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookServiceImplTest;


    @Test
    public void testThatBookIsSaved(){
        final Book book= TestData.testBook();

        final BookEntity bookEntity= testBookEntity();

        when(bookRepository.save(bookEntity)).thenReturn(bookEntity);

        final Book result= bookServiceImplTest.createBook(book);
        assertEquals(book,result);
    }

    @Test
    public void testThatFindByIdReturnsEmptyWhenNoBook(){
        final String isbn= "9876543210";

        when(bookRepository.findById(isbn)).thenReturn(Optional.empty());

        final Optional<Book>  result= bookServiceImplTest.findById(isbn);
        assertEquals(Optional.empty(),result);
    }

    @Test
    public void testThatFindByIdReturnsBookWhenExists(){
        final Book book= TestData.testBook();
        final BookEntity bookEntity= testBookEntity();



        when(bookRepository.findById(book.getIsbn())).thenReturn(Optional.of(bookEntity));

        final Optional<Book>  result= bookServiceImplTest.findById(book.getIsbn());
        assertEquals(Optional.of(book),result);
    }

    @Test
    public void testListBooksReturnsEmptyListWhenNoBooksExist(){
        when(bookRepository.findAll()).thenReturn(new ArrayList<BookEntity>());
        final List<Book> result = bookServiceImplTest.listBooks();
        assertEquals(0,result.size());
    }

    @Test
    public void testListBooksReturnsBooksWhenExist(){
        final BookEntity bookEntity= TestData.testBookEntity();
        when(bookRepository.findAll()).thenReturn(List.of(bookEntity));
        final List<Book> result = bookServiceImplTest.listBooks();
        assertEquals(1,result.size());
    }

}