package ru.nuykin.quizrestapi.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import ru.nuykin.quizrestapi.model.Game;
import ru.nuykin.quizrestapi.model.QuizAnswer;

@Repository
public interface GameRepository extends R2dbcRepository<Game, Long> {
}
