package org.example.project01lms.Services.LoanService;

import org.example.project01lms.Converter.LoanConverter;
import org.example.project01lms.Dto.LoanDto;
import org.example.project01lms.Helper.Eligibility;
import org.example.project01lms.Helper.Validation;
import org.example.project01lms.Models.Book;
import org.example.project01lms.Models.Customers;
import org.example.project01lms.Models.Loan;
import org.example.project01lms.Repo.BookRepo;
import org.example.project01lms.Repo.CustomerRepo;
import org.example.project01lms.Repo.LoanRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImp implements LoanService {

    private final CustomerRepo customerRepo;
    private final BookRepo bookRepo;
    private final LoanRepo loanRepo;
    private final LoanConverter loanConverter;
    private final Validation validation;


    public LoanServiceImp(CustomerRepo customerRepo, BookRepo bookRepo , LoanConverter loanConverter, LoanRepo loanRepo , Validation validation) {
        this.customerRepo = customerRepo;
        this.bookRepo = bookRepo;
        this.loanConverter = loanConverter;
        this.loanRepo = loanRepo;
        this.validation = validation;

    }

    @Override
    public Object createLoan(LoanDto loanDto) {
        Loan loan = loanConverter.toEntity(loanDto);
        loan = loanRepo.save(loan);
        loanDto = loanConverter.toDto(loan);
        return loanDto;
    }

    @Override
    public LoanDto returnBook(LoanDto loanDto) {
Loan loan = new Loan();
        Customers customers = (customerRepo.findByLibraryId(loanDto.getLibraryId())
                .orElseThrow(() -> new RuntimeException("Customer not found")));

        Book book = (bookRepo.findByIsbnNumber(loanDto.getIsbnNumber()))
                .orElseThrow(() -> new RuntimeException("Book not found"));


        loan.setCustomers(customers);
        loan.setBook(book);

        loan.setDueDate(loanDto.getDueDate());
        loan.setBorrowDate(loanDto.getBorrowDate());

        loan = loanRepo.save(loan);
        validation.validateOnReturn(loan);

        loanDto = loanConverter.toDto(loan);
    return null;
    }

    @Override
    public LoanDto save(LoanDto loanDto) {
        return null;
    }

    @Override
    public LoanDto update(LoanDto loanDto) {
        return null;
    }

    @Override
    public List<LoanDto> findall() {
        return List.of();
    }

    @Override
    public LoanDto findById(LoanDto loanDto) {
        return null;
    }
}
