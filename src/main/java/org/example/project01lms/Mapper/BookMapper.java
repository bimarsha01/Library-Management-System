package org.example.project01lms.Mapper;

import org.example.project01lms.Dto.BooksDto;
import org.example.project01lms.Models.Book;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toEntity(BooksDto dto);

    BooksDto toDto(Book book);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBookFromDto(BooksDto dto, @MappingTarget Book book);
}
