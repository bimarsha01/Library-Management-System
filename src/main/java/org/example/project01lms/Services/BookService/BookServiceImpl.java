package org.example.project01lms.Services.BookService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
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
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepo bookRepo, BookMapper bookMapper) {
        this.bookRepo = bookRepo;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookResponseDto save(BookCreationDto bookCreationDto) {
        log.info("Creating book");
        Book book = bookMapper.toEntity(bookCreationDto);
        book = bookRepo.save(book);
        log.info("Book added successfully with isbnNumber {}", book.getIsbnNumber());
        return bookMapper.toDto(book);
    }

    @Override
    public BookResponseDto update(LoanUpdationDto dto) {
        return null;
    }

    @Override
    public BookResponseDto update(String isbnNumber, BookUpdationDto bookUpdationDto) {
        log.info("Updating book details for isbnNumber {}", isbnNumber);
        Book book = bookRepo.findByIsbnNumber(isbnNumber)
                .orElseThrow(() -> new NotAvailableException("NOT_AVAILABLE", "Book with isbnNumber " + isbnNumber + " is not available"));

        bookMapper.updateBookFromDto(bookUpdationDto, book);
        bookRepo.save(book);
        log.info("Book details updated successfully for isbnNumber {}", isbnNumber);
        return bookMapper.toDto(book);
    }

    @Override
    public List<BookResponseDto> findAll() {
        log.info("Fetching all books");
        List<Book> bookList = bookRepo.findAll();
        log.info("Fetched {} books", bookList.size());
        return bookMapper.toDtoList(bookList);
    }

    @Override
    public BookResponseDto findById(Long id) {
        log.info("Fetching book with id {}", id);
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new NotAvailableException("NOT_AVAILABLE", "Book with id " + id + " is not available"));
        return bookMapper.toDto(book);
    }

    @Override
    public BookResponseDto getByIsbnNo(String isbnNumber) {
        log.info("Fetching book with isbnNumber {}", isbnNumber);
        Book book = bookRepo.findByIsbnNumber(isbnNumber)
                .orElseThrow(() -> new NotAvailableException("NOT_AVAILABLE", "Book with isbnNumber " + isbnNumber + " is not available"));
        return bookMapper.toDto(book);
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


    //    public void saveFromExcel(MultipartFile file) {
//        try {
//            File tempfile = File.createTempFile("books_data", ".xlsx");
//            file.transferTo(tempfile);
//
//            List<ExcelFile> excelBooks = fromExcel(tempfile, ExcelFile.class);
//            for (ExcelFile excelBook : excelBooks) {
//                BooksDto dto = getBooksDto(excelBook);
//
//                Book bookentity = bookConverter.toEntity(dto);
//                bookRepo.save(bookentity);
//            }
//            tempfile.delete();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }



