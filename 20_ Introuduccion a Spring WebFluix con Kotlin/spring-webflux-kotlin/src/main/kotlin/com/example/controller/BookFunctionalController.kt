package com.example.controller

import com.example.service.BookHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class BookFunctionalController {

    @Bean
    fun bookRouter(handler: BookHandler)= router {
        ("/api/books" and accept(MediaType.APPLICATION_JSON)).nest {
            GET("", handler::findAll)
            GET("/{id}", handler::findById)
            POST("", handler::create)
            PUT("", handler::update)
            DELETE("/{id}", handler::findById)
            DELETE("", handler::deleteAll)

        }
    }
}