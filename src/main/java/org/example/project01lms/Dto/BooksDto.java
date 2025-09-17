package org.example.project01lms.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
@NoArgsConstructor
@AllArgsConstructor
public class BooksDto {
    private Long booksId;
    @NotNull(message = FieldErrorConstant.NOT_NULL)
    @NotEmpty(message = FieldErrorConstant.NOT_EMPTY)
    private String bookName;

    @NotNull(message = FieldErrorConstant.NOT_NULL)
    @NotEmpty(message = FieldErrorConstant.NOT_EMPTY)
    private String publisherName;

    @NotNull(message = FieldErrorConstant.NOT_NULL)
    @NotEmpty(message = FieldErrorConstant.NOT_EMPTY)
    private String isbnNumber;

    @NotNull(message = FieldErrorConstant.NOT_NULL)
    @NotEmpty(message = FieldErrorConstant.NOT_EMPTY)
    private String authorName;

    @NotNull(message = FieldErrorConstant.NOT_NULL)
    @NotEmpty(message = FieldErrorConstant.NOT_EMPTY)
    private Long BookQuantity;

    @NotNull(message = FieldErrorConstant.NOT_NULL)
    @NotEmpty(message = FieldErrorConstant.NOT_EMPTY)
    private Long availableCopies;

    @NotNull(message = FieldErrorConstant.NOT_NULL)
    @NotEmpty(message = FieldErrorConstant.NOT_EMPTY)
    private String genre;

    private String language;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
