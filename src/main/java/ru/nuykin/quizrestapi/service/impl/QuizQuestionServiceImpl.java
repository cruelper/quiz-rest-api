package ru.nuykin.quizrestapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.model.QuizQuestion;
import ru.nuykin.quizrestapi.service.QuizQuestionService;

@Service
@RequiredArgsConstructor
public class QuizQuestionServiceImpl implements QuizQuestionService {
    @Override
    public Flux<QuizQuestion> findAll() {
        return null;
    }

    @Override
    public Mono<QuizQuestion> findById(int id) {
        return null;
    }

    @Override
    public Mono<QuizQuestion> save(QuizQuestion quizQuestion) {
        return null;
    }

    @Override
    public Mono<QuizQuestion> update(QuizQuestion quizQuestion) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(int id) {
        return null;
    }
}
