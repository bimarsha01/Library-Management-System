package org.example.project01lms.Controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.project01lms.Converter.CustomerConverter;
import org.example.project01lms.Dto.CustomerDto.CustomerCreationDto;
import org.example.project01lms.Dto.CustomerDto.CustomerDto;
import org.example.project01lms.Dto.CustomerDto.CustomerResponseDto;
import org.example.project01lms.Dto.CustomerDto.CustomerUpdationDto;
import org.example.project01lms.Repo.CustomerRepo;
import org.example.project01lms.Response.ApiResponse;
import org.example.project01lms.Services.CustomerServices.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController  extends BaseController{

    public final CustomerService customerService;
    private final CustomerRepo customerRepo;
    private  final CustomerConverter customerConverter;

    public CustomerController(CustomerService customerService, CustomerRepo customerRepo, CustomerConverter customerConverter) {
        this.customerService = customerService;
        this.customerRepo = customerRepo;
        this.customerConverter = customerConverter;
    }


    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCustomer(
            @Valid @RequestBody CustomerCreationDto customerCreationDto) {

        CustomerResponseDto createdCustomer = customerService.save(customerCreationDto);

        if (createdCustomer != null) {
            log.info("Created a customer successfully with id {}", createdCustomer.getLibraryId());
            return ResponseEntity.ok(
                    successResponse("Customer created successfully", Boolean.TRUE, createdCustomer)
            );
        } else {
            log.error("Customer creation failed");
            return ResponseEntity.ok(
                    successResponse("Customer creation failed", Boolean.FALSE, null)
            );
        }
    }

    @GetMapping("/find-by-lid/{libraryId}")
    public ResponseEntity<ApiResponse> findById(@PathVariable String libraryId) {
        log.info("Finding Customer with the id {}" , libraryId);
        CustomerResponseDto responseDto = customerService.getCustomerByLibraryId(libraryId);

        if (responseDto != null) {
            log.info("Customer found successfully with id {}" , libraryId);
            return ResponseEntity.ok(successResponse("Customer found successfully", Boolean.TRUE, responseDto));
        } else {
            log.warn("Unable to find a customer with id {}" , libraryId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(successResponse("Customer not found", Boolean.FALSE, null));
        }
    }

    @PostMapping("/delete/{libraryId}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable String libraryId){
        log.warn("Customer with library id {} is set to be deleted" , libraryId);
        CustomerResponseDto  response = customerService.removeCustomer(libraryId);
        log.info("Customer is being removed");

        if(response != null){
            log.info(("Customer removed successfully"));
            return ResponseEntity.ok(successResponse("Customer Removed successfully", Boolean.TRUE, response));
        }
        else{
            return ResponseEntity.ok(successResponse("Customer remove failed", Boolean.TRUE, null));
        }
    }

    @PostMapping("/update/{libraryId}")
    public ResponseEntity<ApiResponse> updateCustomer(
            @PathVariable String libraryId,
          @Valid  @RequestBody CustomerUpdationDto customerUpdationDto){
        log.info("Update function will now come into the place");
        CustomerResponseDto responseDto = customerService.updateCustomer(libraryId , customerUpdationDto);
        if(responseDto !=null){
            return ResponseEntity.ok(successResponse("Customer Removed successfully", Boolean.TRUE, responseDto));
        }
        else{
            return ResponseEntity.ok(successResponse("Customer remove failed", Boolean.TRUE, null));
        }
    }


}
