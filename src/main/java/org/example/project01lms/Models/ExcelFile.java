package org.example.project01lms.Models;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Excel File")
public class ExcelFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ExcelRow
    private int rowIndex;

    @ExcelCellName("bookName")
    private String bookName;
     @ExcelCellName("publisherName")
    private String publisherName;
     @ExcelCellName("isbnNumber")
    private String isbnNumber;
     @ExcelCellName("authorName")
    private String authorName;
     @ExcelCellName("BookQuantity")
    private String BookQuantity;
     @ExcelCellName("availableCopies")
    private Long availableCopies;
     @ExcelCellName("genre")
    private String genre;
     @ExcelCellName("language")
    private String language;
     @ExcelCellName("createdAt")
    private String createdAt;
@ExcelCellName("updatedAt")
    private String updatedAt;




}
