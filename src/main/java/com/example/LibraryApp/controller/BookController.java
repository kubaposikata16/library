package com.example.LibraryApp.controller;

import com.example.LibraryApp.domain.Book;
import com.example.LibraryApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookRepository bookRepository;
    ////////////////COSTAM
    @GetMapping //pobiera wszystkie
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
    @GetMapping("/{id}") //pobiera po id
    public Book getBookById(@PathVariable("id") int id){
        return bookRepository.findById(id).orElse(null);
    }
    @PostMapping("") //tworzy nowy
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }
    @PutMapping("/{id}") //edytuje po id
    public Book updateBook(@PathVariable("id") int id, @RequestBody Book updatedBook) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setPublicationDate(updatedBook.getPublicationDate());
            book.setDescription(updatedBook.getDescription());
            return bookRepository.save(book);
        } else {
            return null;
        }
    }
    @DeleteMapping("/{id}") //usuwa po id
    public void deleteBook(@PathVariable("id") int id){
        bookRepository.deleteById(id);
    }
}