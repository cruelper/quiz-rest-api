package ru.nuykin.quizrestapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.mapper.QuizQuestionMapper;
import ru.nuykin.quizrestapi.model.QuizQuestion;
import ru.nuykin.quizrestapi.model.QuizQuestion;
import ru.nuykin.quizrestapi.repository.QuizAnswerRepository;
import ru.nuykin.quizrestapi.repository.QuizQuestionRepository;
import ru.nuykin.quizrestapi.service.QuizQuestionService;
import ru.nuykin.quizrestapi.service.QuizQuestionService;

@Service
@RequiredArgsConstructor
public class QuizQuestionServiceImpl implements QuizQuestionService {

    private final QuizQuestionRepository quizQuestionRepository;
    private final QuizQuestionMapper quizQuestionMapper;

    @Override
    public Flux<QuizQuestion> findAll() {
        return quizQuestionRepository.findAll();
    }

    @Override
    public Mono<QuizQuestion> findById(long id) {
        return quizQuestionRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException()));
    }

    @Override
    public Mono<QuizQuestion> findRandom() {
        return quizQuestionRepository.getRandomQuestion();
    }

    @Override
    public Mono<QuizQuestion> save(QuizQuestion quizQuestion) {
        return quizQuestionRepository.save(quizQuestion);
    }

    @Override
    public Mono<QuizQuestion> update(long id, QuizQuestion quizQuestion) {
        return quizQuestionRepository.findById(id)
                .flatMap(oldQuizQuestion -> quizQuestionRepository.save(
                                quizQuestionMapper.updateEntity(quizQuestion, oldQuizQuestion)
                        )
                );
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return quizQuestionRepository.deleteById(id);
    }
}
