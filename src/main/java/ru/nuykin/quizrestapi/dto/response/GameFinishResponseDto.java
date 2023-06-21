package ru.nuykin.quizrestapi.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import ru.nuykin.quizrestapi.dto.CheckQuestionAnswerDto;

import java.util.List;

@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GameFinishResponseDto {
    private Long gameId;
    private List<Boolean> answers;
}
