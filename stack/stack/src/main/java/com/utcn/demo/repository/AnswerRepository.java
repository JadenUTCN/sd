package com.utcn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utcn.demo.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
