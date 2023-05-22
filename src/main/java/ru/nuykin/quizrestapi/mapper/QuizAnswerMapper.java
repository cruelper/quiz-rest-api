package ru.nuykin.quizrestapi.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nuykin.quizrestapi.dto.QuizAnswerDto;
import ru.nuykin.quizrestapi.model.QuizAnswer;

@Mapper(componentModel = "spring")
public interface QuizAnswerMapper {
    QuizAnswerDto fromModelToDto(QuizAnswer quizAnswer);

    @InheritInverseConfiguration
    QuizAnswer fromDtoToModel(QuizAnswerDto quizAnswerDto);

    @Mapping(target = "id", ignore = true)
    QuizAnswer updateEntity(QuizAnswer oldQuizAnswer, @MappingTarget QuizAnswer newQuizAnswer);
}
