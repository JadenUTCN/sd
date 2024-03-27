package com.utcn.demo.repository;

import com.utcn.demo.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository
        extends CrudRepository<Person, String> {
}
