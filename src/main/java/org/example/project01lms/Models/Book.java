package org.example.project01lms.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Customers",uniqueConstraints = {
        @UniqueConstraint(columnNames = "customer_id" , name = "uk_customer_id" ),
        @UniqueConstraint(columnNames = "phone_no" , name = "uk_customer_phone"),
        @UniqueConstraint(columnNames = "library_id" , name = "uk_customer_lid")
})
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "books_id")
    private Long booksId;


}
