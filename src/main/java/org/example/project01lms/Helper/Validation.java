package org.example.project01lms.Helper;

import org.example.project01lms.Models.Customers;
import org.example.project01lms.Models.Loan;
import org.example.project01lms.Repo.LoanRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class Validation {

    public void validateOnReturn(Loan loan) {
        if(loan.getDueDate().isBefore(LocalDate.now())){
            loan.setStatus(false);
            loan.setFine(100.00);
        }
        loan.setReturnDate(LocalDate.now());
    }
}
