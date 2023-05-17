package ru.nuykin.quizrestapi.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.model.QuizAnswer;
import ru.nuykin.quizrestapi.model.QuizCategory;

public interface QuizAnswerService {
    public Flux<QuizAnswer> findAll();

    public Mono<QuizAnswer> findById(int id);

    public Mono<QuizAnswer> save(QuizAnswer quizAnswer);

    public Mono<QuizAnswer> update(QuizAnswer quizAnswer);

    public Mono<Void> deleteById(int id);
}
