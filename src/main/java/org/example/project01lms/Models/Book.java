package org.example.project01lms.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Books",uniqueConstraints = {
        @UniqueConstraint(columnNames = "isbn_number" , name = "uk_customer_id" )
})
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "book_name" , nullable = false , length = 40)
    private String bookName;
    @Column(name = "publisher_name" , nullable = false , length = 100)
    private String publisherName;
    @Column(name = "isbn_number" , nullable = false , length = 100)
    private String isbnNumber;
    @Column(name = "author_name" , nullable = false , length = 100)
    private String authorName;
    @Column(name = "book_quantity" , nullable = false , length = 5)
    private Long bookQuantity;
    @Column(name = "available_copies" , nullable = false)
    private Long availableCopies;
    @Column(name = "genre"  , nullable = false , length = 20)
    private String genre;
    @Column(name = "language" , nullable = false , length = 30)
    private String language;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }


    }
}
