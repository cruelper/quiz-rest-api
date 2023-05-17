package ru.nuykin.quizrestapi.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.model.QuizDifficulty;
import ru.nuykin.quizrestapi.model.QuizQuestion;

public interface QuizQuestionService {
    public Flux<QuizQuestion> findAll();

    public Mono<QuizQuestion> findById(int id);

    public Mono<QuizQuestion> save(QuizQuestion quizQuestion);

    public Mono<QuizQuestion> update(QuizQuestion quizQuestion);

    public Mono<Void> deleteById(int id);
}
