package ru.nuykin.quizrestapi.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.model.Question;

public interface QuestionService {
    public Flux<Question> findAll();

    public Mono<Question> findById(long id);
    public Mono<Question> findRandom();

    public Mono<Question> save(Question question);

    public Mono<Question> update(long id, Question question);

    public Mono<Void> deleteById(long id);
}
