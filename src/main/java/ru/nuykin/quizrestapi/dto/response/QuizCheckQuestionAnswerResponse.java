package ru.nuykin.quizrestapi.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nuykin.quizrestapi.dto.QuizAnswerDto;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class QuizCheckQuestionAnswerResponse {
    private Long question_id;
    private Boolean isCorrect;
    private QuizAnswerDto correctQuizAnswer;
    private QuizAnswerDto yourQuizAnswer;
}
