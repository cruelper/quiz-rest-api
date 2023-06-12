package ru.nuykin.quizrestapi.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.model.QuizCategory;
import ru.nuykin.quizrestapi.model.QuizDifficulty;

public interface QuizDifficultyService {
    public Flux<QuizDifficulty> findAll();

    public Mono<QuizDifficulty> findById(int id);

    public Mono<QuizDifficulty> save(QuizDifficulty quizDifficulty);

    public Mono<QuizDifficulty> update(int id, QuizDifficulty quizDifficulty);

    public Mono<Void> deleteById(int id);
}
