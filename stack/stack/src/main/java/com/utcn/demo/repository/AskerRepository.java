package com.utcn.demo.repository;

import com.utcn.demo.model.Asker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AskerRepository
        extends CrudRepository<Asker, String> {
}
