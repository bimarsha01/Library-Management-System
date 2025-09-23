package org.example.project01lms.Services.CustomerServices;

import jakarta.transaction.Transactional;
import org.example.project01lms.Dto.CustomerDto.CustomerCreationDto;
import org.example.project01lms.Dto.CustomerDto.CustomerResponseDto;
import org.example.project01lms.Dto.CustomerDto.CustomerUpdationDto;
import org.example.project01lms.Models.Customers;
import org.example.project01lms.Services.CRUDservies;

public interface CustomerService extends CRUDservies<CustomerCreationDto , CustomerResponseDto, Customers> {
    CustomerResponseDto getCustomerByLibraryId(String libraryId);

    @Transactional
    CustomerResponseDto  updateCustomer(String libraryId ,  CustomerUpdationDto customerUpdationDto);

    CustomerResponseDto removeCustomer(String libraryId);


}
