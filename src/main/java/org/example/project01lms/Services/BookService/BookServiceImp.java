package org.example.project01lms.Services.BookService;

import jakarta.transaction.Transactional;
import org.example.project01lms.Converter.BookConverter;
import org.example.project01lms.Dto.BooksDto;
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
        Book book = bookConverter.toEntity(booksDto);
        book = bookRepo.save(book);
        return bookConverter.toDto(book);
    }

    @Transactional
    @Override
    public BooksDto update(BooksDto booksDto) {
        Book book = bookRepo.findByIsbnNumber(booksDto.getIsbnNumber())
                .orElseThrow(() -> new NotAvailableException("NOT_AVAILABLE", "Book with isbnNumber" + booksDto.getIsbnNumber() + " is not available"));

        bookMapper.updateBookFromDto(booksDto, book);
        bookRepo.save(book);
        return bookMapper.toDto(book);
    }

    @Override
    public List<BooksDto> findall() {
        List<Book> bookList = bookRepo.findAll();
        return bookConverter.toDtoList(bookList);

    }

    @Override
    public BooksDto findById(BooksDto booksDto) {
        return null;
    }

    public BooksDto getByIsbnNo(String isbnNumber) {
        Book book = bookRepo.findByIsbnNumber(isbnNumber)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return bookConverter.toDto(book);
    }

    public void saveFromExcel(MultipartFile file) {
        try {
            File tempfile = File.createTempFile("books_data", ".xlsx");
            file.transferTo(tempfile);

            List<ExcelFile> excelBooks = fromExcel(tempfile, ExcelFile.class);
            for (ExcelFile excelBook : excelBooks) {
                BooksDto dto = new BooksDto();
                dto.setBookName(excelBook.getBookName());
                dto.setAuthorName(excelBook.getAuthorName());
                dto.setPublisherName(excelBook.getPublisherName());
                dto.setIsbnNumber(excelBook.getIsbnNumber());
                dto.setBookQuantity(Long.parseLong(excelBook.getBookQuantity()));
                dto.setAvailableCopies(excelBook.getAvailableCopies());
                dto.setGenre(excelBook.getGenre());
                dto.setLanguage(excelBook.getLanguage());

                Book bookentity = bookConverter.toEntity(dto);
                bookRepo.save(bookentity);
            }
            tempfile.delete();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
