package ru.nuykin.quizrestapi.repository.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.dto.QuizQuestionWithAnswersDto;

@Repository
@RequiredArgsConstructor
public class QuizQuestionRedisRepository {
    private final ReactiveRedisOperations<String, QuizQuestionWithAnswersDto> reactiveRedisOperations;
    public Flux<QuizQuestionWithAnswersDto> findAll(){
        return reactiveRedisOperations.opsForList().range("QuizQuestionRepository", 0, -1);
    }
    public Mono<QuizQuestionWithAnswersDto> findById(Long id) {
        return reactiveRedisOperations.opsForValue().get(id);
//        return this.findAll().filter(p -> p.getId().equals(id));
    }
    public Mono<Long> save(QuizQuestionWithAnswersDto quizQuestionWithAnswersDto){
        return reactiveRedisOperations.opsForList().rightPush("QuizQuestionWithAnswersDto", quizQuestionWithAnswersDto);
    }
    public Mono<Boolean> deleteAll() {
        return reactiveRedisOperations.opsForList().delete("QuizQuestionWithAnswersDto");
    }
    public Mono<Boolean> remove(Long id) {
        return reactiveRedisOperations.opsForValue().delete(Long.toString(id));
    }
}
