package com.nirbhay.books.booksapi.service.impl;

import com.nirbhay.books.booksapi.TestData;
import com.nirbhay.books.booksapi.domain.Book;
import com.nirbhay.books.booksapi.domain.BookEntity;
import com.nirbhay.books.booksapi.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

        final BookEntity bookEntity= TestData.testBookEntity();

        when(bookRepository.save(bookEntity)).thenReturn(bookEntity);

        final Book result= bookServiceImplTest.createBook(book);
        assertEquals(book,result);
    }

}