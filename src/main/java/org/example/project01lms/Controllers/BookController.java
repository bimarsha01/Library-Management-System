package org.example.project01lms.Controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.project01lms.Dto.BookDto.BookCreationDto;
import org.example.project01lms.Dto.BookDto.BookResponseDto;
import org.example.project01lms.Dto.BookDto.BookUpdationDto;
import org.example.project01lms.Dto.BookDto.BooksDto;
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
    public ResponseEntity<ApiResponse> saveBook( @Valid @RequestBody BookCreationDto bookCreationDto) {
        log.info("Saving Book with details");
        BookResponseDto bookResponseDto  = bookService.save(bookCreationDto);
        if (bookResponseDto != null) {
            log.info("Book has been saved");
            return ResponseEntity.ok(successResponse("Book saved successfully", Boolean.TRUE, bookResponseDto));
        } else {
            log.error("Book creation failed");
            return ResponseEntity.ok(successResponse("Book saved Failed", Boolean.FALSE, bookResponseDto));
        }
    }

//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
//        try {
//            log.info("Book uploading in process");
//            bookServiceImp.saveFromExcel(file);
//            log.error("Book upload successful");
//            return ResponseEntity.ok("file uploaded successfully");
//        } catch (Exception e) {
//            log.error("Book upload Failed");
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to upload Excel: " + e.getMessage());
//        }
//    }

    @PostMapping("/update/{isbnNumber}")
    public ResponseEntity<ApiResponse> updateBook(
            @PathVariable String isbnNumber ,
            @RequestBody BookUpdationDto bookUpdationDto) {
        log.info("Book update in progress");
        BookResponseDto bookResponseDto = bookService.update(isbnNumber , bookUpdationDto);
        if (bookResponseDto != null) {
            log.info("Book updated Successfully");
            return ResponseEntity.ok(successResponse("Book saved successfully", Boolean.TRUE, bookResponseDto));
        } else {
            log.error("Book update failed");
            return ResponseEntity.ok(successResponse("Book saved Failed", Boolean.FALSE, null));
        }
    }

    @GetMapping("/get-by-isbnNumber/{isbnNumber}")
    public ResponseEntity<ApiResponse> getByIsbnNumber(@PathVariable String isbnNumber) {
        log.info("Getting the user info by Isbn number");
       BookResponseDto bookResponseDto = bookService.getByIsbnNo(isbnNumber);
        if (bookResponseDto != null) {
            log.info("Fetch using isbnNumber successful");
            return ResponseEntity.ok(successResponse("Book fetched using ISBN number", Boolean.TRUE, bookResponseDto));
        } else {
            log.error("Fetch failed");
            return ResponseEntity.ok(successResponse("Book fetched using ISBN number failed", Boolean.FALSE, null));
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<ApiResponse> findAll() {
        log.info("Fetching all the books");
        List<BookResponseDto> bookResponseDtos = bookService.findAll();
        if (bookResponseDtos != null) {
            log.info("Fetched successful");
            return ResponseEntity.ok(successResponse("Book fetched successfully", Boolean.TRUE, bookResponseDtos));
        } else {
            log.error("Fetch Failed");
            return ResponseEntity.ok(successResponse("Book fetch Failed", Boolean.FALSE, null));
        }
    }
}

