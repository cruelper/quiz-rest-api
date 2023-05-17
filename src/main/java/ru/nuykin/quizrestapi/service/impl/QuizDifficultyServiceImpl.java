package ru.nuykin.quizrestapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.model.QuizDifficulty;
import ru.nuykin.quizrestapi.repository.QuizDifficultyRepository;
import ru.nuykin.quizrestapi.service.QuizDifficultyService;

@Service
@RequiredArgsConstructor
public class QuizDifficultyServiceImpl implements QuizDifficultyService {
    private final QuizDifficultyRepository quizDifficultyRepository;


    @Override
    public Flux<QuizDifficulty> findAll() {
        return null;
    }

    @Override
    public Mono<QuizDifficulty> findById(int id) {
        return null;
    }

    @Override
    public Mono<QuizDifficulty> save(QuizDifficulty quizDifficulty) {
        return null;
    }

    @Override
    public Mono<QuizDifficulty> update(QuizDifficulty quizDifficulty) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(int id) {
        return null;
    }
}
