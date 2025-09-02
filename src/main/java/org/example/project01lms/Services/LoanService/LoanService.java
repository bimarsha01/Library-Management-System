package org.example.project01lms.Services.LoanService;

import org.example.project01lms.Dto.LoanDto;
import org.example.project01lms.Models.Loan;
import org.example.project01lms.Services.CRUDservies;

public interface LoanService extends CRUDservies<LoanDto , Loan> {

LoanDto returnBook(LoanDto loanDto);
}
