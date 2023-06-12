package ru.nuykin.quizrestapi.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.nuykin.quizrestapi.dto.QuizCategoryDto;
import ru.nuykin.quizrestapi.model.QuizCategory;

@Mapper(componentModel = "spring")
public interface QuizCategoryMapper {
    QuizCategoryDto fromModelToDto(QuizCategory quizCategory);

    @InheritInverseConfiguration
    QuizCategory fromDtoToModel(QuizCategoryDto quizCategoryDto);

    @Mapping(target = "id", ignore = true)
    QuizCategory updateEntity(QuizCategory oldQuizCategory, @MappingTarget QuizCategory newQuizCategory);
}
