package org.example.project01lms.Services.CustomerServices;

import org.example.project01lms.Converter.CustomerConverter;
import org.example.project01lms.Dto.CustomerDto;
import org.example.project01lms.Models.Customers;
import org.example.project01lms.Repo.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CustomerServiceImp implements CustomerService {

    private final CustomerConverter customerConverter;
    private final CustomerRepo customerRepo;

    public CustomerServiceImp(CustomerConverter customerConverter, CustomerRepo customerRepo) {
        this.customerConverter = customerConverter;
        this.customerRepo = customerRepo;
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customers customers = customerConverter.toEntity(customerDto);

        if (customerDto.getLibraryId() == null) {
            String randomLid = generateUniqueLibraryId();
            customers.setLibraryId(randomLid);
        }

        customers = customerRepo.save(customers);
        return customerConverter.toDto(customers);
    }

    private String generateUniqueLibraryId() {
        String lid;
        do {
            lid = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
        } while (customerRepo.existsByLibraryId(lid));
        return lid;
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) {
        return null;
    }

    @Override
    public List<CustomerDto> findall() {
        return List.of();
    }

    @Override
    public CustomerDto findById(CustomerDto customerDto) {
        return null;
    }

    public CustomerDto getCustomerByLibraryId(String libraryId) {
        Customers customer = customerRepo.findByLibraryId(libraryId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return customerConverter.toDto(customer);
    }

}
