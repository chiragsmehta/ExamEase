package com.roima.exammanagement.rest.v1.service;


import com.roima.exammanagement.model.Question;
import com.roima.exammanagement.model.Option;
import com.roima.exammanagement.repository.QuestionRepository;
import com.roima.exammanagement.repository.OptionRepository;
import com.roima.exammanagement.rest.v1.dto.OptionDTO;
import com.roima.exammanagement.rest.v1.mapper.OptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptionService {
    private final OptionRepository optionRepository;
    private final QuestionRepository questionRepository;

    private final OptionMapper optionMapper;
    public OptionDTO saveOption(OptionDTO optionDTO){
           Option option = optionMapper.toEntity(optionDTO);
           optionRepository.save(option);
           return optionDTO;
    }

    public Boolean addOptionToMcqQuestionById(Long mcqQuestionId, Long optionId){
        try {
            Option option = optionRepository.findById(optionId).orElse(null);
            Question question = questionRepository.findById(mcqQuestionId).orElse(null);
            question.getOptions().add(option);
            questionRepository.save(question);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
