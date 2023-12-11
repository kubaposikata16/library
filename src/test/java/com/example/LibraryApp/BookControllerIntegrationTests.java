package com.example.LibraryApp;

import com.example.LibraryApp.domain.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllBooks() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        List<Book> books = objectMapper.readValue(result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Book.class));
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void testAddBook() throws Exception {
        Book newBook = new Book(4, "Title4", "Author4", 2023, "Description4", null);
        String jsonRequest = objectMapper.writeValueAsString(newBook);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }
}