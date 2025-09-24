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
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final Validation validation;

    public CustomerServiceImpl(CustomerRepo customerRepo, CustomerMapper customerMapper, Validation validation) {
        this.customerRepo = customerRepo;
        this.customerMapper = customerMapper;
        this.validation = validation;
    }

    @Override
    public CustomerResponseDto save(CustomerCreationDto customerCreationDto) {
        log.info("Creating new customer");
        Customers customers = customerMapper.toEntity(customerCreationDto);

        String libraryId = validation.generateUniqueLibraryId();
        customers.setLibraryId(libraryId);

        customers = customerRepo.save(customers);
        log.info("Customer created successfully with libraryId {}", libraryId);

        return customerMapper.toDto(customers);
    }

    @Override
    public CustomerResponseDto update(LoanUpdationDto customerDto) {
        throw new UnsupportedOperationException("Use updateCustomer() instead of update()");
    }

    @Override
    public List<CustomerResponseDto> findAll() {
        log.info("Fetching all customers");
        List<Customers> customersList = customerRepo.findAll();
        log.info("Fetched {} customers", customersList.size());
        return customerMapper.toDtoList(customersList);
    }

    @Override
    public CustomerResponseDto findById(Long id) {
        log.info("Fetching customer with id {}", id);
        Customers customer = customerRepo.findById(id)
                .orElseThrow(() -> new NotAvailableException("NOT_FOUND", "Customer with id " + id + " not found"));
        return customerMapper.toDto(customer);
    }

    public CustomerResponseDto getCustomerByLibraryId(String libraryId) {
        log.info("Fetching customer with libraryId {}", libraryId);
        Customers customer = customerRepo.findByLibraryId(libraryId)
                .orElseThrow(() -> new HandleDataException("Customer with libraryId " + libraryId + " not found"));
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerResponseDto updateCustomer(String libraryId, CustomerUpdationDto customerUpdationDto) {
        log.info("Updating customer with libraryId {}", libraryId);
        Customers customers = customerRepo.findByLibraryId(libraryId)
                .orElseThrow(() -> new NotAvailableException("NOT_FOUND", "Customer with libraryId " + libraryId + " not found"));

        customerMapper.updateCustomerFromDto(customerUpdationDto, customers);
        customers = customerRepo.save(customers);

        log.info("Customer with libraryId {} updated successfully", libraryId);
        return customerMapper.toDto(customers);
    }

    @Override
    public CustomerResponseDto removeCustomer(String libraryId) {
        log.info("Removing customer with libraryId {}", libraryId);
        Customers customers = customerRepo.findByLibraryId(libraryId)
                .orElseThrow(() -> new NotAvailableException("NOT_FOUND", "Customer with libraryId " + libraryId + " not found"));

        customerRepo.delete(customers);
        log.info("Customer with libraryId {} removed successfully", libraryId);

        return customerMapper.toDto(customers);
    }
}





