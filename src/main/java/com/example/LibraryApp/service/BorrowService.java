package com.example.LibraryApp.service;

import com.example.LibraryApp.domain.Borrow;

import java.util.List;

public interface BorrowService {
    List<Borrow> getAllBorrows();

    Borrow getBorrowById(int id);

    Borrow addBorrow(Borrow borrowing);

    Borrow updateBorrow(int id, Borrow updatedBorrowing);

    void deleteBorrow(int id);
}