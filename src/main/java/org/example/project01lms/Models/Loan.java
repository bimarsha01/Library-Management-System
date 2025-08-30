package org.example.project01lms.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Loan")
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long id;
  @ManyToOne
    @JoinColumn(name = "cust_lid" , referencedColumnName = "library_id" ,nullable = false  )
    private Customers customers;
  @ManyToOne
    @JoinColumn(name = "book_isbn" , referencedColumnName = "isbn_number" , nullable = false)
    private Book book;

  @Column(name = "borrow_date" , nullable = false )
  private LocalDate borrowDate;

@Column(name = "due_date" , nullable = false )
  private LocalDate dueDate;

@Column(name = "return_date" , nullable = false )
  private LocalDate returnDate;

@Column(name = "status")
    private Boolean status;


}