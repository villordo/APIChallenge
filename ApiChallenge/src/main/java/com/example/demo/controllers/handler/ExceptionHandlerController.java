package com.example.demo.controllers.handler;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.NotValidRolException;
import com.example.demo.models.dtos.ErrorDtoResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorDtoResponse> handleAlreadyExistsException(AlreadyExistsException exception){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDtoResponse.builder()
                .message(exception.getMessage())
                .exception("AlreadyExistsException")
                .build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDtoResponse> handleNotFoundException(NotFoundException exception){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDtoResponse.builder()
                .message(exception.getMessage())
                .exception("NotFoundException")
                .build());
    }

    @ExceptionHandler(NotValidRolException.class)
    public ResponseEntity<ErrorDtoResponse> handleNotValidRolException(NotValidRolException exception){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDtoResponse.builder()
                .message(exception.getMessage())
                .exception("NotValidRolException")
                .build());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorDtoResponse> handleExpiredJwtException(ExpiredJwtException exception){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorDtoResponse.builder()
                .message(exception.getMessage())
                .exception("ExpiredJwtException")
                .build());
    }
}
