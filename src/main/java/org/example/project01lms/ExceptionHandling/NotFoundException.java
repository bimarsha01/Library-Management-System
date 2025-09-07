package org.example.project01lms.ExceptionHandling;

public class NotFoundException extends RuntimeException{

    private final String errorCode;

    public NotFoundException(String errorCode , String message) {
        super(message);
        this.errorCode = errorCode;
    }




}
