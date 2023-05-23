package ru.nuykin.quizrestapi.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.model.QuizAnswer;
import ru.nuykin.quizrestapi.model.QuizCategory;

public interface QuizAnswerService {
    public Flux<QuizAnswer> findAll();

    public Mono<QuizAnswer> findById(long id);
    public Flux<QuizAnswer> findByQuizQuestionId(long id);
    public Mono<QuizAnswer> findCorrectQuizAnswer(long questionId);

    public Mono<QuizAnswer> save(QuizAnswer quizAnswer);

    public Mono<QuizAnswer> update(long id, QuizAnswer quizAnswer);

    public Mono<Void> deleteById(long id);

    public Mono<Void> deleteByQuizQuestionId(long id);
}
