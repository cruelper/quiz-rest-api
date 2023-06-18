package ru.nuykin.quizrestapi.jserviceio;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class QuestionDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Category {
        private Integer id;
        private String title;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private Integer cluesCount;

    }

    private Long id;
    private String answer;
    private String question;
    private Integer value;
    private LocalDateTime airdate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer categoryId;
    private Integer gameId;
    private Integer invalidCount;
    private Category category;
}
