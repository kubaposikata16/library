package com.example.LibraryApp.controller;

import com.example.LibraryApp.domain.Book;
import com.example.LibraryApp.domain.Borrow;
import com.example.LibraryApp.domain.Reader;
import com.example.LibraryApp.repository.BorrowRepository;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/borrows")
public class BorrowController {
    @Autowired
    BorrowRepository borrowRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ReaderRepository readerRepository;

    @GetMapping
    public List<Borrow> getAllReaders() {
        return borrowRepository.findAll();
    }

    @GetMapping("/{id}")
    public Borrow getReaderById(@PathVariable("id") int id) {
        return borrowRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Borrow not found with id: " + id));
    }

    @PostMapping("")
    public Borrow addReader(@RequestBody Borrow borrowing) {
        Borrow newBorrow = new Borrow();
        //Pobierz Book i Reader na podstawie przekazanych ID
        Book book = bookRepository.findById(borrowing.getBook().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        Reader reader = readerRepository.findById(borrowing.getReader().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reader not found"));
        newBorrow.setBook(book);
        newBorrow.setReader(reader);
        return borrowRepository.save(newBorrow);
    }

    @PutMapping("/{id}")
    public Borrow updateReader(@PathVariable("id") int id, @RequestBody Borrow updatedBorrowing) {
        Borrow borrowing = borrowRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Borrow not found with id: " + id));
        Book book = bookRepository.findById(updatedBorrowing.getBook().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found with id: " + id));
        Reader reader = readerRepository.findById(updatedBorrowing.getReader().getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reader not found with id: " + id));
        borrowing.setBook(book);
        borrowing.setReader(reader);
        return borrowRepository.save(borrowing);

    }

    @DeleteMapping("/{id}")
    public void deleteBorrow(@PathVariable("id") int id) {
        borrowRepository.deleteById(id);
    }
}