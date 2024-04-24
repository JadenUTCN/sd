package com.utcn.demo.dto.mapper;

import org.mapstruct.*;
import com.utcn.demo.model.Question;
import com.utcn.demo.dto.QuestionDto;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    Question toQuestion(QuestionDto questionDto);

    @Mappings({
        @Mapping(target = "author.questions", ignore = true),
        @Mapping(target = "author.answers", ignore = true),
    })
    QuestionDto toQuestionDto(Question question);

}
