package ru.nuykin.quizrestapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.mapper.QuizCategoryMapper;
import ru.nuykin.quizrestapi.model.QuizCategory;
import ru.nuykin.quizrestapi.repository.QuizCategoryRepository;
import ru.nuykin.quizrestapi.service.QuizCategoryService;

@Service
@RequiredArgsConstructor
public class QuizCategoryServiceImpl implements QuizCategoryService {

    private final QuizCategoryRepository quizCategoryRepository;
    private final QuizCategoryMapper quizCategoryMapper;

    @Override
    public Flux<QuizCategory> findAll() {
        return quizCategoryRepository.findAll();
    }

    @Override
    public Mono<QuizCategory> findById(int id) {
        return quizCategoryRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException()));
    }

    @Override
    public Mono<QuizCategory> save(QuizCategory quizCategory) {
        return quizCategoryRepository.save(quizCategory);
    }

    @Override
    public Mono<QuizCategory> update(int id, QuizCategory quizCategory) {
        return quizCategoryRepository.findById(id)
                .flatMap(oldQuizCategory -> quizCategoryRepository.save(
                                quizCategoryMapper.updateEntity(quizCategory, oldQuizCategory)
                        )
                );
    }

    @Override
    public Mono<Void> deleteById(int id) {
        return quizCategoryRepository.deleteById(id);
    }
}
