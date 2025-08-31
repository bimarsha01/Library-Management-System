package org.example.project01lms.Repo;

import org.example.project01lms.Models.Book;
import org.example.project01lms.Models.Customers;
import org.example.project01lms.Models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LoanRepo extends JpaRepository<Loan, Long> {

    int countLoanByCustomers(Customers customers);

}
