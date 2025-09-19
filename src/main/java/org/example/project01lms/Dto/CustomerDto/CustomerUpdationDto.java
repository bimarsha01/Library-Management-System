package org.example.project01lms.Dto.CustomerDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.project01lms.ExceptionHandling.FieldErrorConstant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdationDto {

    private String fullName;

    private String email;

    private String address;

    private String phoneNo;




}

