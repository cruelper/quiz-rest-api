package ru.nuykin.quizrestapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.dto.GameStartDto;
import ru.nuykin.quizrestapi.dto.QuizQuestionWithAnswersDto;
import ru.nuykin.quizrestapi.dto.request.QuizCheckQuestionAnswerRequest;
import ru.nuykin.quizrestapi.dto.response.AuthResponseDto;
import ru.nuykin.quizrestapi.dto.response.GameFinishResponseDto;
import ru.nuykin.quizrestapi.dto.response.QuizCheckQuestionAnswerResponse;

@RestController
@RequestMapping(value = "/game")
@RequiredArgsConstructor
public class GameController {

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Mono<GameStartDto> createGame(@RequestBody GameStartDto gameStartDto) {
//        Тело запроса должно включать в себя количество вопросов, которые будут в наборе для игры,
//                а также ограничения по сложности вопросов - максимальная и минимальная сложность.
//                Опционально, могут быть указаны категории.
//                В ответе должен прийти уникальный идентификатор игры и количество вопросов,
//                сгенерированных для нее.
        return Mono.just(new GameStartDto());
    }

    @GetMapping("/{game_id}/{question_number}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<QuizQuestionWithAnswersDto> getQuestion(@PathVariable String game_id, @PathVariable String question_number) {
//        Получение очередного вопроса игры. game_id - идентификатор игры,
//        {question_number} - порядковый номер вопроса в игре.
        return Mono.just(new QuizQuestionWithAnswersDto());
    }

    @PostMapping("/{game_id}/{question_number}/check")
    @ResponseStatus(HttpStatus.OK)
    public Mono<QuizCheckQuestionAnswerResponse> checkQuestion(
            @PathVariable String game_id,
            @PathVariable String question_number,
            @RequestBody QuizCheckQuestionAnswerRequest answer
    ) {
//        Проверка ответа игрока на вопрос. В ответе метода API должен прийти признак
//        правильного или неправильного ответа и текст правильного ответа
        return Mono.just(new QuizCheckQuestionAnswerResponse());
    }

    @PostMapping("/{game_id}/finish")
    @ResponseStatus(HttpStatus.OK)
    public Mono<GameFinishResponseDto> finishGame(@PathVariable String game_id) {
//        Метод завершения игры, в ответе должна прийти результаты игры - список вопросов игры,
//        для каждого из которых указано, правильно ли на него ответил игрок.

        return Mono.just(new GameFinishResponseDto());
    }

}
