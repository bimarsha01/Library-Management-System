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
public class LoanCreationDto {


    private String libraryId;

    @NotBlank(message = FieldErrorConstant.NOT_BLANK)
    private String bookName;

    @NotBlank(message = FieldErrorConstant.NOT_BLANK)
    private String authorName;

    private String isbnNumber;

    @PastOrPresent(message = FieldErrorConstant.PAST_PRESENT)
    private LocalDate borrowDate;

    @Future(message = FieldErrorConstant.FUTURE)
    private LocalDate dueDate;

}

