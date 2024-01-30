package com.roima.exammanagement.rest.v1.mapper;

import com.roima.exammanagement.model.Picture;
import com.roima.exammanagement.rest.v1.dto.PictureDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PictureMapper extends BaseMapper<Picture, PictureDTO> {
}
