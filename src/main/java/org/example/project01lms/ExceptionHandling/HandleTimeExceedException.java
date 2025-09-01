package org.example.project01lms.ExceptionHandling;

public class HandleTimeExceedException extends  RuntimeException{
private final String errorCode;
    public HandleTimeExceedException( String message, String errorCode){
        super(message );
        this.errorCode = errorCode;
    }
    public String getErrorCode() {
        return errorCode;
    }
}
