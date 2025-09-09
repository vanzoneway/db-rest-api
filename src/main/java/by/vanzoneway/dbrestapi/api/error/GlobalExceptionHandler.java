package by.vanzoneway.dbrestapi.api.error;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle400(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ErrorResponse errorResponse = new ErrorResponse(
                "Validation failed",
                errors,
                HttpStatus.BAD_REQUEST.value()
        );

        log.warn("Validation error: {}", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handle400(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .toList();

        ErrorResponse errorResponse = new ErrorResponse(
                "Constraint violation",
                errors,
                HttpStatus.BAD_REQUEST.value()
        );

        log.warn("Constraint violation: {}", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle404(EntityNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                null,
                HttpStatus.NOT_FOUND.value()
        );

        log.warn("Entity not found: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handle409(DataIntegrityViolationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "Data integrity violation. Please check your input data.",
                null,
                HttpStatus.CONFLICT.value()
        );

        log.warn("Data integrity violation: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle500(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                "Internal server error",
                null,
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        log.error("Internal server error: ", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @Getter
    @AllArgsConstructor
    public static class ErrorResponse {
        private String message;
        private List<String> details;
        private int status;
        private final LocalDateTime timestamp = LocalDateTime.now();

        public ErrorResponse(String message, int status) {
            this.message = message;
            this.details = null;
            this.status = status;
        }
    }
}