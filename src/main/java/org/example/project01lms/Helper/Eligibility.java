package org.example.project01lms.Helper;

import org.example.project01lms.Models.Book;
import org.example.project01lms.Models.Customers;
import org.example.project01lms.Repo.LoanRepo;
import org.springframework.stereotype.Component;

@Component
public class Eligibility {
    private final LoanRepo loanRepo;

    public Eligibility(LoanRepo loanRepo) {
        this.loanRepo = loanRepo;
    }

    public void checkEligibilityOfCustomer(Customers customers , Book book){
        int activeLoan = loanRepo.countLoanByCustomers(customers);
        if(activeLoan >= 5){
            throw new RuntimeException("Customer has reached max loan limit of 5 books");
        }
        int check = loanRepo.countByCustomers_LibraryIdAndBook_IsbnNumber(customers.getLibraryId(), book.getIsbnNumber() );
        if(check > 1){
            throw new RuntimeException("Customer cannot have more than one same book");
        }
    }

    public void checkAvailabilityOfBook(Book book){
      if(book.getAvailableCopies() == 0){
          throw new RuntimeException("Book not available");
        }
      else{
          Long total = book.getAvailableCopies() - 1;
          book.setAvailableCopies(total);
      }

    }
}
