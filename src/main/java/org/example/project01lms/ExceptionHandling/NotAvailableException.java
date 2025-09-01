package org.example.project01lms.ExceptionHandling;

public class NotAvailableException extends  RuntimeException{

    private final String errorCode;

    public NotAvailableException(String errorCode, String message){
        super(message);
        this.errorCode = errorCode;

    }
        public String  getErrorCode(){
            return errorCode;
        }
}
