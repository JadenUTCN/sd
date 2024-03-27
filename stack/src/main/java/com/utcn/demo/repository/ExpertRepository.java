package com.utcn.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertRepository extends CrudRepository<com.utcn.demo.model.Expert, Long> {
}
