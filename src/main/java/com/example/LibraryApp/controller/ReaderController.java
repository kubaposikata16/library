package com.example.LibraryApp.controller;

import com.example.LibraryApp.domain.Reader;
import com.example.LibraryApp.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/readers")
public class ReaderController {
    @Autowired
    ReaderRepository readerRepository;

    @GetMapping
    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reader getReaderById(@PathVariable("id") int id) {
        return readerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reader not found with id: " + id));
    }

    @PostMapping("")
    public Reader addReader(@RequestBody Reader reader) {
        return readerRepository.save(reader);
    }

    @PutMapping("/{id}")
    public Reader updateReader(@PathVariable("id") int id, @RequestBody Reader updatedReader) {
        Reader client = readerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reader not found with id: " + id));
        client.setName(updatedReader.getName());
        client.setSurname(updatedReader.getSurname());
        client.setReaderSince(updatedReader.getReaderSince());
        return readerRepository.save(client);
    }

    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable("id") int id) {
        readerRepository.deleteById(id);
    }
}