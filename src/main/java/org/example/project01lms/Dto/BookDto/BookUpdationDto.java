package org.example.project01lms.Dto.BookDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdationDto {

    private Long bookId;

    private Long bookQuantity;

    private Long availableCopies;
}
