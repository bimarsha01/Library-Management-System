package org.example.project01lms.Mapper;

import org.example.project01lms.Dto.BooksDto;
import org.example.project01lms.Dto.CustomerDto;
import org.example.project01lms.Models.Book;
import org.example.project01lms.Models.Customers;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customers toEntity(CustomerDto dto);
    CustomerDto toDto(Customers customers);
    List<CustomerDto> toDtoList(List<Customers> customersList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomerFromDto(CustomerDto dto, @MappingTarget Customers customers);

}
