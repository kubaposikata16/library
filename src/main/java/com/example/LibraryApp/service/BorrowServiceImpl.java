package com.example.LibraryApp.service;

import com.example.LibraryApp.domain.Borrow;
import com.example.LibraryApp.repository.BorrowRepository;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    public List<Borrow> getAllBorrows() {
        return borrowRepository.findAll();
    }

    @Override
    public Borrow getBorrowById(int id) {
        return borrowRepository.findById(id).orElse(null);
    }

    @Override
    public Borrow addBorrow(Borrow borrowing) {
        Borrow newBorrow = new Borrow();
        if (borrowing.getBook() != null && borrowing.getReader() != null) {
            if (bookRepository.existsById(borrowing.getBook().getId()) &&
                    readerRepository.existsById(borrowing.getReader().getId())) {
                newBorrow.setBook(borrowing.getBook());
                newBorrow.setReader(borrowing.getReader());
                return borrowRepository.save(newBorrow);
            }
        }
        return null;
    }

    @Override
    public Borrow updateBorrow(int id, Borrow updatedBorrowing) {
        Borrow borrowing = borrowRepository.findById(id).orElse(null);
        if (borrowing != null) {
            if (updatedBorrowing.getBook() != null && updatedBorrowing.getReader() != null) {
                if (bookRepository.existsById(updatedBorrowing.getBook().getId()) &&
                        readerRepository.existsById(updatedBorrowing.getReader().getId())) {
                    borrowing.setBook(updatedBorrowing.getBook());
                    borrowing.setReader(updatedBorrowing.getReader());
                    return borrowRepository.save(borrowing);
                }
            }
        }
        return null;
    }

    @Override
    public void deleteBorrow(int id) {
        borrowRepository.deleteById(id);
    }
}