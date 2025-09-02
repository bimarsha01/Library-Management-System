package org.example.project01lms.Controllers;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.project01lms.Dto.LoanDto;
import org.example.project01lms.Response.ApiResponse;
import org.example.project01lms.Services.LoanService.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/loan")
@Slf4j
public class LoanController extends BaseController {

    public LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/get")
    @Transactional
    public ResponseEntity<ApiResponse> setLoanForCustomer(@RequestBody LoanDto loanDto){
        log.info("Setting a loan for customer");
    loanDto = (LoanDto) loanService.createLoan(loanDto);
        if(loanDto != null){
            log.info("Loan has been set for customer {}" , loanDto.getLibraryId());
            return ResponseEntity.ok(successResponse("Customer created Successfully" , Boolean.TRUE , loanDto));
        }
        else{
            log.info("Loan was not approved");
            return ResponseEntity.ok(successResponse("Customer creation Failed" , Boolean.FALSE , loanDto));
        }
    }

    @Transactional
    @PostMapping("/book/return")
    public ResponseEntity<ApiResponse> returnBook(@RequestBody LoanDto loanDto){
        loanDto = loanService.returnBook(loanDto);
        if(loanDto != null){
            return ResponseEntity.ok(successResponse("Customer created Successfully" , Boolean.TRUE , loanDto));
        }
        else{
            return ResponseEntity.ok(successResponse("Customer creation Failed" , Boolean.FALSE , loanDto));
        }
    }

}
