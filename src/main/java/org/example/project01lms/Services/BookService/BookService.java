package org.example.project01lms.Services.BookService;

import jakarta.transaction.Transactional;
import org.example.project01lms.Dto.BooksDto;
import org.example.project01lms.Models.Book;
import org.example.project01lms.Services.CRUDservies;
import org.springframework.stereotype.Service;


public interface BookService extends CRUDservies<BooksDto , Book> {

}
