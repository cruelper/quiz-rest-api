package ru.nuykin.quizrestapi.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@RedisHash("QuizQuestionWithAnswersDto")
public class QuizQuestionWithAnswersDto implements Serializable {
    private Long id;
    private String text;
    private QuizCategoryDto quizCategory;
    private QuizDifficultyDto quizDifficulty;
    private List<QuizAnswerDto> quizAnswers;
}
