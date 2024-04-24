package com.utcn.demo.service;

import com.utcn.demo.model.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerService {

    List<Answer> findAll();

    Optional<Answer> findById(Long id);

    Answer save(Answer answer);

    void deleteById(Long id);

}
