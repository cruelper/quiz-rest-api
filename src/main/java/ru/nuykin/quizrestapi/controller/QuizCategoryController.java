package ru.nuykin.quizrestapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.model.QuizCategory;
import ru.nuykin.quizrestapi.service.QuizCategoryService;

@RestController
@RequestMapping(value = "/category")
@RequiredArgsConstructor
public class QuizCategoryController {
    private final QuizCategoryService quizCategoryService;

    @GetMapping
    public Flux<QuizCategory> getAll() {
        return quizCategoryService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<QuizCategory> findById(@PathVariable Integer id) {
        return quizCategoryService.findById(id);
    }

    @PostMapping
    public Mono<QuizCategory> create(@RequestBody QuizCategory quizCategory) {
        return quizCategoryService.save(quizCategory);
    }

    @PatchMapping
    public Mono<QuizCategory> update(@RequestBody QuizCategory quizCategory) {
        return quizCategoryService.save(quizCategory);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Integer id) {
        return quizCategoryService.deleteById(id);
    }

}
