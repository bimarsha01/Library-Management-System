package org.example.project01lms.ExceptionHandling;

import org.example.project01lms.Response.ApiError;
import org.example.project01lms.Response.ApiResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalException  extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        BindingResult bindingResult = (ex.getBindingResult());
        List<FieldError> errorList = bindingResult.getFieldErrors();
        List<String> messageList = new ArrayList<>();
        for (FieldError fieldError : errorList) {
            String message = fieldError.getField() + " should be " + fieldError.getDefaultMessage();
            messageList.add(message);
        }
        return new ResponseEntity<>(new ApiResponse("Data validation failed !!", false, messageList), BAD_REQUEST);
    }

    @ExceptionHandler(HandleDataException.class)
    public ResponseEntity<ApiResponse> handleResourceException(HandleDataException ex){
        ApiResponse error = new ApiResponse(ex.getMessage() , Boolean.FALSE , "gggg");
        return new ResponseEntity<>(error ,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HandleTimeExceedException.class)
    public ResponseEntity<ApiError> handleTimeExceedException(HandleTimeExceedException ex){
        ApiError error = new ApiError(ex.getMessage() , ex.getErrorCode() ,Boolean.FALSE );
        return new ResponseEntity<>(error , BAD_REQUEST);

    } @ExceptionHandler(NotAvailableException.class)
    public ResponseEntity<ApiError> notAvailableException(NotAvailableException ex){
        ApiError error = new ApiError(ex.getMessage() , ex.getErrorCode() ,Boolean.FALSE );
        return new ResponseEntity<>(error , BAD_REQUEST);
    }
    @ExceptionHandler(NotAvailableException.class)
    public ResponseEntity<ApiError> notFoundException(NotAvailableException ex){
        ApiError error = new ApiError("NOT_FOUND" , ex.getErrorCode() , Boolean.FALSE);
        return new ResponseEntity<>(error , BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrityViolation(DataIntegrityViolationException err){
        ConstraintViolationException cve  = (ConstraintViolationException) err.getCause();
        String key = cve.getConstraintName();
        assert key != null;
        String[] constraintMessage = key.split("\\.");
        String constraintName = constraintMessage[1];
        ApiError error = new ApiError(constraintName,  err.getMostSpecificCause().getMessage(), Boolean.FALSE);
        return new ResponseEntity<>(error ,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex){
        ApiResponse error = new ApiResponse("something went wrong" , Boolean.FALSE , "");
        return new ResponseEntity<>(error ,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
