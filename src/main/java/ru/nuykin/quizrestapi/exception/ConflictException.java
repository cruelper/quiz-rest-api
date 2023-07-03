package ru.nuykin.quizrestapi.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends ApiException{
    public ConflictException(String msg) {
        super(msg, HttpStatus.CONFLICT);
    }
}
