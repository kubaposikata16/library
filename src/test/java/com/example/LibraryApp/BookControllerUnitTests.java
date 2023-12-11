package com.example.LibraryApp;

import com.example.LibraryApp.controller.BookController;
import com.example.LibraryApp.domain.Book;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.service.BookService;
import com.example.LibraryApp.service.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest
public class BookControllerUnitTests {

    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookService bookService = new BookServiceImpl();
    @InjectMocks
    private BookController bookController;

    @Test
    public void testGetAllBooks() {
        Book book1 = new Book(1, "Title1", "Author1", 2020, "Description1", null);
        Book book2 = new Book(2, "Title2", "Author2", 2021, "Description2", null);
        List<Book> books = Arrays.asList(book1, book2);
        Mockito.when(bookRepository.findAll()).thenReturn(books);
        List<Book> result = bookController.getAllBooks();
        assertEquals(2, result.size());
    }

    @Test
    public void testAddBook() {
        Book newBook = new Book(3, "Title3", "Author3", 2022, "Description3", null);
        Mockito.when(bookRepository.save(any())).thenReturn(newBook);
        Book result = bookController.addBook(newBook);
        assertEquals(newBook, result);
    }
}