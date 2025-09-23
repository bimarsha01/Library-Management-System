package org.example.project01lms.Controllers;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.project01lms.Dto.LoanDto.LoanCreationDto;
import org.example.project01lms.Dto.LoanDto.LoanResponseDto;
import org.example.project01lms.Dto.LoanDto.LoanUpdationDto;
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

    @PostMapping("/get")
    @Transactional
    public ResponseEntity<ApiResponse> setLoanForCustomer(@RequestBody LoanCreationDto loanCreationDto) {
        log.info("Setting a loan for customer");

        LoanResponseDto loanResponseDto = loanService.save(loanCreationDto);

        if (loanResponseDto != null) {
            log.info("Loan has been set for customer {}", loanResponseDto);
            return ResponseEntity.ok(
                    successResponse("Loan created successfully", Boolean.TRUE, loanResponseDto)
            );
        } else {
            log.info("Loan was not approved");
            return ResponseEntity.ok(
                    successResponse("Loan creation failed", Boolean.FALSE, null)
            );
        }
    }


    @Transactional
    @PostMapping("/book/return")
    public ResponseEntity<ApiResponse> returnBook(
            @RequestParam String libraryId,
            @RequestParam String isbnNummber){
        LoanResponseDto loanResponseDto = loanService.returnBook(libraryId , isbnNummber);
        if(loanResponseDto != null){
            return ResponseEntity.ok(successResponse(" Customer Returned " , Boolean.TRUE , loanResponseDto));
        }
        else{
            return ResponseEntity.ok(successResponse("Customer Returned Failed" , Boolean.FALSE , null));
        }
    }

    @GetMapping("/find-by-lid/{libraryId}")
    public ResponseEntity<ApiResponse> findByLId(@PathVariable String libraryId){
       LoanResponseDto loanResponseDto =  loanService.findByLId(libraryId);
        if(loanResponseDto != null){
            return ResponseEntity.ok(successResponse(" Customer Returned " , Boolean.TRUE , loanResponseDto));
        }
        else{
            return ResponseEntity.ok(successResponse("Customer Returned Failed" , Boolean.FALSE , null));
        }

    }@GetMapping("/find-by-id/{Id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long Id){
       LoanResponseDto loanResponseDto =  loanService.findById(Id);
        if(loanResponseDto != null){
            return ResponseEntity.ok(successResponse(" Customer Returned " , Boolean.TRUE , loanResponseDto));
        }
        else{
            return ResponseEntity.ok(successResponse("Customer Returned Failed" , Boolean.FALSE , null));
        }
    }
    @GetMapping("/update/{libraryId}")
    public ResponseEntity<ApiResponse> update(
            @PathVariable String libraryId ,
            @RequestBody LoanUpdationDto loanUpdationDto
            ){
        LoanResponseDto loanResponseDto = loanService.update(loanUpdationDto);
        if( loanResponseDto != null){
            log.info("Loan has been set for customer {}" ,libraryId);
            return ResponseEntity.ok(successResponse("Customer created Successfully" , Boolean.TRUE , loanResponseDto));
        }
        else{
            log.info("Loan was not approved");
            return ResponseEntity.ok(successResponse("Customer creation Failed" , Boolean.FALSE , null));
        }
    }

}
