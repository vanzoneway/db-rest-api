package by.vanzoneway.dbrestapi.api.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiExceptionResponse(

        HttpStatus status,

        String message,

        LocalDateTime timestamp) {

}