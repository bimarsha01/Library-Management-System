package org.example.project01lms.Services.CustomerServices;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.project01lms.Dto.CustomerDto.CustomerCreationDto;
import org.example.project01lms.Dto.CustomerDto.CustomerResponseDto;
import org.example.project01lms.Dto.CustomerDto.CustomerUpdationDto;
import org.example.project01lms.Dto.LoanDto.LoanUpdationDto;
import org.example.project01lms.ExceptionHandling.HandleDataException;
import org.example.project01lms.ExceptionHandling.NotAvailableException;
import org.example.project01lms.Helper.Validation;
import org.example.project01lms.Mapper.CustomerMapper;
import org.example.project01lms.Models.Customers;
import org.example.project01lms.Repo.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final Validation validation;

    public CustomerServiceImp( CustomerRepo customerRepo, CustomerMapper customerMapper, Validation validation) {
        this.customerRepo = customerRepo;
        this.customerMapper = customerMapper;
        this.validation = validation;
    }

    @Override
    public CustomerResponseDto save(CustomerCreationDto customerCreationDto) {
        log.info("Customer being created with unique id ");
        Customers customers = customerMapper.toEntity(customerCreationDto);
        String randomLid;
            randomLid = validation.generateUniqueLibraryId();
            customers.setLibraryId(randomLid);
            log.info("Customer created successfully with id {}", randomLid);

        customers = customerRepo.save(customers);
        CustomerResponseDto responseDto =  customerMapper.toDto(customers);
        return responseDto;
    }

    @Override
    public CustomerResponseDto update(LoanUpdationDto customerDto) {
        throw new UnsupportedOperationException("Use updateCustomer() instead of update()");
    }


    @Override
    public List<CustomerResponseDto> findAll() {
        List<Customers> customersList = customerRepo.findAll();
        List<CustomerResponseDto> responseDto =customerMapper.toDtoList(customersList);
        return responseDto;
    }

    @Override
    public CustomerResponseDto findById(Long Id) {
        return null;
    }
    public CustomerResponseDto getCustomerByLibraryId(String libraryId) {
        Customers customer = customerRepo.findByLibraryId(libraryId)
                .orElseThrow(() -> new HandleDataException("Customer with " + libraryId + " not found"));
        log.info("Customer found with lid {}", libraryId);
        return customerMapper.toDto(customer);
    }

    @Transactional
    @Override
    public CustomerResponseDto updateCustomer(String  libraryId ,  CustomerUpdationDto customerUpdationDto) {
        log.info("Customer being updated {}", libraryId);
        Customers customers = (customerRepo.findByLibraryId(libraryId)
                .orElseThrow(() -> new NotAvailableException("NOT_FOUND", "Customer with lid " + libraryId + " not found")));

        customerMapper.updateCustomerFromDto(customerUpdationDto, customers);

        customerRepo.save(customers);
        log.info("Customer updated Successfully {}",libraryId);
        CustomerResponseDto responseDto =  customerMapper.toDto(customers);
        return responseDto;
    }


    @Override
    public CustomerResponseDto removeCustomer(String libraryId) {
        Customers customers = customerRepo.findByLibraryId(libraryId)
                .orElseThrow(() -> new RuntimeException("There is no customer with the given Library id"));

        customerRepo.delete(customers);
        return customerMapper.toDto(customers);
    }
}




