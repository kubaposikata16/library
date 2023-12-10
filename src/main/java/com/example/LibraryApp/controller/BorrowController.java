package com.example.LibraryApp.controller;

import com.example.LibraryApp.domain.Book;
import com.example.LibraryApp.domain.Borrow;
import com.example.LibraryApp.domain.Reader;
import com.example.LibraryApp.repository.BorrowRepository;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping //pobiera wszystkie
    public List<Borrow> getAllReaders() {
        return borrowRepository.findAll();
    }

    @GetMapping("/{id}") //pobiera po id
    public Borrow getReaderById(@PathVariable("id") int id) {
        return borrowRepository.findById(id).orElse(null);
    }

    @PostMapping("") //tworzy nowy
    public Borrow addReader(@RequestBody Borrow borrowing) {
        //return borrowRepository.save(borrowing);
        Borrow newBorrow = new Borrow();

        // Pobierz Book i Reader na podstawie przekazanych ID
        Book book = bookRepository.findById(borrowing.getBook().getId()).orElse(null);
        Reader reader = readerRepository.findById(borrowing.getReader().getId()).orElse(null);

        // Sprawdź czy istnieją Book i Reader o podanych ID
        if (book != null && reader != null) {
            newBorrow.setBook(book);
            newBorrow.setReader(reader);
            return borrowRepository.save(newBorrow);
        } else {
            // Obsługa przypadku, gdy nie znaleziono Book lub Reader
            return null;
        }
    }

    @PutMapping("/{id}")
    public Borrow updateReader(@PathVariable("id") int id, @RequestBody Borrow updatedBorrowing) {
        Borrow borrowing = borrowRepository.findById(id).orElse(null);

        if (borrowing != null) {
            Book book = bookRepository.findById(updatedBorrowing.getBook().getId()).orElse(null);
            Reader reader = readerRepository.findById(updatedBorrowing.getReader().getId()).orElse(null);

            if (book != null && reader != null) {
                borrowing.setBook(book);
                borrowing.setReader(reader);
                return borrowRepository.save(borrowing);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}") //usuwa po id
    public void deleteBorrow(@PathVariable("id") int id) {
        borrowRepository.deleteById(id);
    }
}