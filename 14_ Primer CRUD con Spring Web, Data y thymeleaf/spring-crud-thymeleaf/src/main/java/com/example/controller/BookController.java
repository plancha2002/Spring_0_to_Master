package com.example.controller;

import com.example.model.Book;
import com.example.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    private BookRepository bookRepository;


    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String index(){
        return "redirect:/books";
    }


    @GetMapping("/books")
    public String findAll(Model model){

        model.addAttribute("books", bookRepository.findAll());
        //hace referencia a book-list.html en templates
        return "book-list";
    }

    @GetMapping("/books/view/{id}")
    public String findById(@PathVariable Long id, Model model){
        model.addAttribute("books", bookRepository.findById(id).get());
        //hace referencia a book-list.html en templates
        return "book-list";
    }

    @GetMapping("/books/form")
    public String getForm(Model model){
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @GetMapping("/books/edit/{id}")
    public String getFormWithBook(Model model, @PathVariable Long id){
        if (bookRepository.existsById(id)){
        bookRepository.findById(id).ifPresent( b -> model.addAttribute("book", b));
        return "book-form";
        }else{
            return "redirect:/books/form";
        }

    }

    @PostMapping("/books")
    public String create(@ModelAttribute Book book){
        if (book.getId() != null){
            //update
            bookRepository.findById(book.getId()).ifPresent(b -> {
                b.setAuthor(book.getAuthor());
                b.setPrice(book.getPrice());
                b.setTitle(book.getTitle());
                bookRepository.save(b);
                    }
            );
        }else {
            //creacion
            bookRepository.save(book);

        }
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteById(@PathVariable Long id, Model model){
        if (bookRepository.existsById(id)){
            bookRepository.deleteById(id);
        }

        return "redirect:/books";
    }
}
