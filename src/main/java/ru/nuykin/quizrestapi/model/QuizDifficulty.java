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
@Table("quiz_difficulty")
public class QuizDifficulty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column("id")
    private Integer id;

    @Column("level")
    private Short level;
}
