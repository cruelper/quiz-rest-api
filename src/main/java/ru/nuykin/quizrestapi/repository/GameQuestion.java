package ru.nuykin.quizrestapi.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameQuestion extends R2dbcRepository<ru.nuykin.quizrestapi.model.GameQuestion, Long> {
}
