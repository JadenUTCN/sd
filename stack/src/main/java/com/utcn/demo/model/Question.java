package com.utcn.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name ="questions")
public class Question {

    @Id
    @Column(name="question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(name="question_name")
    private String questionName;

    @ManyToOne()
    @JoinColumn(name = "expert_id")
    private Expert expert;

    public Question(){

    }

    public Question(Long questionId, String questionName, Expert expert) {
        this.questionId = questionId;
        this.questionName = questionName;
        this.expert = expert;
    }

    public Long getCourseId() {
        return questionId;
    }

    public void setCourseId(Long courseId) {
        this.questionId = questionId;
    }

    public String getCourseName() {
        return questionName;
    }

    public void setCourseName(String courseName) {
        this.questionName = questionName;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }
}
