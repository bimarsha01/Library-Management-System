package org.example.project01lms.Controllers;

import jakarta.transaction.Transactional;
import org.example.project01lms.Dto.LoanDto;
import org.example.project01lms.Response.ApiResponse;
import org.example.project01lms.Services.LoanService.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/loan")
public class LoanController extends BaseController {

    public LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/get")
    @Transactional
    public ResponseEntity<ApiResponse> setLoanForCustomer(@RequestBody LoanDto loanDto){
    loanDto = (LoanDto) loanService.createLoan(loanDto);
        if(loanDto != null){
            return ResponseEntity.ok(successResponse("Customer created Successfully" , Boolean.TRUE , loanDto));
        }
        else{
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
