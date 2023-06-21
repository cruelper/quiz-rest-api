package ru.nuykin.quizrestapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import ru.nuykin.quizrestapi.dto.QuestionDto;

@Configuration
public class RedisConfig {
    @Bean
    public ReactiveRedisTemplate reactiveRedisTemplate(
            ReactiveRedisConnectionFactory factory
    ) {
        return new ReactiveRedisTemplate<>(factory,
                RedisSerializationContext.fromSerializer(new Jackson2JsonRedisSerializer(
                        QuestionDto.class
                )));
    }
}