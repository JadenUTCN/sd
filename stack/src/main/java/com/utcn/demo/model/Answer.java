package com.utcn.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name ="answers")
public class Answer {

    @Id
    @Column(name="answer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(name="answer_name")
    private String answerName;

    @ManyToOne()
    @JoinColumn(name = "expert_id")
    private Expert expert;

    public Answer(){

    }

    public Answer(Long answerId, String answerName, Expert expert) {
        this.answerId = answerId;
        this.answerName = answerName;
        this.expert = expert;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String courseName) {
        this.answerName = courseName;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }
}
