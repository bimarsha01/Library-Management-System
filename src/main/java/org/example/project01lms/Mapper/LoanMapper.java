package org.example.project01lms.Mapper;

import org.example.project01lms.Dto.LoanDto.LoanCreationDto;
import org.example.project01lms.Dto.LoanDto.LoanResponseDto;
import org.example.project01lms.Dto.LoanDto.LoanUpdationDto;
import org.example.project01lms.Models.Loan;
import org.mapstruct.*;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface LoanMapper {

    @Mapping(target = "id", ignore = true)
    Loan toEntity(LoanCreationDto loanCreationDto);

    @Mapping(source = "id", target = "loanId")
    @Mapping(source = "customer.id", target = "customerId")
    LoanResponseDto toDto(Optional<Loan> loan);

    List<LoanResponseDto> toDtoList(List<Loan> loanList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateLoanFromDto(LoanUpdationDto dto, @MappingTarget Loan loan);
}




