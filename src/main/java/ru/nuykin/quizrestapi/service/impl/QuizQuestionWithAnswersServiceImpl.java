package ru.nuykin.quizrestapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.model.QuizAnswer;
import ru.nuykin.quizrestapi.model.QuizCategory;
import ru.nuykin.quizrestapi.model.QuizQuestion;
import ru.nuykin.quizrestapi.model.QuizQuestionWithAnswers;
import ru.nuykin.quizrestapi.service.*;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class QuizQuestionWithAnswersServiceImpl implements QuizQuestionWithAnswersService {
    private final QuizQuestionService quizQuestionService;
    private final QuizAnswerService quizAnswerService;
    private final QuizCategoryService quizCategoryService;
    private final QuizDifficultyService quizDifficultyService;

    @Override
    public Flux<QuizQuestionWithAnswers> findAll() {
        return quizQuestionService.findAll()
                .flatMap(quizQuestion -> quizAnswerService.findByQuizQuestionId(quizQuestion.getId())
                        .collectList()
                        .flatMap(quizAnswers ->
                            Mono.zip(
                                    quizCategoryService.findById(quizQuestion.getCategoryId()),
                                    quizDifficultyService.findById(quizQuestion.getDifficultyId())
                            ).map(tuple -> QuizQuestionWithAnswers.builder()
                                                .id(quizQuestion.getId())
                                                .text(quizQuestion.getText())
                                                .quizCategory(tuple.getT1())
                                                .quizDifficulty(tuple.getT2())
                                                .quizAnswers(quizAnswers)
                                                .build()
                            )
                        ));
    }

    @Override
    public Mono<QuizQuestionWithAnswers> findById(long id) {
        return quizQuestionService.findById(id)
                .flatMap(quizQuestion -> quizAnswerService.findByQuizQuestionId(quizQuestion.getId())
                        .collectList()
                        .flatMap(quizAnswers ->
                                Mono.zip(
                                        quizCategoryService.findById(quizQuestion.getCategoryId()),
                                        quizDifficultyService.findById(quizQuestion.getDifficultyId())
                                ).map(tuple -> QuizQuestionWithAnswers.builder()
                                        .id(quizQuestion.getId())
                                        .text(quizQuestion.getText())
                                        .quizCategory(tuple.getT1())
                                        .quizDifficulty(tuple.getT2())
                                        .quizAnswers(quizAnswers)
                                        .build()
                                )
                        ));
    }

    @Override
    public Mono<QuizQuestionWithAnswers> findRandom() {
        return quizQuestionService.findRandom()
                .flatMap(quizQuestion -> quizAnswerService.findByQuizQuestionId(quizQuestion.getId())
                        .collectList()
                        .flatMap(quizAnswers ->
                                Mono.zip(
                                        quizCategoryService.findById(quizQuestion.getCategoryId()),
                                        quizDifficultyService.findById(quizQuestion.getDifficultyId())
                                ).map(tuple -> QuizQuestionWithAnswers.builder()
                                        .id(quizQuestion.getId())
                                        .text(quizQuestion.getText())
                                        .quizCategory(tuple.getT1())
                                        .quizDifficulty(tuple.getT2())
                                        .quizAnswers(quizAnswers)
                                        .build()
                                )
                        ));
    }

    @Override
    public Mono<QuizAnswer> checkAnswer(QuizAnswer sendAnswer) {
        return quizAnswerService.findCorrectQuizAnswer(sendAnswer.getQuizQuestionId());
    }

    @Override
    public Mono<QuizQuestionWithAnswers> save(QuizQuestionWithAnswers quizQuestionWithAnswers) throws ExecutionException, InterruptedException {
        QuizQuestion quizQuestion = quizQuestionService.save(QuizQuestion.builder()
                .text(quizQuestionWithAnswers.getText())
                .categoryId(quizQuestionWithAnswers.getQuizCategory().getId())
                .difficultyId(quizQuestionWithAnswers.getQuizDifficulty().getId())
                .build()
        ).toFuture().get();

        for (QuizAnswer quizAnswer : quizQuestionWithAnswers.getQuizAnswers()) {
            quizAnswer.setQuizQuestionId(quizQuestion.getId());
            quizAnswer.setId(quizAnswerService.save(quizAnswer).toFuture().get().getId());
        }

        quizQuestionWithAnswers.setId(quizQuestion.getId());
        return Mono.just(quizQuestionWithAnswers);
    }

    @Override
    public Mono<QuizQuestionWithAnswers> update(long id, QuizQuestionWithAnswers quizQuestionWithAnswers) throws ExecutionException, InterruptedException {
        QuizQuestion quizQuestion = quizQuestionService.findById(id)
                .flatMap(question -> {
                    question.setText(quizQuestionWithAnswers.getText());
                    question.setCategoryId(quizQuestionWithAnswers.getQuizCategory().getId());
                    question.setDifficultyId(quizQuestionWithAnswers.getQuizDifficulty().getId());
                    return quizQuestionService.save(question);
                }).toFuture().get();

        quizAnswerService.deleteByQuizQuestionId(quizQuestion.getId()).toFuture().get();

        for (QuizAnswer quizAnswer : quizQuestionWithAnswers.getQuizAnswers()) {
            quizAnswer.setQuizQuestionId(quizQuestion.getId());
            quizAnswer.setId(quizAnswerService.save(quizAnswer).toFuture().get().getId());
        }

        quizQuestionWithAnswers.setId(quizQuestion.getId());
        return Mono.just(quizQuestionWithAnswers);
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return quizAnswerService.deleteByQuizQuestionId(id).and(quizQuestionService.deleteById(id));
    }
}
