package com.utcn.demo.repository;

import com.utcn.demo.model.Answer;
import com.utcn.demo.model.Expert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AnswerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    void whenFindById_thenReturnAnswer() {
        Expert expert = new Expert(); // Assuming a no-arg constructor, set properties as needed
        entityManager.persistAndFlush(expert); // Persisting expert because Answer has a relationship with Expert

        Answer answer = new Answer();
        answer.setAnswerName("Sample Answer");
        answer.setExpert(expert); // Assuming an Answer references an Expert
        entityManager.persistAndFlush(answer);


        Answer found = answerRepository.findById(answer.getAnswerId()).orElse(null);


        assertThat(found).isNotNull();
        assertThat(found.getAnswerName()).isEqualTo(answer.getAnswerName());
        assertThat(found.getExpert()).isEqualToComparingFieldByField(expert);
    }
}
