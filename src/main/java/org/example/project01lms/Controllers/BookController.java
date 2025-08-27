package org.example.project01lms.Controllers;

import org.example.project01lms.Dto.BooksDto;
import org.example.project01lms.Response.ApiResponse;
import org.example.project01lms.Services.BookService.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController extends BaseController{

    public final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
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
}
