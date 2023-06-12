package ru.nuykin.quizrestapi.model;

import lombok.*;
import ru.nuykin.quizrestapi.dto.QuizAnswerDto;
import ru.nuykin.quizrestapi.dto.QuizCategoryDto;
import ru.nuykin.quizrestapi.dto.QuizDifficultyDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizQuestionWithAnswers {
    private Long id;
    private String text;
    private QuizCategory quizCategory;
    private QuizDifficulty quizDifficulty;
    private List<QuizAnswer> quizAnswers;
}
