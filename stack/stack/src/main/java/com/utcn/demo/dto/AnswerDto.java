package com.utcn.demo.dto;

import lombok.*;
import com.utcn.demo.audit.Auditable;
import com.utcn.demo.model.Account;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto extends Auditable<Account> {

    private Long id;

    private String content;

    private AccountDto author;

    private Boolean isAccepted;

    private Set<AccountDto> negativeVotes;

    private Set<AccountDto> positiveVotes;

    private QuestionDto question;

}
