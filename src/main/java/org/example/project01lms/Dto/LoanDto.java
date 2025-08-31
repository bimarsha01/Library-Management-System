package org.example.project01lms.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {

    private Long id;

    private String libraryId;

    public String bookName;

    public String authorName;

    private String isbnNumber;

    private LocalDate borrowDate;

    private LocalDate returnDate;

    private LocalDate dueDate;

    private Boolean status;

    private Double fine;
}
