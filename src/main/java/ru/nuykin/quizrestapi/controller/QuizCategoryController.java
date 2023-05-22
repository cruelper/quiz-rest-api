package ru.nuykin.quizrestapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.dto.QuizCategoryDto;
import ru.nuykin.quizrestapi.mapper.QuizCategoryMapper;
import ru.nuykin.quizrestapi.service.QuizCategoryService;

@RestController
@RequestMapping(value = "/category")
@RequiredArgsConstructor
public class QuizCategoryController {
    private final QuizCategoryService quizCategoryService;
    private final QuizCategoryMapper quizCategoryMapper;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Flux<QuizCategoryDto> getAll() {
        return quizCategoryService.findAll().map(quizCategoryMapper::fromModelToDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<QuizCategoryDto> findById(@PathVariable Integer id) {
        return quizCategoryService.findById(id)
                .map(quizCategoryMapper::fromModelToDto);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<QuizCategoryDto> create(@RequestBody QuizCategoryDto quizCategoryDto) {
        return quizCategoryService.save(
                quizCategoryMapper.fromDtoToModel(quizCategoryDto)
        ).map(quizCategoryMapper::fromModelToDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<QuizCategoryDto> update(@PathVariable int id, @RequestBody QuizCategoryDto quizCategoryDto) {
        return quizCategoryService.update(
                id, quizCategoryMapper.fromDtoToModel(quizCategoryDto)
        ).map(quizCategoryMapper::fromModelToDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> delete(@PathVariable Integer id) {
        return quizCategoryService.deleteById(id);
    }

}
