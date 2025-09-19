package org.example.project01lms.Services.BookService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.project01lms.Converter.BookConverter;
import org.example.project01lms.Dto.BookDto.BooksDto;
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
    public BooksDto save(BooksDto booksDto) {
        log.info("Creating book");
        Book book = bookConverter.toEntity(booksDto);
        book = bookRepo.save(book);
        log.info("Book added successfully with isbnNumber {} " , book.getIsbnNumber());
        return bookMapper.toDto(book);
    }

    @Transactional
    @Override
    public BooksDto update(BooksDto booksDto) {
        log.info("Updating Book details");
        Book book = bookRepo.findByIsbnNumber(booksDto.getIsbnNumber())
                .orElseThrow(() -> new NotAvailableException("NOT_AVAILABLE", "Book with isbnNumber" + booksDto.getIsbnNumber() + " is not available"));

        bookMapper.updateBookFromDto(booksDto, book);
        bookRepo.save(book);
        log.info("Book details had been updated of {}" , booksDto.getIsbnNumber());
        return bookMapper.toDto(book);
    }

    @Override
    public List<BooksDto> findAll() {
        log.info("Listing the Books");
        List<Book> bookList = bookRepo.findAll();
        log.info("Book list fetched");
        return bookMapper.toDtoList(bookList);
    }

    @Override
    public BooksDto findById(BooksDto booksDto) {
        Book book = bookRepo.findById(booksDto.getBooksId())
                .orElseThrow(() -> new NotAvailableException(
                        "NOT_FOUND", "Book with id " + booksDto.getBooksId() + " not found"));

        return bookMapper.toDto(book);
    }

    public BooksDto getByIsbnNo(String isbnNumber) {
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
