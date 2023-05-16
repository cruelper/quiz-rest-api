package ru.nuykin.quizrestapi.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import ru.nuykin.quizrestapi.model.QuizAnswer;

@Repository
public interface QuizAnswerRepository extends R2dbcRepository<QuizAnswer, Long> {
}
