package ru.nuykin.quizrestapi.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import ru.nuykin.quizrestapi.model.QuizQuestion;

@Repository
public interface QuizQuestionRepository extends R2dbcRepository<QuizQuestion, Long> {
}
