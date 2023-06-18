package ru.nuykin.quizrestapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import ru.nuykin.quizrestapi.jserviceio.QuestionDto;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GameStartDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer gameId;

    private Integer questionCount;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer minDifficulty;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer maxDifficulty;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<QuestionDto.Category> categories;
}
