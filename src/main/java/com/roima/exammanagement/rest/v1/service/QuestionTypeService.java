package com.roima.exammanagement.rest.v1.service;

import com.roima.exammanagement.model.Question;
import com.roima.exammanagement.model.QuestionType;
import com.roima.exammanagement.repository.QuestionTypeRepository;
import com.roima.exammanagement.rest.v1.dto.QuestionTypeDTO;
import com.roima.exammanagement.rest.v1.mapper.QuestionTypeMapper;
import com.sipios.springsearch.anotation.SearchSpec;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionTypeService {
    private final QuestionTypeRepository questionTypeRepository;
    private final QuestionTypeMapper questionTypeMapper;

    public QuestionTypeDTO save(QuestionTypeDTO questionTypeDTO){
        QuestionType questionType = questionTypeMapper.toEntity(questionTypeDTO);
        return questionTypeMapper.toDTO(questionTypeRepository.save(questionType));
    }
    public List<QuestionTypeDTO> find( @SearchSpec Specification<QuestionType> spec) {
        return questionTypeRepository.findAll(spec).stream().map(questionTypeMapper::toDTO).collect(Collectors.toList());
    }
    public List<QuestionTypeDTO> findAll(){
        return questionTypeRepository.findAll().stream().map(questionTypeMapper::toDTO).collect(Collectors.toList());
    }

    public QuestionTypeDTO findById(Long id){
        return questionTypeMapper.toDTO(questionTypeRepository.findById(id).orElse(null));
    }

    public QuestionTypeDTO update(QuestionTypeDTO questionTypeDTO){
        QuestionType questionType = questionTypeRepository.findById(questionTypeDTO.getId()).orElse(null);
        if(questionType == null){
            throw new NoSuchElementException("Question Type does not exist");
        }
        questionTypeMapper.updateSourceFromTarget(questionTypeDTO,questionType);
        questionTypeRepository.save(questionType);
        return questionTypeMapper.toDTO(questionType);
    }

    public Boolean delete(Long id){
        try {
            questionTypeRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}