package org.example.project01lms.Services.LoanService;

import org.example.project01lms.Converter.LoanConverter;
import org.example.project01lms.Dto.LoanDto;
import org.example.project01lms.ExceptionHandling.NotAvailableException;
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
import java.util.Optional;

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
    public LoanDto returnBook(LoanDto loanDto) {
      Loan loan = (loanRepo.findByCustomers_libraryIdAndBook_isbnNumber(loanDto.getLibraryId(), loanDto.getIsbnNumber())
              .orElseThrow(() -> new RuntimeException("Loan not found for this customer and book")));
      validation.validateOnReturn(loan);
      loan = loanRepo.save(loan);
      return loanConverter.toDto(loan);
    }

    @Override
    public LoanDto save(LoanDto loanDto) {
        Loan loan = loanConverter.toEntity(loanDto);
        loan = loanRepo.save(loan);
        loanDto = loanConverter.toDto(loan);
        return loanDto;
    }

    @Override
    public LoanDto update(LoanDto loanDto) {

        return null;
    }

    @Override
    public List<LoanDto> findall() {
       List<Loan> loanList = loanRepo.findAll();
       return loanConverter.toDtoList(loanList);
    }

    @Override
    public LoanDto findById(LoanDto loanDto) {
    Loan loan = loanRepo.findById(loanDto.getId())
            .orElseThrow(() -> new NotAvailableException("NOT_FOUND", "Loan with id " + loanDto.getLibraryId() + "not found"));
    return loanConverter.toDto(loan);
    }
}
