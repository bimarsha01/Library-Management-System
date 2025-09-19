package org.example.project01lms.Dto.CustomerDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDto {

    private Long customerId;

    private String fullName;

    private String email;

    private String address;

    private String phoneNo;

    private Boolean isActive;

    private String libraryId;

}
