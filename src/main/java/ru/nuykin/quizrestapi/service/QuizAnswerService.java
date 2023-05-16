package ru.nuykin.quizrestapi.service;

import reactor.core.publisher.Flux;
import ru.nuykin.quizrestapi.model.QuizAnswer;

public interface QuizAnswerService {
    public Flux<QuizAnswer> findByQuestionId();

}
