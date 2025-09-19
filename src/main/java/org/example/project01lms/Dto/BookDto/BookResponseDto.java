package org.example.project01lms.Dto.BookDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.project01lms.ExceptionHandling.FieldErrorConstant;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

public class BookResponseDto {

    private Long booksId;

    private String bookName;

    private String publisherName;

    private String isbnNumber;

    private String authorName;

    private Long BookQuantity;

    private Long availableCopies;

    private String genre;

    private String language;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
