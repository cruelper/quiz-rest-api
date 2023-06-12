package ru.nuykin.quizrestapi.exception;

public class AuthException extends ApiException{

    public AuthException(String msg, String errorCode) {
        super(msg, errorCode);
    }
}
