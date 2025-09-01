package org.example.project01lms.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.project01lms.ExceptionHandling.FieldErrorConstant;
import org.springframework.validation.FieldError;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long customerId;

    @NotNull(message = FieldErrorConstant.NOT_NULL)
    @NotEmpty(message = FieldErrorConstant.NOT_EMPTY)
    private String fullName;

    @NotNull(message = FieldErrorConstant.NOT_NULL)
    @NotEmpty(message = FieldErrorConstant.NOT_EMPTY)
    private String email;

    @NotNull(message = FieldErrorConstant.NOT_NULL)
    @NotEmpty(message = FieldErrorConstant.NOT_EMPTY)
    private String address;

    private String phoneNo;

    private Boolean isActive;

    private String libraryId;

}
