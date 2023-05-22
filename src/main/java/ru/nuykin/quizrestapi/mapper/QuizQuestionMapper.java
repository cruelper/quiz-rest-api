package ru.nuykin.quizrestapi.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nuykin.quizrestapi.dto.QuizQuestionWithAnswersDto;
import ru.nuykin.quizrestapi.model.QuizQuestion;

@Mapper(componentModel = "spring")
public interface QuizQuestionMapper {
    QuizQuestionWithAnswersDto fromModelToDto(QuizQuestion quizQuestion);

    @InheritInverseConfiguration
    QuizQuestion fromDtoToModel(QuizQuestionWithAnswersDto quizQuestionWithAnswersDto);

    @Mapping(target = "id", ignore = true)
    QuizQuestion updateEntity(QuizQuestion oldQuizQuestion, @MappingTarget QuizQuestion newQuizQuestion);
}

