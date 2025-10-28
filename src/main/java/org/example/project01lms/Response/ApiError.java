package org.example.project01lms.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {
    private String error;
    private String errorCode;
    private Boolean status;


}
