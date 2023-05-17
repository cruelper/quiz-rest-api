package ru.nuykin.quizrestapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.model.QuizAnswer;
import ru.nuykin.quizrestapi.repository.QuizAnswerRepository;
import ru.nuykin.quizrestapi.service.QuizAnswerService;

@Service
@RequiredArgsConstructor
public class QuizAnswerServiceImpl implements QuizAnswerService {

    private final QuizAnswerRepository quizAnswerRepository;

    @Override
    public Flux<QuizAnswer> findAll() {
        return null;
    }

    @Override
    public Mono<QuizAnswer> findById(int id) {
        return null;
    }

    @Override
    public Mono<QuizAnswer> save(QuizAnswer quizCategory) {
        return null;
    }

    @Override
    public Mono<QuizAnswer> update(QuizAnswer quizCategory) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(int id) {
        return null;
    }
}
