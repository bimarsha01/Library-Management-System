package org.example.project01lms.Services.BookService;

import jakarta.transaction.Transactional;
import org.example.project01lms.Dto.BookDto.BookCreationDto;
import org.example.project01lms.Dto.BookDto.BookResponseDto;
import org.example.project01lms.Dto.BookDto.BookUpdationDto;
import org.example.project01lms.Dto.BookDto.BooksDto;
import org.example.project01lms.Models.Book;
import org.example.project01lms.Services.CRUDservies;


public interface BookService extends CRUDservies<BookCreationDto , BookResponseDto, Book> {

    @Transactional
    BookResponseDto update(String isbnNumber , BookUpdationDto bookUpdationDto);

    BookResponseDto getByIsbnNo(String isbnNumber);
}
