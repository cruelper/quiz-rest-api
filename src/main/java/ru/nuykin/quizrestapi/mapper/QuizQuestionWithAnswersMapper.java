package ru.nuykin.quizrestapi.mapper;

import org.mapstruct.*;
import ru.nuykin.quizrestapi.dto.QuizAnswerDto;
import ru.nuykin.quizrestapi.dto.QuizCategoryDto;
import ru.nuykin.quizrestapi.dto.QuizDifficultyDto;
import ru.nuykin.quizrestapi.dto.QuizQuestionWithAnswersDto;
import ru.nuykin.quizrestapi.jserviceio.QuestionDto;
import ru.nuykin.quizrestapi.model.QuizQuestionWithAnswers;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface QuizQuestionWithAnswersMapper {
    QuizQuestionWithAnswersDto fromModelToDto(QuizQuestionWithAnswers quizAnswer);

    @InheritInverseConfiguration
    QuizQuestionWithAnswers fromDtoToModel(QuizQuestionWithAnswersDto quizQuestionWithAnswersDto);

    @Mapping(source = "question", target = "text")
    @Mapping(source = "category", target = "quizCategory", qualifiedByName = "fromCategoryToQuizCategory")
    @Mapping(source = "value", target = "quizDifficulty", qualifiedByName = "fromValueToQuizDifficulty")
    @Mapping(source = "answer", target = "quizAnswers", qualifiedByName = "fromAnswerToListQuizAnswers")
    QuizQuestionWithAnswersDto fromJServiceQuestionToQuizQuestionDto(QuestionDto questionDto);

    @Mapping(target = "id", ignore = true)
    QuizQuestionWithAnswers updateEntity(QuizQuestionWithAnswers oldQuizQuestionWithAnswer, @MappingTarget QuizQuestionWithAnswers newQuizQuestionWithAnswer);

    @Named("fromCategoryToQuizCategory")
    default QuizCategoryDto fromCategoryToQuizCategory(QuestionDto.Category category) {
        return new QuizCategoryDto(category.getId(), category.getTitle());
    }

    @Named("fromValueToQuizDifficulty")
    default QuizDifficultyDto fromValueToQuizDifficulty(Integer value) {
        return new QuizDifficultyDto(-1, value);
    }

    @Named("fromAnswerToListQuizAnswers")
    default List<QuizAnswerDto> fromAnswerToListQuizAnswers(String answer) {
        return new ArrayList<>() {{
            add(new QuizAnswerDto(-1L, answer, true));
        }};
    }
}
