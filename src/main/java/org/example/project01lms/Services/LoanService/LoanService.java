package org.example.project01lms.Services.LoanService;

import org.example.project01lms.Dto.LoanDto.LoanCreationDto;
import org.example.project01lms.Dto.LoanDto.LoanResponseDto;
import org.example.project01lms.Dto.LoanDto.LoanUpdationDto;
import org.example.project01lms.Models.Loan;
import org.example.project01lms.Services.CRUDservies;

public interface LoanService extends CRUDservies<LoanCreationDto , LoanResponseDto, Loan> {

LoanResponseDto returnBook(String libraryId , String isbnNumber);

    LoanResponseDto update(String libraryId, LoanUpdationDto loanUpdationDto);

LoanResponseDto findByLibraryId(String libraryId);

    LoanResponseDto findById(Long id);

}
