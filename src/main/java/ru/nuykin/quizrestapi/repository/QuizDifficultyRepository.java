package ru.nuykin.quizrestapi.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import ru.nuykin.quizrestapi.model.QuizDifficulty;

@Repository
public interface QuizDifficultyRepository extends R2dbcRepository<QuizDifficulty, Integer> {
}
