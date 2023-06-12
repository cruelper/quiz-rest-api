package ru.nuykin.quizrestapi.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.model.QuizQuestion;

@Repository
public interface QuizQuestionRepository extends R2dbcRepository<QuizQuestion, Long> {
    @Query(value = "SELECT * FROM quiz_question ORDER BY RANDOM() LIMIT 1")
    Mono<QuizQuestion> getRandomQuestion();
}
