package org.example.project01lms.Services.LoanService;

import org.example.project01lms.Dto.LoanDto.LoanCreationDto;
import org.example.project01lms.Dto.LoanDto.LoanDto;
import org.example.project01lms.Dto.LoanDto.LoanResponseDto;
import org.example.project01lms.Dto.LoanDto.LoanUpdationDto;
import org.example.project01lms.Models.Loan;
import org.example.project01lms.Services.CRUDservies;

public interface LoanService extends CRUDservies<LoanCreationDto , LoanResponseDto, Loan> {

LoanDto returnBook(LoanDto loanDto);

LoanResponseDto update(String libraryId, LoanUpdationDto loanUpdationDto);

LoanResponseDto findByLId(String libraryId);
}
