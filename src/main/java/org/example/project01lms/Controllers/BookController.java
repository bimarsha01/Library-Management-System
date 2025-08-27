package org.example.project01lms.Controllers;

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

@RestController
@RequestMapping("/books")
public class BookController extends BaseController{

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


    @PostMapping("/save")
    public ResponseEntity<ApiResponse> saveBook(@RequestBody BooksDto booksDto) {
        booksDto = bookService.save(booksDto);
        if (booksDto != null) {
            return ResponseEntity.ok(successResponse("Book saved successfully", Boolean.TRUE, booksDto));
        }else{
            return ResponseEntity.ok(successResponse("Book saved Failed", Boolean.FALSE, booksDto));
        }
    }
    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcelFile(@RequestParam ("file") MultipartFile file) {
        try{
         bookServiceImp.saveFromExcel(file);
         return ResponseEntity.ok("file uploaded successfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload Excel: " + e.getMessage());
        }
    }
}
