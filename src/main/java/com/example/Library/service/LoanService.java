package com.example.Library.service;

import com.example.Library.model.Book;
import com.example.Library.model.Loan;
import com.example.Library.model.User;
import com.example.Library.repository.BookRepository;
import com.example.Library.repository.LoanRepository;
import com.example.Library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository){
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<Loan> getAllLoans()  {
        return loanRepository.findAll();
    }

    public Loan borrowBook(Long bookId, Long userId) {

        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isEmpty()) {
            throw new RuntimeException("Book not found");
        }

        Book book = optionalBook.get();

        if (book.isAvailable() == false) {
            throw new RuntimeException("Book already borrowed");
        }
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            return null;
        }

        User user = optionalUser.get();

        book.setAvailable(false);
        bookRepository.save(book);

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setLoanDate(LocalDate.now());

        return loanRepository.save(loan);
    }

    public Loan returnBook(Long bookId) {
        Optional<Loan> optionalLoan = loanRepository.findByBookIdAndReturnDateIsNull(bookId);

        if (optionalLoan.isEmpty()){
            return  null;
        }

        Loan loan = optionalLoan.get();

        loan.setReturnDate(LocalDate.now());

        Book book = loan.getBook();
        book.setAvailable(true);

        bookRepository.save(book);
        return loanRepository.save(loan);

    }

    public List<Loan> getUserLoanHistory(Long userId){

        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            return null;
        }else{
            return loanRepository.findByUserId(userId);
        }

    }
}
