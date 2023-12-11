package com.example.LibraryApp;

import com.example.LibraryApp.controller.ReaderController;
import com.example.LibraryApp.domain.Reader;
import com.example.LibraryApp.repository.ReaderRepository;
import com.example.LibraryApp.service.ReaderService;
import com.example.LibraryApp.service.ReaderServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ReaderControllerUnitTests {

    @Mock
    private ReaderRepository readerRepository;

    @InjectMocks
    private ReaderService readerService = new ReaderServiceImpl();

    @InjectMocks
    private ReaderController readerController;

    @Test
    public void testAddReader() {
        Reader newReader = new Reader(1, "John", "Doe", 2020, null);
        Mockito.when(readerRepository.save(any())).thenReturn(newReader);
        Reader result = readerController.addReader(newReader);
        assertEquals(newReader, result);
    }
}