package com.litora.bookreview.exception;

import com.litora.bookreview.dto.CustomHttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<CustomHttpResponse> handleException(BookNotFoundException exception) {
        String errMsg = exception.getMessage();
        CustomHttpResponse response = new CustomHttpResponse(null, Map.of("message", errMsg));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DeletionFailedException.class)
    public ResponseEntity<String> handleDeletionException(DeletionFailedException ex){
        String errMsg = ex.getMessage();
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(errMsg);
    }
}
