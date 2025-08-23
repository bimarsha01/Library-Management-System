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
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "customer_id")
    private Long customerId;

        @Column(name = "full_name" , length = 100 , nullable = false)
    private String fullName;
        @Column(name = "email" , length = 100 , nullable = false)
    private String email;
        @Column(name = "address" , length = 100 , nullable = false)
    private String address;
        @Column(name = "phone_no" , length = 10 , nullable = false)
    private String phoneNo;
        @Column(name = "is_active"  , nullable = false)
    private Boolean isActive;
        @Column(name = "library_id" , length = 6)
    private String libraryId;
}
