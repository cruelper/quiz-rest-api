package ru.nuykin.quizrestapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Entity
@Table(name = "quiz_answer")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class QuizAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column("text")
    private String text;

    @Column("isCorrect")
    private Boolean isCorrect;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false)
    private QuizQuestion quizQuestion;
}