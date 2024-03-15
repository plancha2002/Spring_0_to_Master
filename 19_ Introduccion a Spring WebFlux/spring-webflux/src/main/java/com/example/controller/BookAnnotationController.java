package com.example.controller;

import com.example.model.Book;
import com.example.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BookAnnotationController {
    private BookService service;

    public BookAnnotationController(BookService service) {
        this.service = service;
    }

    @GetMapping("/books/{id}")
    public Mono<Book> findById(@PathVariable Long id){
        return this.service.findById(id);
    }


    @GetMapping("/books")
    public Flux<Book> findAll(){
        return this.service.findAll();
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Book> create(@RequestBody Mono<Book> book){
       return book.flatMap(b -> this.service.create(b));
    }

    @PutMapping("/books")
    public Mono<Book> update(@RequestBody Mono<Book> book){
        return book.flatMap(b -> this.service.update(b));
    }

    @DeleteMapping("/books/{id}")
    public Mono<Void> deleteById(@PathVariable Long id){
     return this.service.deleteById(id);
    }

    @DeleteMapping("/books")
    public Mono<Void> deleteAll(){
        return this.service.deleteAll();
    }


}
