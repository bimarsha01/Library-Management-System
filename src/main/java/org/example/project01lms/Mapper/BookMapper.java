package org.example.project01lms.Mapper;

import org.example.project01lms.Dto.BookDto.BookCreationDto;
import org.example.project01lms.Dto.BookDto.BookResponseDto;
import org.example.project01lms.Dto.BookDto.BookUpdationDto;
import org.example.project01lms.Dto.BookDto.BooksDto;
import org.example.project01lms.Models.Book;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "id", ignore = true)
    Book toEntity(BookCreationDto dto);

    BookResponseDto toDto(Book book);

    List<BookResponseDto> toDtoList(List<Book> bookList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBookFromDto(BookUpdationDto dto, @MappingTarget Book book);

}
