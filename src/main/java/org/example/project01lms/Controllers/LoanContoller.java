package org.example.project01lms.Controllers;

import org.example.project01lms.Dto.LoanDto;
import org.example.project01lms.Services.LoanService.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/loan")
public class LoanContoller {

    public LoanService loanService;

    public LoanContoller(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/get")
    public ResponseEntity<String> setLoanForCustomer(@RequestBody LoanDto loanDto){
loanDto = (LoanDto) loanService.createLoan(loanDto);
return null;
    }

}
