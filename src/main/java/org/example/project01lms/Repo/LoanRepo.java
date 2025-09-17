package org.example.project01lms.Repo;

import jakarta.transaction.Transactional;
import org.example.project01lms.Models.Customers;
import org.example.project01lms.Models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepo extends JpaRepository<Loan, Long> {

    int countLoanByCustomers(Customers customers);

    Optional<Loan> findByCustomers_libraryIdAndBook_isbnNumber(String libraryId, String isbnNumber);

    @Modifying
    @Transactional
//    void removeLoanByCustomers_LibraryId(String libraryId);

    // Use proper return type for finding by ISBN
    Optional<Loan> findByBook_isbnNumber(String isbnNumber);

    int countByCustomers_LibraryIdAndBook_IsbnNumber(String customers_libraryId, String book_isbnNumber);

    Optional<Object> findByCustomers_libraryId(String libraryId);
}
