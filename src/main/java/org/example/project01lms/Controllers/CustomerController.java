package org.example.project01lms.Controllers;

import org.example.project01lms.Dto.CustomerDto;
import org.example.project01lms.Response.ApiResponse;
import org.example.project01lms.Services.CustomerServices.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController  extends BaseController{

    public final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
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
