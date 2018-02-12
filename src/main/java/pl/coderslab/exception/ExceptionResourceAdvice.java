package pl.coderslab.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionResourceAdvice {

    @ExceptionHandler({ BaseEntityNotFoundException.class })
    public ResponseEntity<ExceptionResponse> handleNoSuchObject(BaseEntityNotFoundException e) {
        return new ResponseEntity<>(new ExceptionResponse(RestApiErrorCode.ENTITY_NOT_FOUND.code(), e.getMessage()),
                RestApiErrorCode.ENTITY_NOT_FOUND.getStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        ExceptionResponse response = null;

        if (e instanceof DataIntegrityViolationException) {
            response = new ExceptionResponse(RestApiErrorCode.DUPLICATE.code(),
                    RestApiErrorCode.DUPLICATE.getDescription());
            return new ResponseEntity<>(response, RestApiErrorCode.DUPLICATE.getStatus());
        }

        return new ResponseEntity<>(new ExceptionResponse(RestApiErrorCode.UNKNOWN.code(), e.getMessage()),
                RestApiErrorCode.UNKNOWN.getStatus());
    }
}
