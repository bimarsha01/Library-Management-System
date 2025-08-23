package org.example.project01lms.Dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long customerId;

    private String fullName;

    private String email;

    private String address;

    private String phoneNo;

    private Boolean isActive;

    private String libraryId;

}
