package ru.nuykin.quizrestapi.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.model.QuizAnswer;

@Repository
public interface QuizAnswerRepository extends R2dbcRepository<QuizAnswer, Long> {
    Flux<QuizAnswer> findByQuizQuestionId(Long quizQuestionId);

    Mono<Void> deleteByQuizQuestionId(Long quizQuestionId);

    Mono<QuizAnswer> findByQuizQuestionIdAndIsCorrect(Long quizQuestionId, Boolean isCorrect);

}
