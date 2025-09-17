package org.example.project01lms.Controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.project01lms.Dto.BooksDto;
import org.example.project01lms.Repo.CustomerRepo;
import org.example.project01lms.Response.ApiResponse;
import org.example.project01lms.Services.BookService.BookService;
import org.example.project01lms.Services.BookService.BookServiceImp;
import org.example.project01lms.Services.CustomerServices.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController extends BaseController {

    public final BookService bookService;
    private final CustomerRepo customerRepo;
    private final CustomerService customerService;
    private final BookServiceImp bookServiceImp;

    public BookController(BookService bookService, CustomerRepo customerRepo, CustomerService customerService, BookServiceImp bookServiceImp) {
        this.bookService = bookService;
        this.customerRepo = customerRepo;
        this.customerService = customerService;
        this.bookServiceImp = bookServiceImp;
    }


    @PostMapping("/add")
    @Transactional
    public ResponseEntity<ApiResponse> saveBook( @Valid @RequestBody BooksDto booksDto) {
        log.info("Saving Book with details");
        booksDto = bookService.save(booksDto);
        if (booksDto != null) {
            log.info("Book has been saved");
            return ResponseEntity.ok(successResponse("Book saved successfully", Boolean.TRUE, booksDto));
        } else {
            log.error("Book creation failed");
            return ResponseEntity.ok(successResponse("Book saved Failed", Boolean.FALSE, booksDto));
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            log.info("Book uploading in process");
            bookServiceImp.saveFromExcel(file);
            log.error("Book upload successful");
            return ResponseEntity.ok("file uploaded successfully");
        } catch (Exception e) {
            log.error("Book upload Failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload Excel: " + e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse> updateBook(@RequestBody BooksDto booksDto) {
        log.info("Book update in progress");
        booksDto = bookService.update(booksDto);
        if (booksDto != null) {
            log.info("Book updated Successfully");
            return ResponseEntity.ok(successResponse("Book saved successfully", Boolean.TRUE, booksDto));
        } else {
            log.error("Book update failed");
            return ResponseEntity.ok(successResponse("Book saved Failed", Boolean.FALSE, booksDto));
        }
    }

    @GetMapping("/get-by-isbnNumber/{isbnNumber}")
    public ResponseEntity<ApiResponse> getByIsbnNumber(@PathVariable String isbnNumber) {
        log.info("Getting the user info by Isbn number");
        BooksDto booksDto = bookService.getByIsbnNo(isbnNumber);
        if (booksDto != null) {
            log.info("Fetch using isbnNumber successful");
            return ResponseEntity.ok(successResponse("Book fetched using ISBN number", Boolean.TRUE, booksDto));
        } else {
            log.error("Fetch failed");
            return ResponseEntity.ok(successResponse("Book fetched using ISBN number failed", Boolean.FALSE, booksDto));
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<ApiResponse> findAll() {
        log.info("Fetching all the books");
        List<BooksDto> booksDto = bookService.findall();
        if (booksDto != null) {
            log.info("Fetched successful");
            return ResponseEntity.ok(successResponse("Book fetched successfully", Boolean.TRUE, booksDto));
        } else {
            log.error("Fetch Failed");
            return ResponseEntity.ok(successResponse("Book fetch Failed", Boolean.FALSE, booksDto));
        }
    }
}

