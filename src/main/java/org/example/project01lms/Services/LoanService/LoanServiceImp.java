package org.example.project01lms.Services.LoanService;

import org.example.project01lms.Converter.LoanConverter;
import org.example.project01lms.Dto.LoanDto;
import org.example.project01lms.Models.Book;
import org.example.project01lms.Models.Customers;
import org.example.project01lms.Models.Loan;
import org.example.project01lms.Repo.BookRepo;
import org.example.project01lms.Repo.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImp implements LoanService {

    private final CustomerRepo customerRepo;
    private final BookRepo bookRepo;
    public LoanConverter loanConverter;

    public LoanServiceImp(CustomerRepo customerRepo, BookRepo bookRepo , LoanConverter loanConverter) {
        this.customerRepo = customerRepo;
        this.bookRepo = bookRepo;
        this.loanConverter = loanConverter;
    }

    @Override
    public Object createLoan(LoanDto loanDto) {
        Loan loan = loanConverter.toEntity(loanDto);
        loanDto = loanConverter.toDto(loan);
        return loanDto;
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
