package com.utcn.demo.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.utcn.demo.model.Answer;
import com.utcn.demo.repository.AnswerRepository;
import com.utcn.demo.service.AnswerService;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Answer> findById(Long id) {
        return answerRepository.findById(id);
    }

    @Override
    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public void deleteById(Long id) {
        try {
            answerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            log.info("Delete non existing entity with id=" + id, ex);
        }
    }
}
