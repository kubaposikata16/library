package com.example.LibraryApp.repository;

import com.example.LibraryApp.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//repozytorium ksiazek
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}