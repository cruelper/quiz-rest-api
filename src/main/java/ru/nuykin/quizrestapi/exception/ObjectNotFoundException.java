package ru.nuykin.quizrestapi.exception;

public class ObjectNotFoundException extends RuntimeException {
    static StackWalker sw = StackWalker.getInstance();
    public ObjectNotFoundException(Class<?> clazz, Long id) {
        super("Object of class " + sw.getCallerClass() + " with id" + id + " not found.");
    }
}
