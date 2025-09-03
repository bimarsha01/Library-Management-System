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


//    THIS  IS GENERALLY USED TO UPDATE SOME THINGS LIKE FROM THE ENTITY AND ALSO THE ATTRIBUTES NOT CHANGING THE ACTUAL ATTRIBUTE HELPING THE USER TO NOT CHANGE AND PASS EVERY OTHER FIELDS
//    OR ATTRIBUTES MAKING IT MORE REASONALE
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBookFromDto(BooksDto dto, @MappingTarget Book book);
}
