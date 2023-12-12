package com.example.LibraryApp.controller;

import com.example.LibraryApp.domain.Book;
import com.example.LibraryApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable("id") int id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id: " + id));
    }

    @PostMapping("")
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable("id") int id, @RequestBody Book updatedBook) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id: " + id));
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPublicationDate(updatedBook.getPublicationDate());
        book.setDescription(updatedBook.getDescription());
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") int id) {
        bookRepository.deleteById(id);
    }
}