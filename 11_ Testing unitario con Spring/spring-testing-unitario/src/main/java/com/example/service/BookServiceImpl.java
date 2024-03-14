package com.example.service;

import com.example.model.Book;
import com.example.repository.BookRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRespository repository;


    public BookServiceImpl(BookRespository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.repository.findById(id);
    }
}
