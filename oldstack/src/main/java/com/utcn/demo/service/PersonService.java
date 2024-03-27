package com.utcn.demo.service;

import com.utcn.demo.model.Person;
import com.utcn.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> retrievePersons(){
        return (List<Person>) personRepository.findAll();
    }
}
