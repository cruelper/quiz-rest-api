package ru.nuykin.quizrestapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.model.QuizCategory;
import ru.nuykin.quizrestapi.repository.QuizCategoryRepository;
import ru.nuykin.quizrestapi.service.QuizAnswerService;
import ru.nuykin.quizrestapi.service.QuizCategoryService;

@Service
@RequiredArgsConstructor
public class QuizCategoryServiceImpl implements QuizCategoryService {

    private final QuizCategoryRepository quizCategoryRepository;
    @Override
    public Flux<QuizCategory> findAll() {
        return quizCategoryRepository.findAll();
    }

    @Override
    public Mono<QuizCategory> findById(int id) {
        return quizCategoryRepository.findById(id);
    }

    @Override
    public Mono<QuizCategory> save(QuizCategory quizCategory) {
        return null;
    }

    @Override
    public Mono<QuizCategory> update(QuizCategory quizCategory) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(int id) {
        return null;
    }
}
