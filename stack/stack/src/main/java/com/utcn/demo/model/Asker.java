package com.utcn.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "asker")
public class Asker {

    @Id
    @Column(name = "cnp")
    private String cnp;

    @Column(name = "name")
    private String name;

    public Asker(){};

    public Asker(String cnp, String name){
        this.cnp = cnp;
        this.name = name;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
