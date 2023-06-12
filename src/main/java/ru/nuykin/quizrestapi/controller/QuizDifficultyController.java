package ru.nuykin.quizrestapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.dto.QuizDifficultyDto;
import ru.nuykin.quizrestapi.mapper.QuizDifficultyMapper;
import ru.nuykin.quizrestapi.model.QuizDifficulty;
import ru.nuykin.quizrestapi.service.QuizDifficultyService;

@RestController
@RequestMapping("/difficulty")
@RequiredArgsConstructor
public class QuizDifficultyController {
    private final QuizDifficultyService quizDifficultyService;
    private final QuizDifficultyMapper quizDifficultyMapper;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Flux<QuizDifficultyDto> getAll() {
        return quizDifficultyService.findAll().map(quizDifficultyMapper::fromModelToDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<QuizDifficultyDto> findById(@PathVariable Integer id) {
        return quizDifficultyService.findById(id)
                .map(quizDifficultyMapper::fromModelToDto);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<QuizDifficultyDto> create(@RequestBody QuizDifficultyDto quizDifficultyDto) {
        return quizDifficultyService.save(
                quizDifficultyMapper.fromDtoToModel(quizDifficultyDto)
        ).map(quizDifficultyMapper::fromModelToDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<QuizDifficultyDto> update(@PathVariable int id, @RequestBody QuizDifficultyDto quizDifficultyDto) {
        return quizDifficultyService.update(
                id, quizDifficultyMapper.fromDtoToModel(quizDifficultyDto)
        ).map(quizDifficultyMapper::fromModelToDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> delete(@PathVariable Integer id) {
        return quizDifficultyService.deleteById(id);
    }

}
