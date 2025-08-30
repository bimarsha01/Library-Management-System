package org.example.project01lms.Converter;

import org.example.project01lms.Dto.LoanDto;
import org.example.project01lms.Helper.Validation;
import org.example.project01lms.Models.Book;
import org.example.project01lms.Models.Customers;
import org.example.project01lms.Models.Loan;
import org.example.project01lms.Repo.BookRepo;
import org.example.project01lms.Repo.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanConverter extends AbstractConverter<LoanDto , Loan> {


    public CustomerRepo customerRepo;
    public BookRepo bookRepo;
    public Validation validation;

    public LoanConverter(CustomerRepo customerRepo , BookRepo bookRepo , Validation validation) {
        this.customerRepo = customerRepo;
        this.bookRepo = bookRepo;
        this.validation = validation;
    }

    @Override
    public LoanDto toDto(Loan loan) {
        return null;
    }

    @Override
    public List<LoanDto> toDtoList(List<Loan> entity) {
        return List.of();
    }

    @Override
    public Loan toEntity(LoanDto loanDto) {
        Loan loan = new Loan();
        Customers customer = (customerRepo.findByLibraryId(loanDto.getLibraryId())
                .orElseThrow(() -> new RuntimeException("Customer not found")));
            validation.checkEligibilityOfCustomer(customer);
        Book book = (bookRepo.findByIsbnNumber(loanDto.getIsbnNumber()))
                .orElseThrow(() -> new RuntimeException("Book not found"));
        loan.setCustomers(customer);
        loan.setBook(book);
        return loan;
    }
}
