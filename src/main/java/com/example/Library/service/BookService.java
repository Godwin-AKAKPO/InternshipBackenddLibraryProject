package com.example.Library.service;

import com.example.Library.dto.BookDTO;
import com.example.Library.model.Book;
import com.example.Library.model.Category;
import com.example.Library.repository.BookRepository;
import org.springframework.stereotype.Service;
import com.example.Library.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    public BookService(BookRepository bookRepository,CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){

        Optional<Book> optionalBook = bookRepository.findById(id);

        if(optionalBook.isEmpty()){
            return null;
        }else{
            return optionalBook.get();
        }

    }

    public Book saveBookFromDTO(BookDTO bookDTO) {
        Category category = categoryRepository.findById(bookDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setAvailable(bookDTO.isAvailable());
        book.setCategory(category);

        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails){
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isEmpty()){
            return null;
        }else{
            Book book = optionalBook.get();

            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setAvailable(bookDetails.isAvailable());
            book.setCategory(bookDetails.getCategory());

            return bookRepository.save(book);
        }
    }
    public boolean deleteBook(Long id){

        Optional<Book> optionalBook = bookRepository.findById(id);

        if(optionalBook.isEmpty()){
            return false;
        }else{
            bookRepository.deleteById(id);
            return true;
        }

    }
}
