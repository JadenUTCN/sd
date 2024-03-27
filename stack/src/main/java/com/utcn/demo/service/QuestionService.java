package com.utcn.demo.service;


import com.utcn.demo.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<com.utcn.demo.model.Question> retrieveQuestion(){
        return (List<com.utcn.demo.model.Question>) this.questionRepository.findAll();
    }

    public com.utcn.demo.model.Question insertQuestion(com.utcn.demo.model.Question question) {
        return this.questionRepository.save(question);
    }

    public String deleteById(Long id) {
        try{
            this.questionRepository.deleteById(id);
            return "Deletion Successfully";
        }catch (Exception e){
            return "Failed to delete course with id " + id;
        }
    }
}
