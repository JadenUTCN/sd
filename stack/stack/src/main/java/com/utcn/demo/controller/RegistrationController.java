package com.utcn.demo.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.utcn.demo.controller.validator.AccountPostDtoValidator;
import com.utcn.demo.model.Account;
import com.utcn.demo.dto.AccountPostDto;
import com.utcn.demo.dto.mapper.AccountMapper;
import com.utcn.demo.service.AccountService;

import java.util.Locale;
import java.util.Optional;

import static com.utcn.demo.controller.ControllerConstants.REGISTRATION_PATH;

@Controller
@AllArgsConstructor
@RequestMapping(REGISTRATION_PATH)
public class RegistrationController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final AccountPostDtoValidator accountPostDtoValidator;
    private final MessageSource messageSource;

    @ModelAttribute("module")
    String module() {
        return "registration";
    }

    @GetMapping
    public String registration(AccountPostDto accountPostDto) {
        return "registration";
    }

    @PostMapping
    public String registration(@Validated @ModelAttribute("accountPostDto") AccountPostDto accountPostDto,
                               BindingResult bindingResult,
                               Model model,
                               Locale locale) {

        accountPostDtoValidator.validate(accountPostDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        Optional<Account> account = accountService.findByEmail(accountPostDto.getEmail());

        if (account.isPresent()) {
            String errorMessage = messageSource.getMessage("error.registration.user.exist", null, locale);
            model.addAttribute("error", errorMessage);
            return "registration";
        }

        accountService.save(accountMapper.postDtoToAccount(accountPostDto));

        return "redirect:login";
    }



}
