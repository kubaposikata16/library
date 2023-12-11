package com.example.LibraryApp.service;

import com.example.LibraryApp.domain.Reader;
import com.example.LibraryApp.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    @Override
    public Reader getReaderById(int id) {
        return readerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reader not found with id: " + id));
    }

    @Override
    public Reader addReader(Reader reader) {
        return readerRepository.save(reader);
    }

    @Override
    public Reader updateReader(int id, Reader updatedReader) {
        Reader reader = readerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reader not found with id: " + id));
        reader.setName(updatedReader.getName());
        reader.setSurname(updatedReader.getSurname());
        reader.setReaderSince(updatedReader.getReaderSince());
        return readerRepository.save(reader);

    }

    @Override
    public void deleteReader(int id) {
        readerRepository.deleteById(id);
    }
}