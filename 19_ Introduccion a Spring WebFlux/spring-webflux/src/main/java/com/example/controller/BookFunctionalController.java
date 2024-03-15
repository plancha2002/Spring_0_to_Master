package com.example.controller;

import com.example.model.Book;
import com.example.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.net.URI;

@Configuration
public class BookFunctionalController {
    private BookService service;

    public BookFunctionalController(BookService service) {
        this.service = service;
    }
    //equivalente del findAll al controllador por etiquetas
    @Bean
    public RouterFunction<ServerResponse> findAllBooks(){
        return RouterFunctions.route().GET(
                "/functional/books",
                request -> ServerResponse.ok().body(service.findAll(), Book.class)
        ).build();
    }

    @Bean
    public RouterFunction<ServerResponse> findById(){
        return RouterFunctions.route().GET(
                "/functional/books/{id}",
                request -> ServerResponse.ok().body(
                        service.findById(
                                Long.valueOf( request.pathVariable ("id") ) ),
                                Book.class
                )
        ).build();
    }

    @Bean
    public RouterFunction<ServerResponse> createCook(){
        return RouterFunctions.route().POST(
                "/functional/books/",
                request -> request.bodyToMono(Book.class)
                        .flatMap(b -> this.service.create(b))
                        .flatMap(b ->
                                ServerResponse.created(URI.create("/functional/books/" + b.getId()))
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(BodyInserters.fromValue(b))
                            )
        ).build();
    }
}
