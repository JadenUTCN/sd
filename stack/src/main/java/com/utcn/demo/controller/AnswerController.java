package com.utcn.demo.controller;

import com.utcn.demo.model.Answer;
import com.utcn.demo.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping("/getAll")
    @ResponseBody
    public List<com.utcn.demo.model.Answer> retrieveAllCourses(){
        return this.answerService.retrieveAnswer();
    }
    
    @PostMapping("/insertAnswer")
    @ResponseBody
    public com.utcn.demo.model.Answer insertAnswer(@RequestBody Answer answer){
        return this.answerService.insertAnswer(answer);
    }

    @PutMapping("/updateAnswer")
    @ResponseBody
    public com.utcn.demo.model.Answer updateCourse(@RequestBody Answer answer){
        return this.answerService.insertAnswer(answer);
    }

    @DeleteMapping("/deleteById")
    @ResponseBody
    public String deleteById(@RequestParam Long id){
        return this.answerService.deleteById(id);
    }
}
