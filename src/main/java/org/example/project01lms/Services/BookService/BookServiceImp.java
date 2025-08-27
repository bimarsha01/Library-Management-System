package org.example.project01lms.Services.BookService;

import org.example.project01lms.Converter.BookConverter;
import org.example.project01lms.Dto.BooksDto;
import org.example.project01lms.Models.Book;
import org.example.project01lms.Repo.BookRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
@Service
public class BookServiceImp  implements BookService{

    public BookConverter bookConverter;
    public BookRepo bookRepo;


    public BookServiceImp(BookConverter bookConverter , BookRepo bookRepo) {
        this.bookConverter = bookConverter;
        this.bookRepo = bookRepo;
    }

    @Override
    public BooksDto save(BooksDto booksDto) {
        Book book = bookConverter.toEntity(booksDto);
        book = bookRepo.save(book);
        return bookConverter.toDto(book);
    }


    @Override
    public BooksDto update(BooksDto booksDto) {
        return null;
    }

    @Override
    public List<BooksDto> findall() {
        return List.of();
    }

    @Override
    public BooksDto findById(BooksDto booksDto) {
        return null;
    }
public BooksDto getByIsbnNo(String isbnNumber){
        Book book = bookRepo.findByIsbnNumber(isbnNumber)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    return bookConverter.toDto(book);
}
}
