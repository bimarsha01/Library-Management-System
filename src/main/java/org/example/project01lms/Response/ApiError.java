package org.example.project01lms.Response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiError {
    private String error;
    private String errorCode;
    private Boolean status;


}
