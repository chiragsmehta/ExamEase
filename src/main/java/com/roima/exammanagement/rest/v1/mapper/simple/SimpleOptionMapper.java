package com.roima.exammanagement.rest.v1.mapper.simple;

import com.roima.exammanagement.model.Option;
import com.roima.exammanagement.rest.v1.dto.OptionDTO;
import com.roima.exammanagement.rest.v1.dto.simple.SimpleOptionDTO;
import com.roima.exammanagement.rest.v1.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleOptionMapper extends BaseMapper<Option, SimpleOptionDTO> {
}
