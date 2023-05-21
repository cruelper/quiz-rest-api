package ru.nuykin.quizrestapi.exception;

public class ApiException extends RuntimeException {
    private String errorCode;

    public ApiException(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
}
