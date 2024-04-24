package com.utcn.demo.dto;

import lombok.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountPostDto {

    @NotBlank(message = "Name can't be empty")
    @Size(message = "Name can't be more 15 characters", max = 15)
    private String name;

    @NotBlank(message = "Email can't be empty")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Password can't be empty")
    @Size(message = "Password can't be less 6 characters", min = 6)
    private String password;

    @NotBlank(message = "Password confirm can't be empty")
    private String passwordConfirm;

}
