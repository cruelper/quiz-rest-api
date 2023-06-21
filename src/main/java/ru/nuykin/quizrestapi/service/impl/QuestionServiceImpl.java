package ru.nuykin.quizrestapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.mapper.QuizQuestionMapper;
import ru.nuykin.quizrestapi.model.Question;
import ru.nuykin.quizrestapi.repository.db.QuestionRepository;
import ru.nuykin.quizrestapi.service.QuestionService;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizQuestionMapper quizQuestionMapper;

    @Override
    public Flux<Question> findAll() {
        return questionRepository.findAll();
    }

    @Override
    public Mono<Question> findById(long id) {
        return questionRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException()));
    }

    @Override
    public Mono<Question> findRandom() {
        return questionRepository.getRandomQuestion();
    }

    @Override
    public Mono<Question> save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Mono<Question> update(long id, Question question) {
        return questionRepository.findById(id)
                .flatMap(oldQuizQuestion -> questionRepository.save(
                                quizQuestionMapper.updateEntity(question, oldQuizQuestion)
                        )
                );
    }

    @Override
    public Mono<Void> deleteById(long id) {
        return questionRepository.deleteById(id);
    }
}
