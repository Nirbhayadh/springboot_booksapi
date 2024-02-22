package com.nirbhay.books.booksapi.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nirbhay.books.booksapi.TestData;
import com.nirbhay.books.booksapi.domain.Book;
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

    @Test
    public void testThatBookIsCreated() throws Exception {
        final Book book= TestData.testBook();
        final ObjectMapper objectMapper= new ObjectMapper();
        final String bookJson= objectMapper.writeValueAsString(book);
        mockMvc.perform(MockMvcRequestBu    ilders.put("/book/"+ book.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson))
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn").value(book.getIsbn()))
                        .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(book.getTitle()))
                                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(book.getAuthor())
        );
    }

}