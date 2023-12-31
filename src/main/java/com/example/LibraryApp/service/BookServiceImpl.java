package com.example.LibraryApp.service;

import com.example.LibraryApp.domain.Book;
import com.example.LibraryApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(int id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id: " + id));
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(int id, Book updatedBook) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id: " + id));
        book.setTitle(updatedBook.getTitle());
            /*//do pokazania squashowania
            System.out.println("print 1");
            System.out.println("print 2");
            System.out.println("print 3");
            */
        book.setAuthor(updatedBook.getAuthor());
        book.setPublicationDate(updatedBook.getPublicationDate());
        book.setDescription(updatedBook.getDescription());
        return bookRepository.save(book);

    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

}