package ru.nuykin.quizrestapi.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionWithCategory {
    private Long id;
    private String question;
    private Category category;
    private Integer difficulty;
    private String answer;

}
