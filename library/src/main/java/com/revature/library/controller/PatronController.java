package com.revature.library.controller;

import com.revature.library.model.Loan;
import com.revature.library.model.Patron;
import com.revature.library.repository.PatronRepository;
import com.revature.library.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {
    private final PatronRepository patronRepository;
    private final LoanService loanService;

    public PatronController(PatronRepository patronRepository, LoanService loanService) {
        this.patronRepository = patronRepository;
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<Patron> createPatron(@RequestBody Patron patron) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patronRepository.save(patron));
    }

    @GetMapping("/{patronId}/loans")
    public ResponseEntity<List<Loan>> getLoansByPatron(@PathVariable Long patronId) {
        return ResponseEntity.ok(loanService.getLoansByPatron(patronId));
    }

    @GetMapping
    public ResponseEntity<List<Patron>> getAllPatrons(){
        return ResponseEntity.ok(patronRepository.findAll());
    }
}



