package com.example.LibraryApp.service;

import com.example.LibraryApp.domain.Book;
import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(int id);
    Book addBook(Book book);
    Book updateBook(int id, Book updatedBook);
    void deleteBook(int id);
}