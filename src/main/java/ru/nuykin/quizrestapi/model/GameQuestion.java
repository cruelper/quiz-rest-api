package ru.nuykin.quizrestapi.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "game_question")
public class GameQuestion {
    @Id
    @Column("game_id")
    private Long gameId;

    @Column("question_id")
    private Integer questionId;

    @Column("question_source")
    private String questionSource;

    @Column("is_correct_answer")
    private Boolean isCorrectAnswer;
}
