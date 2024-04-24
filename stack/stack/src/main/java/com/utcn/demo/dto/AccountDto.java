package com.utcn.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import com.utcn.demo.model.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {

    private Long id;

    @NotBlank(message = "Name can't be empty")
    @Size(message = "Name can't be more 15 characters", max = 15)
    private String name;

    @NotBlank(message = "Email can't be empty")
    @Email(message = "Invalid email")
    private String email;

    private ImageDto avatar;

    private Set<Role> roles;

    private List<AnswerDto> answers;

    private List<QuestionDto> questions;



}
