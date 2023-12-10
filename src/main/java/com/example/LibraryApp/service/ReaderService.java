package com.example.LibraryApp.service;

import com.example.LibraryApp.domain.Reader;
import java.util.List;

public interface ReaderService {
    List<Reader> getAllReaders();
    Reader getReaderById(int id);
    Reader addReader(Reader reader);
    Reader updateReader(int id, Reader updatedReader);
    void deleteReader(int id);
}