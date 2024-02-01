package com.roima.exammanagement.rest.v1.mapper;

import com.roima.exammanagement.model.Option;
import com.roima.exammanagement.rest.v1.dto.OptionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OptionMapper extends BaseMapper<Option, OptionDTO> {
}
