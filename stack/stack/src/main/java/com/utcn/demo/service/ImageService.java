package com.utcn.demo.service;

import com.utcn.demo.model.Image;

import java.util.Optional;

public interface ImageService {

    Optional<Image> findById(Long id);

    Image save(Image image);

    void deleteById(Long id);

}
