package org.example.project01lms.Converter;

import org.example.project01lms.Dto.CustomerDto;
import org.example.project01lms.Models.Customers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CustomerConverter extends AbstractConverter<CustomerDto , Customers> {


    @Override
    public CustomerDto toDto(Customers customers) {
       CustomerDto customerDto = new CustomerDto();
       customerDto.setCustomerId(customers.getCustomerId());
       customerDto.setFullName(customers.getFullName());
       customerDto.setEmail(customers.getEmail());
       customerDto.setAddress(customers.getAddress());
       customerDto.setPhoneNo(customers.getPhoneNo());
       customerDto.setIsActive(customers.getIsActive());
       customerDto.setLibraryId(customers.getLibraryId());
       return customerDto;
    }

    @Override
    public List<CustomerDto> toDtoList(List<Customers> entity) {
            List<CustomerDto> customerDtoList = new ArrayList<>();
            for(Customers customers : entity){
                customerDtoList.add(toDto(customers));
            }
            return customerDtoList;
    }

    @Override
    public Customers toEntity(CustomerDto customerDto) {
    Customers customers = new Customers();
    customers.setFullName(customerDto.getFullName());
    customers.setEmail(customerDto.getEmail());
//    customers.setPassword(customerDto.getPassword());
    customers.setAddress(customerDto.getAddress());
    customers.setPhoneNo(customerDto.getPhoneNo());
    customers.setIsActive(customerDto.getIsActive());
    return customers;
    }
}
