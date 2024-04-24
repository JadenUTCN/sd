package com.utcn.demo.controller;

import com.utcn.demo.model.Question;
import com.utcn.demo.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;





import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {

    @Qualifier("questionService")
    private final QuestionService questionService;

    @ModelAttribute("module")
    public String module() {
        return "index";
    }

    @GetMapping
    public String index(Model model) {
        List<Question> questions = questionService.findAll();

        model.addAttribute("questions", questions);

        return "index";
    }

}
