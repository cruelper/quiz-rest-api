package ru.nuykin.quizrestapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "quiz_answer")
public class QuizAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column("id")
    private Long id;

    @Column("text")
    private String text;

    @Column("is_correct")
    private Boolean isCorrect;

    @Column("question_id")
    private Long quizQuestionId;
}