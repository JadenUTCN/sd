package com.utcn.demo.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.utcn.demo.controller.exception.AccountNotFoundException;
import com.utcn.demo.model.Account;
import com.utcn.demo.model.Answer;
import com.utcn.demo.model.Question;
import com.utcn.demo.service.AccountService;
import com.utcn.demo.service.AnswerService;
import com.utcn.demo.service.QuestionService;

import jakarta.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.utcn.demo.controller.ControllerConstants.ANSWERS_PATH;
import static com.utcn.demo.controller.ControllerConstants.QUESTIONS_PATH;

@Controller
@RequestMapping(ANSWERS_PATH)
@AllArgsConstructor
public class AnswerController {

    private static final String TEMPLATE_DIR = "answer";
    private static final String LIST_TEMPLATE = TEMPLATE_DIR + "/list";
    private static final String EDIT_TEMPLATE = TEMPLATE_DIR + "/edit";

    @Qualifier("questionServiceImpl")
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final AccountService accountService;

    @GetMapping
    public String findAll(Model model) {
        List<Answer> answers = answerService.findAll();

        model.addAttribute("answers", answers);

        return LIST_TEMPLATE;
    }

    @PostMapping
    public String createAnswer(@Valid @ModelAttribute Answer answer,
                               Principal principal) {

        String userEmail = principal.getName();
        Account author = accountService.findByEmail(userEmail)
                .orElseThrow(() -> new AccountNotFoundException(userEmail));

        Long id = answer.getQuestion().getId();

        Question question = questionService.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        answer.setAuthor(author);
        answer.setQuestion(question);
        answerService.save(answer);

        return String.format("redirect:%s/%d", QUESTIONS_PATH, id);
    }

    @PatchMapping(value = "/{id}/like", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map voteUp(@PathVariable Long id, Principal principal) {
        String userEmail = principal.getName();
        Account author = accountService.findByEmail(userEmail).orElseThrow(() -> new AccountNotFoundException(userEmail));
        Answer answer = answerService.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
        answer.removeNegativeVote(author);
        answer.addPositiveVote(author);
        answerService.save(answer);
        Integer rating = answer.getRating();
        return Collections.singletonMap("rating", rating);
    }

    @PatchMapping(value = "/{id}/dislike", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map voteDown(@PathVariable Long id, Principal principal) {
        String userEmail = principal.getName();
        Account author = accountService.findByEmail(userEmail).orElseThrow(() -> new AccountNotFoundException(userEmail));
        Answer answer = answerService.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
        answer.removePositiveVote(author);
        answer.addNegativeVote(author);
        answerService.save(answer);
        Integer rating = answer.getRating();
        return Collections.singletonMap("rating", rating);
    }

    @GetMapping("/edit/{id}")
    public String getEditAnswerForm(@PathVariable Long id, Model model) {
        Answer answer = answerService.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));

        model.addAttribute("answer", answer);

        return EDIT_TEMPLATE;
    }

    @PutMapping
    public String editAnswer(@Valid @ModelAttribute Answer answer) {
        answerService.save(answer);

        return String.format("redirect:%s/%d", QUESTIONS_PATH, answer.getQuestion().getId());
    }


}
