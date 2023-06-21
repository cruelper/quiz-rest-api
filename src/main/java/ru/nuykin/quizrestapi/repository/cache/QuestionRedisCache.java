package ru.nuykin.quizrestapi.repository.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.dto.QuestionDto;

@Repository
@RequiredArgsConstructor
public class QuestionRedisCache {
    private final String KEY = "QuizQuestionWithAnswersDto";
    private final ReactiveRedisOperations<String, QuestionDto> reactiveRedisOperations;
    public Flux<QuestionDto> findAll(){
        return reactiveRedisOperations.opsForList().range(KEY, 0, -1);
    }
    public Mono<QuestionDto> findById(Long id) {
//        return reactiveRedisOperations.opsForValue().get(id);
        return this.findAll().filter(p -> p.getId().equals(id)).last();
    }
    public Mono<Long> save(QuestionDto questionDto){
        return reactiveRedisOperations.opsForList().rightPush(KEY, questionDto);
    }
    public Mono<Boolean> deleteAll() {
        return reactiveRedisOperations.opsForList().delete(KEY);
    }
    public Mono<Boolean> remove(Long id) {
        return reactiveRedisOperations.opsForValue().delete(Long.toString(id));
    }
}
