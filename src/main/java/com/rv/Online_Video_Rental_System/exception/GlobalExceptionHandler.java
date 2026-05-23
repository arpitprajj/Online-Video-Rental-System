package com.rv.Online_Video_Rental_System.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String>ResourceNotFoundExceptionhandler(ResourceNotFoundException ex){
        return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RentalException.class)
    public ResponseEntity<String>RentalExceptionHandler(RentalException ex){
        return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<String>EmailAlreadyExistExceptionHandler(EmailAlreadyExistException ex){
        return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String>MethodArgumentNotValidHandler(MethodArgumentNotValidException ex){
        return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
