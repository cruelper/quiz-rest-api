package ru.nuykin.quizrestapi.jserviceio;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nuykin.quizrestapi.dto.QuizQuestionWithAnswersDto;
import ru.nuykin.quizrestapi.mapper.QuizQuestionWithAnswersMapper;
import ru.nuykin.quizrestapi.repository.redis.QuizQuestionRedisRepository;

@Service
@RequiredArgsConstructor
public class JServiceIOService {
    private final FeignClient feignClient;
    private final QuizQuestionWithAnswersMapper quizQuestionWithAnswersMapper;
    private final QuizQuestionRedisRepository quizQuestionRedisRepository;

    public Flux<QuizQuestionWithAnswersDto> findRandom(int count) {
        return feignClient.getRandomQuestion(count)
                .flatMap(questionDto -> {
                    QuizQuestionWithAnswersDto quizQuestionWithAnswersDto =
                            quizQuestionWithAnswersMapper.fromJServiceQuestionToQuizQuestionDto(questionDto);
                    quizQuestionRedisRepository.save(quizQuestionWithAnswersDto).subscribe();
                    return Mono.just(quizQuestionWithAnswersDto);
                });
    }
}
