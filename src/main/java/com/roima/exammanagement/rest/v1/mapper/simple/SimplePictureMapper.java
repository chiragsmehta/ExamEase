package com.roima.exammanagement.rest.v1.mapper.simple;

import com.roima.exammanagement.model.Picture;
import com.roima.exammanagement.rest.v1.dto.PictureDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimplePictureDTO;
import com.roima.exammanagement.rest.v1.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimplePictureMapper extends BaseMapper<Picture, SimplePictureDTO> {
}
