package org.example.project01lms.Helper;

import org.example.project01lms.Models.Customers;
import org.example.project01lms.Repo.LoanRepo;
import org.springframework.stereotype.Service;

@Service
public class Validation {
    private final LoanRepo loanRepo;

    public Validation(LoanRepo loanRepo) {
        this.loanRepo = loanRepo;
    }

    public String checkEligibilityOfCustomer(Customers customer){

    }
}
