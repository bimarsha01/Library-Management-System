package org.example.project01lms.Services.LoanService;

import lombok.extern.slf4j.Slf4j;
import org.example.project01lms.Dto.LoanDto.LoanCreationDto;
import org.example.project01lms.Dto.LoanDto.LoanResponseDto;
import org.example.project01lms.Dto.LoanDto.LoanUpdationDto;
import org.example.project01lms.ExceptionHandling.NotFoundException;
import org.example.project01lms.Helper.Update;
import org.example.project01lms.Helper.Validation;
import org.example.project01lms.Mapper.LoanMapper;
import org.example.project01lms.Models.Loan;
import org.example.project01lms.Repo.BookRepo;
import org.example.project01lms.Repo.CustomerRepo;
import org.example.project01lms.Repo.LoanRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LoanServiceImp implements LoanService {

    private final CustomerRepo customerRepo;
    private final BookRepo bookRepo;
    private final LoanRepo loanRepo;
    private final Validation validation;
    private final Update update;
    private final LoanMapper loanMapper;

    public LoanServiceImp(CustomerRepo customerRepo,
                          BookRepo bookRepo,
                          LoanRepo loanRepo,
                          Validation validation,
                          Update update,
                          LoanMapper loanMapper) {
        this.customerRepo = customerRepo;
        this.bookRepo = bookRepo;
        this.loanRepo = loanRepo;
        this.validation = validation;
        this.update = update;
        this.loanMapper = loanMapper;
    }

    @Override
    public LoanResponseDto returnBook(String libraryId, String isbnNumber) {
        Loan loan = loanRepo.findByCustomers_libraryIdAndBook_isbnNumber(libraryId, isbnNumber)
                .orElseThrow(() -> new NotFoundException("NOT_FOUND",
                        "No loan found for libraryId " + libraryId + " with ISBN " + isbnNumber));

        validation.validateOnReturn(loan);
        update.updateOnReturn(loan);
        loanRepo.save(loan);

        return loanMapper.toDto(loan);
    }

    @Override
    public LoanResponseDto save(LoanCreationDto loanCreationDto) {
        Loan loan = loanMapper.toEntity(loanCreationDto);
        loan = loanRepo.save(loan);
        return loanMapper.toDto(loan);
    }

    @Override
    public LoanResponseDto update(LoanUpdationDto dto) {
        log.warn("This method is not implemented");
        return null;
    }

    @Override
    public LoanResponseDto update(String libraryId, LoanUpdationDto loanUpdationDto) {
        Loan loan = loanRepo.findByCustomers_libraryId(libraryId)
                .orElseThrow(() -> new NotFoundException("NOT_FOUND",
                        "No loan found for libraryId: " + libraryId));

        log.info("Updating loan for customer with libraryId: {}", libraryId);

        loanMapper.updateLoanFromDto(loanUpdationDto, loan);
        loanRepo.save(loan);

        log.info("Loan updated successfully for libraryId: {}", libraryId);
        return loanMapper.toDto(loan);
    }

    @Override
    public List<LoanResponseDto> findAll() {
        return loanMapper.toDtoList(loanRepo.findAll());
    }

    @Override
    public LoanResponseDto findById(Long id) {
        log.info("Finding loan with id: {}", id);

        Loan loan = loanRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("NOT_FOUND",
                        "Loan with id " + id + " not found"));

        return loanMapper.toDto(loan);
    }

    public LoanResponseDto findByLId(String libraryId) {
        log.info("Finding loan using libraryId: {}", libraryId);

        Loan loan = loanRepo.findByCustomers_libraryId(libraryId)
                .orElseThrow(() -> new NotFoundException("LOAN_NOT_FOUND",
                        "There is no loan under this libraryId: " + libraryId));

        return loanMapper.toDto(loan);
    }
}
