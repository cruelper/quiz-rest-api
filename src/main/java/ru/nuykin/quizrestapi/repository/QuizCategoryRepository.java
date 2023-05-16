package ru.nuykin.quizrestapi.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import ru.nuykin.quizrestapi.model.QuizCategory;

@Repository
public interface QuizCategoryRepository extends R2dbcRepository<QuizCategory, Integer> {
}
