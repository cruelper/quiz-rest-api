package ru.nuykin.quizrestapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.dto.QuestionDto;
import ru.nuykin.quizrestapi.dto.CheckQuestionAnswerDto;
import ru.nuykin.quizrestapi.dto.request.GameStartRequestDto;
import ru.nuykin.quizrestapi.dto.response.GameFinishResponseDto;
import ru.nuykin.quizrestapi.dto.response.GameStartResponseDto;
import ru.nuykin.quizrestapi.mapper.QuestionWithCategoryMapper;
import ru.nuykin.quizrestapi.security.UserPrincipal;
import ru.nuykin.quizrestapi.service.GameWithQuestionsService;

@RestController
@RequestMapping(value = "/game")
@RequiredArgsConstructor
public class GameController {
    private final GameWithQuestionsService gameWithQuestionsService;
    private final QuestionWithCategoryMapper questionWithCategoryMapper;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Mono<GameStartResponseDto> createGame(
            @RequestBody GameStartRequestDto gameStartDto,
            @AuthenticationPrincipal Mono<Authentication> principal
    ) {
        return principal.map(user -> ((UserPrincipal)user.getPrincipal()).getId())
                .flatMap(userId -> gameWithQuestionsService.startGame(userId, gameStartDto));
    }

    @GetMapping("/{game_id}/{question_number}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<QuestionDto> getQuestion(@PathVariable Long game_id, @PathVariable Integer question_number) {
        return gameWithQuestionsService.getQuestion(game_id, question_number)
                .map(questionWithCategoryMapper::fromModelToDto);
    }

    @PostMapping("/{game_id}/{question_number}/check")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CheckQuestionAnswerDto> checkQuestion(
            @PathVariable Long game_id,
            @PathVariable Integer question_number,
            @RequestBody CheckQuestionAnswerDto answer
    ) {
        return gameWithQuestionsService.checkQuestion(game_id, question_number, answer).log();
    }

    @PostMapping("/{game_id}/finish")
    @ResponseStatus(HttpStatus.OK)
    public Mono<GameFinishResponseDto> finishGame(@PathVariable Long game_id) {
        return gameWithQuestionsService.finishGame(game_id);
    }

}
