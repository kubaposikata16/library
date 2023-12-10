package com.example.LibraryApp.service;

import com.example.LibraryApp.domain.Book;
import com.example.LibraryApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(int id, Book updatedBook) {
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

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }
}