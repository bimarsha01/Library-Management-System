package org.example.project01lms.Services.LoanService;

import lombok.extern.slf4j.Slf4j;
import org.example.project01lms.Converter.LoanConverter;
import org.example.project01lms.Dto.LoanDto.LoanCreationDto;
import org.example.project01lms.Dto.LoanDto.LoanDto;
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
    private final LoanConverter loanConverter;
    private final Validation validation;
    private final Update update;
    private final LoanMapper loanMapper;


    public LoanServiceImp(CustomerRepo customerRepo, BookRepo bookRepo , LoanConverter loanConverter, LoanRepo loanRepo , Validation validation , Update update, LoanMapper loanMapper) {
        this.customerRepo = customerRepo;
        this.bookRepo = bookRepo;
        this.loanConverter = loanConverter;
        this.loanRepo = loanRepo;
        this.validation = validation;
        this.update = update;
        this.loanMapper = loanMapper;
    }

//    @Override
//    public LoanResponseDto returnBook(LoanDto loanDto) {
//        LoanDto finalLoanDto = loanDto;
//        Loan loan = loanRepo.findByCustomers_libraryIdAndBook_isbnNumber(
//                loanDto.getLibraryId(),
//                loanDto.getIsbnNumber()
//        ).orElseThrow(() -> new NotFoundException("NOT_FOUND" , "The user with library id " + finalLoanDto.getLibraryId() + " with isbn Number "+ finalLoanDto.getIsbnNumber() + "is not found"));
//
//        validation.validateOnReturn(loan);
//        update.updateOnReturn(loan);
//       loan = loanRepo.save(loan);
//        return loanDto;
//    }


    @Override
    public LoanResponseDto save(LoanCreationDto loanCreationDto) {
        Loan loan = loanMapper.toEntity(loanCreationDto);
        loan = loanRepo.save(loan);
        return loanMapper.toDto(loan);
    }


    @Override
    public LoanResponseDto update(String libraryId, LoanUpdationDto loanUpdationDto) {
        Loan loan = (Loan)loanRepo.findByCustomers_libraryId(libraryId)
                .orElseThrow(() -> new NotFoundException("NOT_FOUND" , "the library id you are looking is not found or is not in the database"));
        log.info("Loan is being updated for customer with Library id : {} " , libraryId);
        loanMapper.updateLoanFromDto(loanUpdationDto ,  loan);
        loanRepo.save(loan);
        log.info("Loan has been updated successfully for the customer : {}" ,libraryId);
       return  loanMapper.toDto(loan);
    }

    @Override
    public List<LoanDto> findAll() {
       List<Loan> loanList = loanRepo.findAll();
       return loanMapper.toDtoList(loanList);
    }

    @Override
    public LoanResponseDto findById(Long Id) {
        return null;
    }

    @Override
    public LoanDto findById(LoanDto loanDto) {
        log.info("Finding the details of the customer with the given library id : {}" , loanDto.getLibraryId());
    Loan loan = (Loan) loanRepo.findByCustomers_libraryId(loanDto.getLibraryId())
            .orElseThrow(() -> new NotFoundException("NOT_FOUND", "Loan with id " + loanDto.getLibraryId() + "not found"));
    return loanMapper.toDto(loan);
    }
}
