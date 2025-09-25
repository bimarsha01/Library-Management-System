package org.example.project01lms.Dto.BookDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.project01lms.ExceptionHandling.FieldErrorConstant;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {

    private Long bookId;           // matches entity
    private String bookName;
    private String publisherName;
    private String isbnNumber;
    private String authorName;
    private Long bookQuantity;     // matches entity
    private Long availableCopies;
    private String genre;
    private String language;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
