package com.example.LibraryApp.controller;

import com.example.LibraryApp.domain.Reader;
import com.example.LibraryApp.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/readers")
public class ReaderController {
    @Autowired
    ReaderRepository readerRepository;

    @GetMapping //pobiera wszystkie
    public List<Reader> getAllReaders(){
        return readerRepository.findAll();
    }
    @GetMapping("/{id}") //pobiera po id
    public Reader getReaderById(@PathVariable("id") int id){
        return readerRepository.findById(id).orElse(null);
    }
    @PostMapping("") //tworzy nowy
    public Reader addReader(@RequestBody Reader reader) {
        return readerRepository.save(reader);
    }
    @PutMapping("/{id}") //edytuje po id
    public Reader updateReader(@PathVariable("id") int id, @RequestBody Reader updatedReader) {
        Reader client = readerRepository.findById(id).orElse(null);
        if (client != null) {
            client.setName(updatedReader.getName());
            client.setSurname(updatedReader.getSurname());
            client.setReaderSince(updatedReader.getReaderSince());
            return readerRepository.save(client);
        } else {
            return null;
        }
    }
    @DeleteMapping("/{id}") //usuwa po id
    public void deleteReader(@PathVariable("id") int id){
        readerRepository.deleteById(id);
    }
}