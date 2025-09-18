package org.example.project01lms.Mapper;

import org.example.project01lms.Dto.BooksDto;
import org.example.project01lms.Models.Book;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "id", ignore = true)
    Book toEntity(BooksDto dto);

    BooksDto toDto(Book book);

    List<BooksDto> toDtoList(List<Book> bookList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBookFromDto(BooksDto dto, @MappingTarget Book book);

}
