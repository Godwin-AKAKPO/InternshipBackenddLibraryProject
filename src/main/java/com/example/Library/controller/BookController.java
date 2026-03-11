package com.example.Library.controller;


import com.example.Library.dto.BookDTO;
import com.example.Library.model.Book;
import com.example.Library.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks () {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book createBook(@RequestBody BookDTO bookDTO){
        return bookService.saveBookFromDTO(bookDTO);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book){
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id){

        boolean deleted = bookService.deleteBook(id);

        if(deleted){
            return "Livre supprimé";
        }else{
            return "Livre introuvable";
        }

    }
}
