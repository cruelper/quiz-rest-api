package ru.nuykin.quizrestapi.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.model.QuizCategory;

public interface QuizCategoryService {
    public Flux<QuizCategory> findAll();

    public Mono<QuizCategory> findById(int id);

    public Mono<QuizCategory> save(QuizCategory quizCategory);

    public Mono<QuizCategory> update(QuizCategory quizCategory);

    public Mono<Void> deleteById(int id);
}
