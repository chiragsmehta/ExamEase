package com.roima.exammanagement.rest.v1.mapper;

public interface BaseMapper<S,T> {
    T toDTO(S source);
    S toEntity(T target);
}
