package org.example.project01lms.Controllers;

import org.example.project01lms.Dto.BooksDto;
import org.example.project01lms.Response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController extends BaseController{

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> saveBook(@ModelAttribute BooksDto booksDto){
        return ResponseEntity.ok(successResponse("Book saved successfully" , Boolean.TRUE , booksDto));
    }
}
