package com.example.service;

import com.example.model.Book;
import com.example.repository.BookRespository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith()
class BookServiceImplTest {

    //system under test: SUT
//    @InjectMocks
    BookService service;
    //dependencias
    @Mock
    BookRespository repository;


    @BeforeEach
    void setUp(){
        //esto sustituye a ineject y extendwWith
        MockitoAnnotations.openMocks(this);
        service = new BookServiceImpl(repository);

        }


    @Test
    void findAll() {
    //given

        //MOck
        when(repository.findAll()).thenReturn(List.of());

        //comporatamiento a testear
        List<Book> books = service.findAll();

        assertNotNull(books);
        assertEquals(0, books.size());
        //verificacion mockito
        verify(repository, times(1)).findAll();
        //temporales:
        //atleast, times, never
    }

    @Test
    void findByIdFound() {
        // configurar mock
        when(repository.findById(any())).thenReturn(Optional.of(new Book( "book1", "description")));

        // ejecutar el comportamiento a testear
        Optional<Book> bookOpt = service.findById(1L);

        // comprobaciones de JUnit
        assertNotNull(bookOpt);
        assertTrue(bookOpt.isPresent());
        assertEquals("book1", bookOpt.get().getTitle());
        // verificaciones Mockito
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void findByIdNotFound() {
    }

    @Test
    void findByIdWrong() {
    }
}