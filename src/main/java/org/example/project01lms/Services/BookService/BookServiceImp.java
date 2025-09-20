package org.example.project01lms.Services.BookService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.project01lms.Converter.BookConverter;
import org.example.project01lms.Dto.BookDto.BookCreationDto;
import org.example.project01lms.Dto.BookDto.BookResponseDto;
import org.example.project01lms.Dto.BookDto.BookUpdationDto;
import org.example.project01lms.Dto.BookDto.BooksDto;
import org.example.project01lms.Dto.LoanDto.LoanUpdationDto;
import org.example.project01lms.ExceptionHandling.NotAvailableException;
import org.example.project01lms.Mapper.BookMapper;
import org.example.project01lms.Models.Book;
import org.example.project01lms.Models.ExcelFile;
import org.example.project01lms.Repo.BookRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.poiji.bind.Poiji.fromExcel;

@Service
@Slf4j
public class BookServiceImp implements BookService {


    public BookConverter bookConverter;
    public BookRepo bookRepo;
    private BookMapper bookMapper;

    public BookServiceImp(BookConverter bookConverter, BookRepo bookRepo, BookMapper bookMapper) {
        this.bookConverter = bookConverter;
        this.bookRepo = bookRepo;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookResponseDto save(BookCreationDto bookCreationDto) {
        log.info("Creating book");
        Book book = bookMapper.toEntity(bookCreationDto);
        book = bookRepo.save(book);
        log.info("Book added successfully with isbnNumber {} " , book.getIsbnNumber());
        return bookMapper.toDto(book);
    }

    @Override
    public BookResponseDto update(LoanUpdationDto dto) {
        return null;
    }

    @Transactional
    @Override
    public BookResponseDto update( String isbnNumber , BookUpdationDto bookUpdationDto) {
        log.info("Updating Book details");
        Book book = bookRepo.findByIsbnNumber(isbnNumber)
                .orElseThrow(() -> new NotAvailableException("NOT_AVAILABLE", "Book with isbnNumber" + isbnNumber + " is not available"));

        bookMapper.updateBookFromDto(bookUpdationDto, book);
        bookRepo.save(book);
        log.info("Book details had been updated of {}" ,isbnNumber);
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookResponseDto> findAll() {
        log.info("Listing the Books");
        List<Book> bookList = bookRepo.findAll();
        log.info("Book list fetched");
        return bookMapper.toDtoList(bookList);
    }

    @Override
    public BookResponseDto findById(Long Id) {
        return null;
    }

    public BookResponseDto getByIsbnNo(String isbnNumber) {
        Book book = bookRepo.findByIsbnNumber(isbnNumber)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return bookMapper.toDto(book);
    }

    public void saveFromExcel(MultipartFile file) {
        try {
            File tempfile = File.createTempFile("books_data", ".xlsx");
            file.transferTo(tempfile);

            List<ExcelFile> excelBooks = fromExcel(tempfile, ExcelFile.class);
            for (ExcelFile excelBook : excelBooks) {
                BooksDto dto = getBooksDto(excelBook);

                Book bookentity = bookConverter.toEntity(dto);
                bookRepo.save(bookentity);
            }
            tempfile.delete();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static BooksDto getBooksDto(ExcelFile excelBook) {
        BooksDto dto = new BooksDto();
        dto.setBookName(excelBook.getBookName());
        dto.setAuthorName(excelBook.getAuthorName());
        dto.setPublisherName(excelBook.getPublisherName());
        dto.setIsbnNumber(excelBook.getIsbnNumber());
        dto.setBookQuantity(Long.parseLong(excelBook.getBookQuantity()));
        dto.setAvailableCopies(excelBook.getAvailableCopies());
        dto.setGenre(excelBook.getGenre());
        dto.setLanguage(excelBook.getLanguage());
        return dto;
    }


}
