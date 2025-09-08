package by.vanzoneway.dbrestapi.api.error;

import by.vanzoneway.dbrestapi.core.exception.NotAllowedDatabaseOperationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({NotAllowedDatabaseOperationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptionResponse handle400(final Exception e) {
        return new ApiExceptionResponse(
                HttpStatus.BAD_REQUEST,
                e.getMessage(),
                LocalDateTime.now());
    }

//    @ExceptionHandler()
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ApiExceptionResponse handle404(final Exception e) {
//        return new ApiExceptionResponse(
//                HttpStatus.NOT_FOUND,
//                e.getMessage(),
//                LocalDateTime.now());
//    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiExceptionResponse handle500(final Exception e) {
        log.error(e.getMessage(), e);
        return new ApiExceptionResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage(),
                LocalDateTime.now());
    }
}