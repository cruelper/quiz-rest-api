package ru.nuykin.quizrestapi.aspect;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Aspect
@ControllerAdvice
public class ResponseWrapperAspect {

    @Getter
    @Setter
    @Builder
    static private class ResponseWrapper {
        private String code;
        private String message;
        private Object result;
    }

    @Around("execution(* ru.nuykin.quizrestapi.controller.*.*(..))")
    public Object wrapResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();

        if (result instanceof Mono) {
            return ((Mono<?>) result).map(res -> ResponseWrapper.builder()
                    .code("200")
                    .message("OK")
                    .result(res)
                    .build()
            );
        }
        if (result instanceof Flux) {
            return ((Flux<?>) result).collectList()
                    .map(res -> ResponseWrapper.builder()
                            .code("200")
                            .message("OK")
                            .result(res)
                            .build()
                    )
                    .flux();
        }
        return Mono.just(ResponseWrapper.builder()
                .code("500")
                .message("Неожиданный ответ")
                .build()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public Object handleException(RuntimeException exception) {
        return new ResponseEntity<>(Mono.just(ResponseWrapper.builder()
                .code("500")
                .message(exception.getMessage())
                .build()
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
