package ru.nuykin.quizrestapi.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nuykin.quizrestapi.dto.QuizDifficultyDto;
import ru.nuykin.quizrestapi.model.QuizDifficulty;

@Mapper(componentModel = "spring")
public interface QuizDifficultyMapper {
    QuizDifficultyDto fromModelToDto(QuizDifficulty quizDifficulty);

    @InheritInverseConfiguration
    QuizDifficulty fromDtoToModel(QuizDifficultyDto quizDifficultyDto);

    @Mapping(target = "id", ignore = true)
    QuizDifficulty updateEntity(QuizDifficulty oldQuizDifficulty, @MappingTarget QuizDifficulty newQuizDifficulty);
}
