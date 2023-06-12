package ru.nuykin.quizrestapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    ResponseEntity<?> objectNotFound (ObjectNotFoundException ex) {
        log.debug("Handling exception: " + ex);
        return ResponseEntity.notFound().build();
    }
}
