package com.roima.exammanagement.rest.v1.mapper;


import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

public interface BaseMapper<S,T> {
    T toDTO(S source);
    S toEntity(T target);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void  updateSourceFromTarget(T target, @MappingTarget S source);
}
