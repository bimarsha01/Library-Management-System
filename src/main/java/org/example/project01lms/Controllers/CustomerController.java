package org.example.project01lms.Controllers;

import org.example.project01lms.Converter.CustomerConverter;
import org.example.project01lms.Dto.CustomerDto;
import org.example.project01lms.Models.Customers;
import org.example.project01lms.Repo.CustomerRepo;
import org.example.project01lms.Response.ApiResponse;
import org.example.project01lms.Services.CustomerServices.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController  extends BaseController{

    public final CustomerService customerService;
    private final CustomerRepo customerRepo;
    private  final CustomerConverter customerConverter;

    public CustomerController(CustomerService customerService, CustomerRepo customerRepo, CustomerConverter customerConverter) {
        this.customerService = customerService;
        this.customerRepo = customerRepo;
        this.customerConverter = customerConverter;
    }


    @PostMapping("/save")
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody CustomerDto customerDto){
        customerDto = customerService.save(customerDto);
        if(customerDto != null){
            return ResponseEntity.ok(successResponse("Customer created Successfully" , Boolean.TRUE , customerDto));
        }
        else{
            return ResponseEntity.ok(successResponse("Customer creation Failed" , Boolean.FALSE , customerDto));
        }
    }
}
