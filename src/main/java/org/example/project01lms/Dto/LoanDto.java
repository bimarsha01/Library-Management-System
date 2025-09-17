package org.example.project01lms.Dto;

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
public class LoanDto {

    private Long id;

    private String libraryId;

    @NotBlank(message = FieldErrorConstant.NOT_BLANK)
    private String bookName;

    @NotBlank(message = FieldErrorConstant.NOT_BLANK)
    private String authorName;

    private String isbnNumber;

    @PastOrPresent(message = FieldErrorConstant.PAST_PRESENT)
    private LocalDate borrowDate;

    @FutureOrPresent(message = FieldErrorConstant.PRESENT_FUTURE)
    private LocalDate returnDate;

    @Future(message = FieldErrorConstant.FUTURE)
    private LocalDate dueDate;

    @NotNull(message = FieldErrorConstant.NOT_NULL)
    private Boolean status;

    @PositiveOrZero(message = FieldErrorConstant.IS_POSITIVE)
    private Double fine;
}
