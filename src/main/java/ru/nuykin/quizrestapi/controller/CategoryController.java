package ru.nuykin.quizrestapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.dto.CategoryDto;
import ru.nuykin.quizrestapi.mapper.QuizCategoryMapper;
import ru.nuykin.quizrestapi.service.CategoryService;

@RestController
@RequestMapping(value = "/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final QuizCategoryMapper quizCategoryMapper;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Flux<CategoryDto> getAll() {
        return categoryService.findAll().map(quizCategoryMapper::fromModelToDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CategoryDto> findById(@PathVariable Integer id) {
        return categoryService.findById(id)
                .map(quizCategoryMapper::fromModelToDto);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CategoryDto> create(@RequestBody CategoryDto categoryDto) {
        return categoryService.save(
                quizCategoryMapper.fromDtoToModel(categoryDto)
        ).map(quizCategoryMapper::fromModelToDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CategoryDto> update(@PathVariable int id, @RequestBody CategoryDto categoryDto) {
        return categoryService.update(
                id, quizCategoryMapper.fromDtoToModel(categoryDto)
        ).map(quizCategoryMapper::fromModelToDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> delete(@PathVariable Integer id) {
        return categoryService.deleteById(id);
    }

}
