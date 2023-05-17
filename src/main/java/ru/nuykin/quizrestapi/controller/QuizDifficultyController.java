package ru.nuykin.quizrestapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.model.QuizDifficulty;
import ru.nuykin.quizrestapi.service.QuizDifficultyService;

@RestController
@RequestMapping(value = "/difficulty")
@RequiredArgsConstructor
public class QuizDifficultyController {
    private final QuizDifficultyService quizDifficultyService;

    @GetMapping
    public Flux<QuizDifficulty> getAll() {
        return quizDifficultyService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<QuizDifficulty> findById(@PathVariable Integer id) {
        return quizDifficultyService.findById(id);
    }

    @PostMapping
    public Mono<QuizDifficulty> create(@RequestBody QuizDifficulty quizCategory) {
        return quizDifficultyService.save(quizCategory);
    }

    @PatchMapping
    public Mono<QuizDifficulty> update(@RequestBody QuizDifficulty quizCategory) {
        return quizDifficultyService.save(quizCategory);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Integer id) {
        return quizDifficultyService.deleteById(id);
    }
}
