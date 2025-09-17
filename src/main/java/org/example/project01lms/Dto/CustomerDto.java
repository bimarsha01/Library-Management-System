package org.example.project01lms.Dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.project01lms.ExceptionHandling.FieldErrorConstant;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long customerId;

  @NotBlank(message = FieldErrorConstant.NOT_BLANK)
    private String fullName;

    @NotBlank(message = FieldErrorConstant.NOT_BLANK)
    @Email(message = "Enter correct format")
    private String email;

    @NotBlank(message = FieldErrorConstant.NOT_BLANK)
    private String address;


    @NotNull(message = FieldErrorConstant.NOT_NULL)
    @Pattern(regexp="\\d{10}", message="Phone number must be 10 digits")
    private String phoneNo;

    private Boolean isActive;

    private String libraryId;

}
