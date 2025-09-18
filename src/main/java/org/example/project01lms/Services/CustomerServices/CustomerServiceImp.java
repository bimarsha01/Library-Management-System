package org.example.project01lms.Services.CustomerServices;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.project01lms.Converter.CustomerConverter;
import org.example.project01lms.Dto.CustomerDto;
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

    private final CustomerConverter customerConverter;
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final Validation validation;

    public CustomerServiceImp(CustomerConverter customerConverter, CustomerRepo customerRepo, CustomerMapper customerMapper, Validation validation) {
        this.customerConverter = customerConverter;
        this.customerRepo = customerRepo;
        this.customerMapper = customerMapper;
        this.validation = validation;
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        log.info("Customer being created with unique id ");
        Customers customers = customerConverter.toEntity(customerDto);
        String randomLid;
        if (customerDto.getLibraryId() == null) {
            randomLid = validation.generateUniqueLibraryId();
            customers.setLibraryId(randomLid);
            log.info("Customer created successfully with id {}", randomLid);
        }

        customers = customerRepo.save(customers);
        return customerMapper.toDto(customers);
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) {
        throw new UnsupportedOperationException("Use updateCustomer() instead of update()");
    }


    @Override
    public List<CustomerDto> findall() {
        List<Customers> customersList = customerRepo.findAll();
        return customerMapper.toDtoList(customersList);
    }

    @Override
    public CustomerDto findById(CustomerDto customerDto) {
        Customers customers = (customerRepo.findById(customerDto.getCustomerId())
                .orElseThrow(() -> new NotAvailableException("NOT_FOUND", "Customer with id " + customerDto.getCustomerId() + "did not match to the database")));
        log.info("Customer found successfully {} ", customerDto.getLibraryId());
        return customerMapper.toDto(customers);
    }

    public CustomerDto getCustomerByLibraryId(String libraryId) {
        Customers customer = customerRepo.findByLibraryId(libraryId)
                .orElseThrow(() -> new HandleDataException("Customer with " + libraryId + " not found"));
        log.info("Customer found with lid {}", libraryId);
        return customerMapper.toDto(customer);
    }

    @Transactional
    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        log.info("Customer being updated {}", customerDto.getLibraryId());
        Customers customers = (customerRepo.findByLibraryId(customerDto.getLibraryId())
                .orElseThrow(() -> new NotAvailableException("NOT_FOUND", "Customer with lid " + customerDto.getLibraryId() + " not found")));

        customerMapper.updateCustomerFromDto(customerDto, customers);

        customerRepo.save(customers);
        log.info("Customer updated Successfully {}", customerDto.getLibraryId());
        return customerMapper.toDto(customers);
    }

    @Override
    public CustomerDto removeCustomer(CustomerDto customerDto) {
        Customers customers = customerRepo.findByLibraryId(customerDto.getLibraryId())
                .orElseThrow(() -> new RuntimeException("There is no customer with the given Library id"));

        customerRepo.delete(customers);
        return customerMapper.toDto(customers);
    }
}




