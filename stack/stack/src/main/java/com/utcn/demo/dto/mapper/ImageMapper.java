package com.utcn.demo.dto.mapper;

import org.mapstruct.Mapper;
import com.utcn.demo.model.Image;
import com.utcn.demo.dto.ImageDto;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    Image toImage(ImageDto imageDto);

    ImageDto toImageDto(Image image);

}
