package com.utcn.demo.service;


import com.utcn.demo.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public List<com.utcn.demo.model.Answer> retrieveAnswer(){
        return (List<com.utcn.demo.model.Answer>) this.answerRepository.findAll();
    }

    public com.utcn.demo.model.Answer insertAnswer(com.utcn.demo.model.Answer course) {
        return this.answerRepository.save(course);
    }

    public String deleteById(Long id) {
        try{
            this.answerRepository.deleteById(id);
            return "Deletion Successfully";
        }catch (Exception e){
            return "Failed to delete course with id " + id;
        }
    }
}
