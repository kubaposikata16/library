package com.example.LibraryApp.repository;

import com.example.LibraryApp.domain.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//repozytorium wypozyczen
@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Integer> {

}