package org.example.project01lms.Helper;

import jakarta.transaction.Transactional;
import org.example.project01lms.ExceptionHandling.NotAvailableException;
import org.example.project01lms.Models.Book;
import org.example.project01lms.Models.Loan;
import org.example.project01lms.Repo.BookRepo;
import org.example.project01lms.Repo.LoanRepo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Update {

    private final LoanRepo loanRepo;
    private final BookRepo bookRepo;

    public Update(LoanRepo loanRepo, BookRepo bookRepo) {
        this.loanRepo = loanRepo;
        this.bookRepo = bookRepo;
    }

    @Transactional
    public void updateOnReturn(Loan loan) {
        Book book = bookRepo.findByIsbnNumber(loan.getBook().getIsbnNumber())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setAvailableCopies(book.getAvailableCopies() + 1);

    }
}
