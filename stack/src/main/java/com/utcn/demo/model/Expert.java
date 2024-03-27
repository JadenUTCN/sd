package com.utcn.demo.model;


import jakarta.persistence.*;

@Entity
@Table(name ="experts")
public class Expert {

    @Id
    @Column(name="expert_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expertId;

    @Column(name = "last_name")
    private String lastName;

    @Column(name="first_name")
    private String firstName;

    public Expert(){

    }

    public Expert(Long expertId, String lastName, String firstName) {
        this.expertId = expertId;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Long getExpertId() {
        return expertId;
    }

    public void setExpertId(Long expertId) {
        this.expertId = expertId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
