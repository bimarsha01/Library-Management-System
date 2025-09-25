package org.example.project01lms.Mapper;

import org.example.project01lms.Dto.LoanDto.LoanCreationDto;
import org.example.project01lms.Dto.LoanDto.LoanResponseDto;
import org.example.project01lms.Dto.LoanDto.LoanUpdationDto;
import org.example.project01lms.Models.Loan;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanMapper {

    @Mapping(target = "id", ignore = true)

    Loan toEntity(LoanCreationDto dto);

    @Mapping(target = "libraryId", source = "customers.libraryId")
    @Mapping(target = "bookName", source = "book.bookName")
    @Mapping(target = "authorName", source = "book.authorName")
    @Mapping(target = "isbnNumber", source = "book.isbnNumber")
    LoanResponseDto toDto(Loan loan);

    List<LoanResponseDto> toDtoList(List<Loan> loanList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateLoanFromDto(LoanUpdationDto dto, @MappingTarget Loan loan);
}
