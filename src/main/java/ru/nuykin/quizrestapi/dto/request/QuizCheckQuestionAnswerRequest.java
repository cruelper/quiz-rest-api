package ru.nuykin.quizrestapi.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import ru.nuykin.quizrestapi.dto.QuizAnswerDto;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class QuizCheckQuestionAnswerRequest {
    private Long questionId;
    private QuizAnswerDto quizAnswer;
}
