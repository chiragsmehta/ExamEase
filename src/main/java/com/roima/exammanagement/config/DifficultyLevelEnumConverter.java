package com.roima.exammanagement.config;

import com.roima.exammanagement.model.DifficultyLevel;
import lombok.NonNull;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class DifficultyLevelEnumConverter implements Converter<String,DifficultyLevel> {
    @Override
    public DifficultyLevel convert(@NonNull String source) {
        if(StringUtils.isBlank(source)){
            return null;
        }else{
            return EnumUtils.getEnum(DifficultyLevel.class,source.toUpperCase());
        }
    }
}
