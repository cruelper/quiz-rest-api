package ru.nuykin.quizrestapi.jserviceio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;

@ReactiveFeignClient(url = "http://jservice.io/api/", name = "jservice")
public interface FeignClient {
    @GetMapping("/random")
    Flux<QuestionDto> getRandomQuestion(@RequestParam(value = "count") Integer count);
}
