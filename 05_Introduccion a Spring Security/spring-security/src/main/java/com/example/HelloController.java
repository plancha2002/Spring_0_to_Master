package com.example;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        SecurityContextHolder.getContext().getAuthentication();
        return "Hola mundo";
    }
}
