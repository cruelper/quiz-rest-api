package ru.nuykin.quizrestapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.dto.QuizQuestionWithAnswersDto;
import ru.nuykin.quizrestapi.mapper.QuizQuestionWithAnswersMapper;
import ru.nuykin.quizrestapi.service.QuizQuestionWithAnswersService;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/question-with-answers")
@RequiredArgsConstructor
public class QuizQuestionWithAnswersController {
    private final QuizQuestionWithAnswersService quizQuestionWithAnswersService;
    private final QuizQuestionWithAnswersMapper quizQuestionWithAnswersMapper;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Flux<QuizQuestionWithAnswersDto> getAll() {
        return quizQuestionWithAnswersService.findAll().map(quizQuestionWithAnswersMapper::fromModelToDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<QuizQuestionWithAnswersDto> findById(@PathVariable Integer id) {
        return quizQuestionWithAnswersService.findById(id)
                .map(quizQuestionWithAnswersMapper::fromModelToDto);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<QuizQuestionWithAnswersDto> create(@RequestBody QuizQuestionWithAnswersDto quizQuestionWithAnswersDto) throws ExecutionException, InterruptedException {
        return quizQuestionWithAnswersService.save(
                quizQuestionWithAnswersMapper.fromDtoToModel(quizQuestionWithAnswersDto)
        ).map(quizQuestionWithAnswersMapper::fromModelToDto);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<QuizQuestionWithAnswersDto> update(@PathVariable int id, @RequestBody QuizQuestionWithAnswersDto quizQuestionWithAnswersDto) {
        return quizQuestionWithAnswersService.update(
                id, quizQuestionWithAnswersMapper.fromDtoToModel(quizQuestionWithAnswersDto)
        ).map(quizQuestionWithAnswersMapper::fromModelToDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> delete(@PathVariable Integer id) {
        return quizQuestionWithAnswersService.deleteById(id);
    }

}
