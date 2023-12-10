package com.example.LibraryApp.repository;

import com.example.LibraryApp.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//repozytorium czytelnikow
@Repository
public interface ReaderRepository extends JpaRepository<Reader, Integer> {

}