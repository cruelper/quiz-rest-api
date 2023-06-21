package ru.nuykin.quizrestapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.dto.QuestionDto;
import ru.nuykin.quizrestapi.mapper.QuestionWithCategoryMapper;
import ru.nuykin.quizrestapi.repository.client.JServiceFeignClient;
import ru.nuykin.quizrestapi.repository.cache.QuestionRedisCache;

@Service
@RequiredArgsConstructor
public class JServiceService {
    private final JServiceFeignClient jServiceFeignClient;
    private final QuestionWithCategoryMapper questionWithCategoryMapper;
    private final QuestionRedisCache questionRedisCache;

    public Flux<QuestionDto> findRandom(int count) {
        return jServiceFeignClient.getRandomQuestion(count)
                .flatMap(questionDto -> {
                    questionRedisCache.save(questionDto).subscribe();
                    return Mono.just(questionDto);
                });
    }
}
