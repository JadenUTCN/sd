package com.utcn.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.utcn.demo.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
