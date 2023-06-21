package ru.nuykin.quizrestapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.dto.CheckQuestionAnswerDto;
import ru.nuykin.quizrestapi.dto.request.GameStartRequestDto;
import ru.nuykin.quizrestapi.dto.response.GameFinishResponseDto;
import ru.nuykin.quizrestapi.dto.response.GameStartResponseDto;
import ru.nuykin.quizrestapi.mapper.QuizQuestionMapper;
import ru.nuykin.quizrestapi.model.Game;
import ru.nuykin.quizrestapi.model.GameQuestion;
import ru.nuykin.quizrestapi.model.Question;
import ru.nuykin.quizrestapi.model.QuestionSource;
import ru.nuykin.quizrestapi.repository.cache.QuestionRedisCache;
import ru.nuykin.quizrestapi.service.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class GameWithQuestionsServiceImpl implements GameWithQuestionsService {
    private final GameService gameService;
    private final GameQuestionService gameQuestionService;
    private final QuestionService questionService;
    private final JServiceService jServiceService;
    private final QuestionRedisCache questionRedisCache;
    private final QuizQuestionMapper quizQuestionMapper;

    @Override
    public Mono<GameStartResponseDto> startGame(Long userId, GameStartRequestDto gameStartRequestDto) {
        return gameService.save(
                Game.builder()
                        .userId(userId)
                        .startTime(LocalDateTime.now())
                        .build()
        ).map(game -> {
            IntStream.range(0, gameStartRequestDto.getQuestionCount()).forEach(i ->
                    getIdOfRandomQuestion().flatMap(
                            entry -> gameQuestionService.save(
                                    GameQuestion.builder()
                                            .gameId(game.getId())
                                            .number(i)
                                            .questionId(entry.getKey())
                                            .questionSource(entry.getValue())
                                            .isCorrectAnswer(false)
                                            .build()
                            )).subscribe()
            );

            return GameStartResponseDto.builder()
                    .questionCount(Long.valueOf(gameStartRequestDto.getQuestionCount()))
                    .gameId(game.getId())
                    .build();
        });
    }

    private Mono<Map.Entry<Long, QuestionSource>> getIdOfRandomQuestion() {
        if (Math.random() > 0.5) {
            return questionService.findRandom().map(question -> Map.entry(question.getId(), QuestionSource.DB));
        }
        return jServiceService.findRandom(1).last().map(question ->
                Map.entry(question.getId(), QuestionSource.JSERVICE
                ));
    }

    @Override
    public Mono<Question> getQuestion(Long gameId, Integer questionNumber) throws ExecutionException, InterruptedException {
        return gameQuestionService.findByGameIdAndQuestionNumber(gameId, questionNumber).flatMap(gameQuestion -> {
            if (gameQuestion.getQuestionSource() == QuestionSource.DB) {
                return questionService.findById(gameQuestion.getQuestionId());
            } else {
                var id = gameQuestion.getQuestionId();

                return questionRedisCache.findById(gameQuestion.getQuestionId())
                        .map(quizQuestionMapper::fromDtoToModel);
            }
        });
    }

    @Override
    public Mono<CheckQuestionAnswerDto> checkQuestion(
            Long gameId,
            Integer questionNumber,
            CheckQuestionAnswerDto answer
    ) {
        return gameQuestionService.findByGameIdAndQuestionNumber(gameId, questionNumber).flatMap(gameQuestion -> {
            if (gameQuestion.getQuestionSource() == QuestionSource.DB) {
                return questionService.findById(gameQuestion.getQuestionId())
                        .map(question -> {
                                    gameQuestion.setIsCorrectAnswer(question.getAnswer().equals(answer.getYourAnswer()));
                                    gameQuestionService.save(gameQuestion);
                                    return CheckQuestionAnswerDto.builder()
                                            .correctAnswer(question.getAnswer())
                                            .yourAnswer(answer.getYourAnswer())
                                            .isCorrect(gameQuestion.getIsCorrectAnswer())
                                            .build();
                                }
                        );
            } else {
                return questionRedisCache.findById(gameQuestion.getQuestionId())
                        .map(questionDto -> {
                                    gameQuestion.setIsCorrectAnswer(questionDto.getAnswer().equals(answer.getYourAnswer()));
                                    gameQuestionService.save(gameQuestion);
                                    return CheckQuestionAnswerDto.builder()
                                            .correctAnswer(questionDto.getAnswer())
                                            .yourAnswer(answer.getYourAnswer())
                                            .isCorrect(questionDto.getAnswer().equals(answer.getYourAnswer()))
                                            .build();
                                }
                        );
            }
        });
    }

    @Override
    public Mono<GameFinishResponseDto> finishGame(Long gameId) {
        return gameService.findById(gameId)
                .flatMap(game -> {
                    game.setEndTime(LocalDateTime.now());
                    return gameService.save(game);
                })
                .flatMap(game -> gameQuestionService.findAllByGameId(gameId)
                        .map(GameQuestion::getIsCorrectAnswer)
                        .collectList()
                        .map(isCorrectList -> GameFinishResponseDto.builder()
                                .gameId(gameId)
                                .answers(isCorrectList)
                                .build()
                        ));
    }
}
