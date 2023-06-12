package ru.nuykin.quizrestapi.service;

import org.springframework.data.util.Pair;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.model.QuizAnswer;
import ru.nuykin.quizrestapi.model.QuizQuestionWithAnswers;

import java.util.concurrent.ExecutionException;

public interface QuizQuestionWithAnswersService {
    public Flux<QuizQuestionWithAnswers> findAll();
    public Mono<QuizQuestionWithAnswers> findById(long id);
    public Mono<QuizQuestionWithAnswers> findRandom();
    public Mono<QuizAnswer> checkAnswer(QuizAnswer sendAnswer);
    public Mono<QuizQuestionWithAnswers> save(QuizQuestionWithAnswers quizQuestionWithAnswers) throws ExecutionException, InterruptedException;
    public Mono<QuizQuestionWithAnswers> update(long id, QuizQuestionWithAnswers quizQuestionWithAnswers) throws ExecutionException, InterruptedException;
    public Mono<Void> deleteById(long id);
}
