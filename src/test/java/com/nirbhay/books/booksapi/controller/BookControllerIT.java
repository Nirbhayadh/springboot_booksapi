package com.nirbhay.books.booksapi.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nirbhay.books.booksapi.TestData;
import com.nirbhay.books.booksapi.domain.Book;
import com.nirbhay.books.booksapi.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class BookControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookService bookService;

    @Test
    public void testThatBookIsCreated() throws Exception {
        final Book book= TestData.testBook();
        final ObjectMapper objectMapper= new ObjectMapper();
        final String bookJson= objectMapper.writeValueAsString(book);
        mockMvc.perform(MockMvcRequestBuilders.put("/book/"+ book.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle()))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(book.getAuthor())
        );
    }

    @Test
    public void testThatRetrieveBookReturns484WhenNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/9876543210"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testThatRetrieveBookReturnsHttp200AndBookWhenFound() throws Exception{

        final Book book= TestData.testBook();
        bookService.createBook(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/"+ book.getIsbn()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(book.getAuthor()));
    }

    @Test
    public void testThatListBookReturnsHttp200EmptyListWhenNoBooksExist() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testThatListBookReturnsHttp200AndListOfBooksWhenBooksExist() throws Exception{
        final Book book= TestData.testBook();
        bookService.createBook(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].isbn").value(book.getIsbn()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").value(book.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].author").value(book.getAuthor()));
    }

}