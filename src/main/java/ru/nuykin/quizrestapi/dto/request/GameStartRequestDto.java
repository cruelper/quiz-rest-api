package ru.nuykin.quizrestapi.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import ru.nuykin.quizrestapi.dto.CategoryDto;

import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GameStartRequestDto {
    private Integer questionCount;
    private Integer minDifficulty;
    private Integer maxDifficulty;
    private List<CategoryDto> categories;
}
