package org.example.project01lms.ExceptionHandling;

import lombok.Getter;

@Getter
public class HandleTimeExceedException extends  RuntimeException{
private final String errorCode;
    public HandleTimeExceedException( String message, String errorCode){
        super(message );
        this.errorCode = errorCode;
    }
}
