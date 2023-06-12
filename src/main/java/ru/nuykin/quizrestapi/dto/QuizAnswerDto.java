package ru.nuykin.quizrestapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import ru.nuykin.quizrestapi.model.QuizQuestion;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class QuizAnswerDto {
    private Long id;
    private String text;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean isCorrect;
}
