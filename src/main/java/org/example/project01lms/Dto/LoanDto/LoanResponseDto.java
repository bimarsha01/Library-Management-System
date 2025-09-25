package org.example.project01lms.Dto.LoanDto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.project01lms.ExceptionHandling.FieldErrorConstant;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanResponseDto {

    private String libraryId;

    private String bookName;

    private String authorName;

    private String isbnNumber;

    private LocalDate borrowDate;

    private LocalDate returnDate;

    private LocalDate dueDate;

    private Boolean status;

    private Double fine;
}

