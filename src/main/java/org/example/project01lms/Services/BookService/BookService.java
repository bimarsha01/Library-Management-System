package org.example.project01lms.Services.BookService;

import org.example.project01lms.Dto.BookDto.BooksDto;
import org.example.project01lms.Models.Book;
import org.example.project01lms.Services.CRUDservies;


public interface BookService extends CRUDservies<BooksDto , Book> {

    BooksDto getByIsbnNo(String isbnNumber);
}
