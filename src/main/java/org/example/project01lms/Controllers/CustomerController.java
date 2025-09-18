package org.example.project01lms.Controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.project01lms.Converter.CustomerConverter;
import org.example.project01lms.Dto.CustomerDto;
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
    public ResponseEntity<ApiResponse> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        log.info("Creating a customer {}" , customerDto.getLibraryId());
        customerDto = customerService.save(customerDto);
        if (customerDto != null) {
            log.info("Created a customer Successfully with id {}" , customerDto.getLibraryId());
            return ResponseEntity.ok(successResponse("Customer created Successfully", Boolean.TRUE, customerDto));
        } else {
            log.error("Customer creation failed");
            return ResponseEntity.ok(successResponse("Customer creation Failed", Boolean.FALSE, customerDto));
        }
    }

    @GetMapping("/find-by-lid")
    public ResponseEntity<ApiResponse> getCustomerByLibraryId( @RequestParam String libraryId) {
        log.info("Finding Customer with the id {}" , libraryId);
        CustomerDto customerDto = customerService.getCustomerByLibraryId(libraryId);

        if (customerDto != null) {
            log.info("Customer found successfully with id {}" , libraryId);
            return ResponseEntity.ok(successResponse("Customer found successfully", Boolean.TRUE, customerDto));
        } else {
            log.warn("Unable to find a customer with id {}" , libraryId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(successResponse("Customer not found", Boolean.FALSE, null));
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<ApiResponse> deleteCustomer(@RequestBody CustomerDto customerDto){
        log.warn("Customer with library id {} is set to be deleted" ,  customerDto.getLibraryId() );
        customerDto = customerService.removeCustomer(customerDto);
        log.info("Customer is being removed");

        if(customerDto != null){
            log.info(("Customer removed successfully"));
            return ResponseEntity.ok(successResponse("Customer Removed successfully", Boolean.TRUE, customerDto));
        }
        else{
            return ResponseEntity.ok(successResponse("Customer remove failed", Boolean.TRUE, customerDto));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateCustomer(@RequestBody CustomerDto customerDto){
        log.info("Update function will now come into the place");
        customerDto = customerService.updateCustomer(customerDto);
        if(customerDto !=null){
            return ResponseEntity.ok(successResponse("Customer Removed successfully", Boolean.TRUE, customerDto));
        }
        else{
            return ResponseEntity.ok(successResponse("Customer remove failed", Boolean.TRUE, customerDto));
        }
    }


}
