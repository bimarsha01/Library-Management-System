package org.example.project01lms.Converter;

import org.example.project01lms.Dto.BooksDto;
import org.example.project01lms.Models.Book;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Component
public class BookConverter extends AbstractConverter<BooksDto , Book> {

    @Override
    public BooksDto toDto(Book book) {
       BooksDto booksDto = new BooksDto();
       booksDto.setBooksId(book.getBookId());
       booksDto.setBookName(book.getBookName());
       booksDto.setAuthorName(book.getAuthorName());
       booksDto.setPublisherName(book.getPublisherName());
       booksDto.setIsbnNumber(book.getIsbnNumber());
       booksDto.setBookQuantity(book.getBookQuantity());
       booksDto.setAvailableCopies(book.getAvailableCopies());
       booksDto.setGenre(book.getGenre());
       booksDto.setLanguage(book.getLanguage());
       booksDto.setCreatedAt(book.getCreatedAt());
       booksDto.setUpdatedAt(book.getUpdatedAt());
       return booksDto;
    }

    @Override
    public List<BooksDto> toDtoList(List<Book> entity) {
    List<BooksDto> booksDtoList = new ArrayList<>();
    for(Book book :entity){
        booksDtoList.add(toDto(book));
    }
    return booksDtoList;
    }

    @Override
    public Book toEntity(BooksDto booksDto) {
  Book book = new Book();
  book.setBookName(booksDto.getBookName());
  book.setPublisherName(booksDto.getPublisherName());
  book.setAuthorName(booksDto.getAuthorName());
  book.setIsbnNumber(booksDto.getIsbnNumber());
  book.setBookQuantity(booksDto.getBookQuantity());
  book.setAvailableCopies(booksDto.getAvailableCopies());
  book.setGenre(booksDto.getGenre());
  book.setLanguage(booksDto.getLanguage());


  return book;
    }
}
