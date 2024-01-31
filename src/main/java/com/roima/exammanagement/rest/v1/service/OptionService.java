package com.roima.exammanagement.rest.v1.service;


import com.roima.exammanagement.model.McqQuestion;
import com.roima.exammanagement.model.Option;
import com.roima.exammanagement.repository.McqQuestionRepository;
import com.roima.exammanagement.repository.OptionRepository;
import com.roima.exammanagement.rest.v1.dto.OptionDTO;
import com.roima.exammanagement.rest.v1.mapper.OptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptionService {
    private final OptionRepository optionRepository;
    private final McqQuestionRepository mcqQuestionRepository;

    private final OptionMapper optionMapper;
    public OptionDTO saveOption(OptionDTO optionDTO){
           Option option = optionMapper.toEntity(optionDTO);
           optionRepository.save(option);
           return optionDTO;
    }

    public Boolean addOptionToMcqQuestionById(Long mcqQuestionId, Long optionId){
        try {
            Option option = optionRepository.findById(optionId).orElseThrow();
            McqQuestion mcqQuestion = mcqQuestionRepository.findById(mcqQuestionId).orElseThrow();
            mcqQuestion.getOptions().add(option);
            mcqQuestionRepository.save(mcqQuestion);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
