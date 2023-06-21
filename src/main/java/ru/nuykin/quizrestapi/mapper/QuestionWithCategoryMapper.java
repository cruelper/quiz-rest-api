package ru.nuykin.quizrestapi.mapper;

import org.mapstruct.*;
import ru.nuykin.quizrestapi.dto.QuestionDto;
import ru.nuykin.quizrestapi.model.QuestionWithCategory;

@Mapper(componentModel = "spring")
public interface QuestionWithCategoryMapper {
    QuestionDto fromModelToDto(QuestionWithCategory quizAnswer);

    @InheritInverseConfiguration
    QuestionWithCategory fromDtoToModel(QuestionDto questionDto);

    @Mapping(target = "id", ignore = true)
    QuestionWithCategory updateEntity(QuestionWithCategory oldQuizQuestionWithAnswer, @MappingTarget QuestionWithCategory newQuizQuestionWithAnswer);

//    @Named("fromCategoryToQuizCategory")
//    default QuizCategoryDto fromCategoryToQuizCategory(QuestionDto.Category category) {
//        return new QuizCategoryDto(category.getId(), category.getTitle());
//    }
//
//    @Named("fromValueToQuizDifficulty")
//    default QuizDifficultyDto fromValueToQuizDifficulty(Integer value) {
//        return new QuizDifficultyDto(-1, value);
//    }
//
//    @Named("fromAnswerToListQuizAnswers")
//    default List<QuizAnswerDto> fromAnswerToListQuizAnswers(String answer) {
//        return new ArrayList<>() {{
//            add(new QuizAnswerDto(-1L, answer, true));
//        }};
//    }
}
