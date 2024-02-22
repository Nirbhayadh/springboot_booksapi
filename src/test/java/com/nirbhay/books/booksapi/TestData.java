package com.nirbhay.books.booksapi;

import com.nirbhay.books.booksapi.domain.Book;
import com.nirbhay.books.booksapi.domain.BookEntity;

public final class TestData {
    private TestData() {

    }
    public static Book testBook(){
        return Book.builder()
                .title("Harry Potter")
                .isbn("123456")
                .author("JK Rowling")
                .build();
    }
    public static BookEntity testBookEntity(){
        return BookEntity.builder()
                .title("Harry Potter")
                .isbn("123456")
                .author("JK Rowling")
                .build();
    }
}
