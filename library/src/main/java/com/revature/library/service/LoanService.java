package com.revature.library.service;

import com.revature.library.exception.BookNotAvailableException;
import com.revature.library.exception.BookNotFoundException;
import com.revature.library.exception.PatronNotFoundException;
import com.revature.library.model.Book;
import com.revature.library.model.Loan;
import com.revature.library.model.Patron;
import com.revature.library.repository.BookRepository;
import com.revature.library.repository.LoanRepository;
import com.revature.library.repository.PatronRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, PatronRepository patronRepository){
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    public Loan createLoan(Long bookId, Long patronId){
        Book book = bookRepository.findById(bookId).orElseThrow((() -> new BookNotFoundException("Book not found with "+bookId)));

        if(!book.isAvailable()){
            throw new BookNotAvailableException("Book with id "+bookId+" is not available");
        }

        Patron patron = patronRepository.findById(patronId).orElseThrow(() -> new PatronNotFoundException("Patron not found with"+patronId));

        Loan loan = new Loan(book, patron);
        book.setAvailable(false);
        return loanRepository.save(loan);
    }

    public Loan returnLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found with"+ loanId));

        loan.setReturnDate(LocalDate.now());
        loan.getBook().setAvailable(true);
        return loanRepository.save(loan);
    }

    public List<Loan> getActiveLoans() {
        return loanRepository.findByReturnDateIsNull();
    }

    public List<Loan> getLoansByPatron(Long patronId){
        return loanRepository.findByPatronId(patronId);

    }
}
