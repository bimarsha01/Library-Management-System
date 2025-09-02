package org.example.project01lms.Helper;

import org.example.project01lms.Models.Loan;
import org.example.project01lms.Repo.CustomerRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class Validation {

    private final CustomerRepo customerRepo;

    public Validation(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


    public void validateOnReturn(Loan loan) {
        if(loan.getDueDate().isBefore(LocalDate.now())){
            loan.setStatus(false);
            loan.setFine(loan.getFine() + 100.00);
        }
        loan.setReturnDate(LocalDate.now());
    }

    public String generateUniqueLibraryId() {
        String lid;
        do {
            lid = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
        } while (customerRepo.existsByLibraryId(lid));
        return lid;
    }
}
