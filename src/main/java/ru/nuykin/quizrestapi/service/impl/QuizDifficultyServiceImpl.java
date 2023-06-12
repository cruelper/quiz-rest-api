package ru.nuykin.quizrestapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.mapper.QuizDifficultyMapper;
import ru.nuykin.quizrestapi.model.QuizDifficulty;
import ru.nuykin.quizrestapi.repository.QuizDifficultyRepository;
import ru.nuykin.quizrestapi.service.QuizDifficultyService;

@Service
@RequiredArgsConstructor
public class QuizDifficultyServiceImpl implements QuizDifficultyService {

    private final QuizDifficultyRepository quizDifficultyRepository;
    private final QuizDifficultyMapper quizDifficultyMapper;

    @Override
    public Flux<QuizDifficulty> findAll() {
        return quizDifficultyRepository.findAll();
    }

    @Override
    public Mono<QuizDifficulty> findById(int id) {
        return quizDifficultyRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException()));
    }

    @Override
    public Mono<QuizDifficulty> save(QuizDifficulty quizDifficulty) {
        return quizDifficultyRepository.save(quizDifficulty);
    }

    @Override
    public Mono<QuizDifficulty> update(int id, QuizDifficulty quizDifficulty) {
        return quizDifficultyRepository.findById(id)
                .flatMap(oldQuizDifficulty -> quizDifficultyRepository.save(
                                quizDifficultyMapper.updateEntity(quizDifficulty, oldQuizDifficulty)
                        )
                );
    }

    @Override
    public Mono<Void> deleteById(int id) {
        return quizDifficultyRepository.deleteById(id);
    }
}