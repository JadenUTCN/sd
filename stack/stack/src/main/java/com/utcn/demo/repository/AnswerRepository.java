package com.utcn.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.utcn.demo.model.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query("SELECT a FROM Answer a LEFT JOIN a.positiveVotes pv LEFT JOIN a.negativeVotes nv GROUP BY a.id ORDER BY (COUNT(pv) - COUNT(nv)) DESC")
    Page<Answer> findAll(Pageable pageable);
}
