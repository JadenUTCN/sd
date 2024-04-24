package com.utcn.demo.dto;

import lombok.*;
import com.utcn.demo.audit.Auditable;
import com.utcn.demo.model.Account;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto extends Auditable<Account> {

    private Long id;

    private String title;

    private String body;


    private Set<AccountDto> positiveVotes;

    private Set<AccountDto> negativeVotes;

    @NotNull(message = "Account NOT NULL")
    private AccountDto author;

    private List<AnswerDto> answers;

}
