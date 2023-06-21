package ru.nuykin.quizrestapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.mapper.QuizCategoryMapper;
import ru.nuykin.quizrestapi.model.Category;
import ru.nuykin.quizrestapi.repository.db.CategoryRepository;
import ru.nuykin.quizrestapi.service.CategoryService;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final QuizCategoryMapper quizCategoryMapper;

    @Override
    public Flux<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Mono<Category> findById(int id) {
        return categoryRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException()));
    }

    @Override
    public Mono<Category> save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Mono<Category> update(int id, Category category) {
        return categoryRepository.findById(id)
                .flatMap(oldQuizCategory -> categoryRepository.save(
                                quizCategoryMapper.updateEntity(category, oldQuizCategory)
                        )
                );
    }

    @Override
    public Mono<Void> deleteById(int id) {
        return categoryRepository.deleteById(id);
    }
}
