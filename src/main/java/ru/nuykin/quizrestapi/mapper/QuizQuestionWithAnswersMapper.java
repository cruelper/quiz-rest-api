package ru.nuykin.quizrestapi.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nuykin.quizrestapi.dto.QuizAnswerDto;
import ru.nuykin.quizrestapi.dto.QuizQuestionWithAnswersDto;
import ru.nuykin.quizrestapi.model.QuizAnswer;
import ru.nuykin.quizrestapi.model.QuizQuestionWithAnswers;

@Mapper(componentModel = "spring")
public interface QuizQuestionWithAnswersMapper {
    QuizQuestionWithAnswersDto fromModelToDto(QuizQuestionWithAnswers quizAnswer);

    @InheritInverseConfiguration
    QuizQuestionWithAnswers fromDtoToModel(QuizQuestionWithAnswersDto quizQuestionWithAnswersDto);

    @Mapping(target = "id", ignore = true)
    QuizQuestionWithAnswers updateEntity(QuizQuestionWithAnswers oldQuizQuestionWithAnswer, @MappingTarget QuizQuestionWithAnswers newQuizQuestionWithAnswer);
}
