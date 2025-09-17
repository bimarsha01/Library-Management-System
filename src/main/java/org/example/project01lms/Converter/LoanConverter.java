package org.example.project01lms.Converter;

import org.example.project01lms.Dto.LoanDto;
import org.example.project01lms.ExceptionHandling.HandleTimeExceedException;
import org.example.project01lms.ExceptionHandling.NotAvailableException;
import org.example.project01lms.Helper.Eligibility;
import org.example.project01lms.Helper.Validation;
import org.example.project01lms.Models.Book;
import org.example.project01lms.Models.Customers;
import org.example.project01lms.Models.Loan;
import org.example.project01lms.Repo.BookRepo;
import org.example.project01lms.Repo.CustomerRepo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanConverter extends AbstractConverter<LoanDto , Loan> {


    public CustomerRepo customerRepo;
    public BookRepo bookRepo;
    public Validation validation;
    public Eligibility eligibility;

    public LoanConverter(CustomerRepo customerRepo , BookRepo bookRepo , Validation validation , Eligibility eligibility) {
        this.customerRepo = customerRepo;
        this.bookRepo = bookRepo;
        this.validation = validation;
        this.eligibility = eligibility;
    }

    @Override
    public LoanDto toDto(Loan loan) {
       LoanDto loanDto = new LoanDto();
       loanDto.setId(loan.getId());
       loanDto.setLibraryId(loan.getCustomers().getLibraryId());
       loanDto.setIsbnNumber(loan.getBook().getIsbnNumber());
       loanDto.setBookName(loan.getBook().getBookName());
       loanDto.setAuthorName(loan.getBook().getAuthorName());
       loanDto.setBorrowDate(loan.getBorrowDate());
       loanDto.setDueDate(loan.getDueDate());
       loanDto.setReturnDate(loan.getReturnDate());
       loanDto.setStatus(loan.getStatus());
       loanDto.setFine(loan.getFine());
       return loanDto;
    }

    @Override
    public List<LoanDto> toDtoList(List<Loan> entity) {
        return List.of();
    }

    @Override
    public Loan toEntity(LoanDto loanDto) {
        Loan loan = new Loan();

        Customers customers = (customerRepo.findByLibraryId(loanDto.getLibraryId())
                .orElseThrow(() -> new RuntimeException("Something went wrong while fetching the library id")));

        Book book = (bookRepo.findByIsbnNumber(loanDto.getIsbnNumber()))
                .orElseThrow(() -> new NotAvailableException("NOT_AVAILABLE" , "Book with isbnNumber " + loanDto.getIsbnNumber() + " is not available at the moment"));

            eligibility.checkEligibilityOfCustomer(customers , book);

            eligibility.checkAvailabilityOfBook(book);
        loan.setCustomers(customers);
        loan.setBook(book);
        loan.setDueDate(loanDto.getDueDate());

        return loan;
    }
}
