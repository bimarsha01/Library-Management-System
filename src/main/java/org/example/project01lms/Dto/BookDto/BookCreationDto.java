package org.example.project01lms.Dto.BookDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.project01lms.ExceptionHandling.FieldErrorConstant;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookCreationDto {

   @NotBlank(message = FieldErrorConstant.NOT_BLANK)
    private String bookName;

    @NotBlank(message = FieldErrorConstant.NOT_BLANK)
    private String publisherName;

    @NotBlank(message = FieldErrorConstant.NOT_BLANK)
    @Size(min = 13 , max = 13)
    private String isbnNumber;

    @NotBlank(message = FieldErrorConstant.NOT_BLANK)
    private String authorName;

    @NotNull(message = FieldErrorConstant.NOT_NULL)
    private Long bookQuantity;

    @NotNull(message = FieldErrorConstant.NOT_NULL)
    private Long availableCopies;

    @NotBlank(message = FieldErrorConstant.NOT_BLANK)
    private String genre;

    @NotBlank(message = FieldErrorConstant.NOT_BLANK)
    private String language;
}



//something is going wrong her with this code and needs to be rechecked.