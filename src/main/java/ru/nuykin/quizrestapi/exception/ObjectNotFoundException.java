package ru.nuykin.quizrestapi.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(Class<?> clazz, Long id) {
        super("Object of class " + clazz.getName() + " with id" + id + " not found.");
    }
}
