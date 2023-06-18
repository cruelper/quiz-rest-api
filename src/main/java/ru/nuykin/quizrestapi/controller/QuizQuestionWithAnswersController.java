package ru.nuykin.quizrestapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.dto.QuizQuestionWithAnswersDto;
import ru.nuykin.quizrestapi.dto.request.QuizCheckQuestionAnswerRequest;
import ru.nuykin.quizrestapi.dto.response.QuizCheckQuestionAnswerResponse;
import ru.nuykin.quizrestapi.jserviceio.FeignClient;
import ru.nuykin.quizrestapi.jserviceio.JServiceIOService;
import ru.nuykin.quizrestapi.mapper.QuizAnswerMapper;
import ru.nuykin.quizrestapi.mapper.QuizQuestionWithAnswersMapper;
import ru.nuykin.quizrestapi.model.QuizAnswer;
import ru.nuykin.quizrestapi.service.QuizQuestionWithAnswersService;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/question-with-answers")
@RequiredArgsConstructor
public class QuizQuestionWithAnswersController {
    private final QuizQuestionWithAnswersService quizQuestionWithAnswersService;
    private final QuizQuestionWithAnswersMapper quizQuestionWithAnswersMapper;
    private final QuizAnswerMapper quizAnswerMapper;
    private final JServiceIOService jServiceIOService;

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

    @GetMapping("/random")
    @ResponseStatus(HttpStatus.OK)
    public Mono<QuizQuestionWithAnswersDto> findRandom(@RequestParam(required = true) Integer count) {
        if ((int)(Math.random()*100) % 2 == 0) {
            return quizQuestionWithAnswersService.findRandom()
                    .map(quizQuestionWithAnswersMapper::fromModelToDto);
        }
        return jServiceIOService.findRandom(1).last();
    }

    @PostMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    public Mono<QuizCheckQuestionAnswerResponse> checkAnswer(@RequestBody QuizCheckQuestionAnswerRequest sendAnswer) {
        return quizQuestionWithAnswersService.checkAnswer(QuizAnswer.builder()
                .quizQuestionId(sendAnswer.getQuestionId())
                .id(sendAnswer.getQuizAnswer().getId())
                .build()
        ).map(correctQuizAnswer -> QuizCheckQuestionAnswerResponse.builder()
                .question_id(sendAnswer.getQuestionId())
                .isCorrect(Objects.equals(correctQuizAnswer.getId(), sendAnswer.getQuizAnswer().getId()))
                .yourQuizAnswer(sendAnswer.getQuizAnswer())
                .correctQuizAnswer(quizAnswerMapper.fromModelToDto(correctQuizAnswer))
                .build()
        );
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
    public Mono<QuizQuestionWithAnswersDto> update(@PathVariable int id, @RequestBody QuizQuestionWithAnswersDto quizQuestionWithAnswersDto) throws ExecutionException, InterruptedException {
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
