package com.example.Library.controller;

import com.example.Library.dto.ReturnRequest;
import com.example.Library.model.Loan;
import com.example.Library.service.LoanService;
import org.springframework.web.bind.annotation.*;
import com.example.Library.dto.BorrowRequest;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private LoanService loanService;

    public LoanController(LoanService loanService){
        this.loanService = loanService;
    }

    @GetMapping
    public List<Loan> getAllLoans(){
        return loanService.getAllLoans();
    }


    @PostMapping("/borrow")
    public Loan borrowBook(@RequestBody BorrowRequest request){
        return loanService.borrowBook(request.getBookId(), request.getUserId());
    }

    @PostMapping("/return")
    public Loan returnBook(@RequestBody ReturnRequest request){
        return loanService.returnBook(request.getBookId());
    }

    @GetMapping("/user/{id}")
    public List<Loan> getUserLoans(@PathVariable Long id){
        return loanService.getUserLoanHistory(id);
    }

}