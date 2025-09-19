package org.example.project01lms.Mapper;

import org.example.project01lms.Dto.LoanDto.LoanDto;
import org.example.project01lms.Models.Loan;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")

public interface LoanMapper {

    @Mapping(target = "id", ignore = true)
    Loan toEntity(LoanDto dto);

    LoanDto toDto(Loan loan);

    List<LoanDto> toDtoList(List<Loan> loanList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateLoanFromDto(LoanDto dto, @MappingTarget Loan loan);



}
