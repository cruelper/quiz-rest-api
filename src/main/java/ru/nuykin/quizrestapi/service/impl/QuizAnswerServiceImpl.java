package ru.nuykin.quizrestapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.mapper.QuizAnswerMapper;
import ru.nuykin.quizrestapi.model.QuizAnswer;
import ru.nuykin.quizrestapi.repository.QuizAnswerRepository;
import ru.nuykin.quizrestapi.service.QuizAnswerService;

@Service
@RequiredArgsConstructor
public class QuizAnswerServiceImpl implements QuizAnswerService {

    private final QuizAnswerRepository quizAnswerRepository;
    private final QuizAnswerMapper quizAnswerMapper;

    @Override
    public Flux<QuizAnswer> findAll() {
        return quizAnswerRepository.findAll();
    }

    @Override
    public Mono<QuizAnswer> findById(long id) {
        return quizAnswerRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException()));
    }

    @Override
    public Flux<QuizAnswer> findByQuizQuestionId(long id) {
        return quizAnswerRepository.findByQuizQuestionId(id);
    }

    @Override
    public Mono<QuizAnswer> findCorrectQuizAnswer(long questionId) {
        return quizAnswerRepository.findByQuizQuestionIdAndIsCorrect(questionId, true);
    }

    @Override
    public Mono<QuizAnswer> save(QuizAnswer quizAnswer) {
        return quizAnswerRepository.save(quizAnswer);
    }

    @Override
    public Mono<QuizAnswer> update(long id, QuizAnswer quizAnswer) {
        return quizAnswerRepository.findById(id)
                .flatMap(oldQuizAnswer -> quizAnswerRepository.save(
                                quizAnswerMapper.updateEntity(quizAnswer, oldQuizAnswer)
                        )
                );
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return quizAnswerRepository.deleteById(id);
    }

    @Override
    public Mono<Void> deleteByQuizQuestionId(long id) {
        return quizAnswerRepository.deleteByQuizQuestionId(id);
    }
}
