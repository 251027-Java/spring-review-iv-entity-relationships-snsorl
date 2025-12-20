package com.revature.library.controller;

import com.revature.library.exception.BookNotFoundException;
import com.revature.library.model.Book;
import com.revature.library.model.Loan;
import com.revature.library.repository.PatronRepository;
import com.revature.library.service.BookService;
import com.revature.library.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestParam Long bookId, @RequestParam Long patronId) {
        Loan loan = loanService.createLoan(bookId, patronId);
        return ResponseEntity.status(HttpStatus.CREATED).body(loan);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Loan> returnLoan(@PathVariable Long id) {
        Loan returned = loanService.returnLoan(id);
        return ResponseEntity.ok(returned);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Loan>> getActiveLoans(){
        return ResponseEntity.ok(loanService.getActiveLoans());
    }


}