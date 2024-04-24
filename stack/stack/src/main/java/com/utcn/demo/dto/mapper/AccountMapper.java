package com.utcn.demo.dto.mapper;

import org.mapstruct.*;
import com.utcn.demo.model.Account;
import com.utcn.demo.model.Question;
import com.utcn.demo.dto.AccountDto;
import com.utcn.demo.dto.AccountPostDto;
import com.utcn.demo.dto.QuestionDto;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "questions", qualifiedByName = "questionsToQuestionsDto")
    AccountDto toAccountDto(Account account);

    @Named("questionsToQuestionsDto")
    @Mapping(target = "author", expression = "java(null)")
    QuestionDto toQuestionDto(Question question);

    @Mappings({
            @Mapping(target = "questions", ignore = true),
            @Mapping(target = "password", ignore = true)
    })
    Account toAccount(AccountDto accountDto);

    @Mapping(target = "id", ignore = true)
    Account postDtoToAccount(AccountPostDto accountPostDto);

}
