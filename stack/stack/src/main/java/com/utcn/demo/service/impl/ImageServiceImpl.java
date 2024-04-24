package com.utcn.demo.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.utcn.demo.model.Image;
import com.utcn.demo.repository.ImageRepository;
import com.utcn.demo.service.ImageService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Image> findById(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public void deleteById(Long id) {
        imageRepository.deleteById(id);
    }

}
