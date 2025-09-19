package org.example.project01lms.Mapper;

import org.example.project01lms.Dto.CustomerDto.CustomerCreationDto;
import org.example.project01lms.Dto.CustomerDto.CustomerDto;
import org.example.project01lms.Dto.CustomerDto.CustomerResponseDto;
import org.example.project01lms.Dto.CustomerDto.CustomerUpdationDto;
import org.example.project01lms.Models.Customers;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "id", ignore = true)
    Customers toEntity(CustomerCreationDto dto);

    CustomerResponseDto toDto(Customers customers);

    List<CustomerResponseDto> toDtoList(List<Customers> customersList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    CustomerUpdationDto updateCustomerFromDto(CustomerUpdationDto dto, @MappingTarget Customers customers);

}
