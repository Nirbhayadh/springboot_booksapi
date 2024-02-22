package com.nirbhay.books.booksapi.repository;

import com.nirbhay.books.booksapi.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<BookEntity, String> {
}
