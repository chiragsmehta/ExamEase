package com.roima.exammanagement.rest.v1.controller.admin;


import com.roima.exammanagement.model.QuestionType;
import com.roima.exammanagement.rest.v1.dto.QuestionCategoryDTO;
import com.roima.exammanagement.rest.v1.dto.QuestionDTO;
import com.roima.exammanagement.rest.v1.dto.QuestionTypeDTO;
import com.roima.exammanagement.rest.v1.service.QuestionTypeService;
import com.sipios.springsearch.anotation.SearchSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/question-type")
@CrossOrigin
public class QuestionTypeController {
    private final QuestionTypeService questionTypeService;

    @PostMapping("")
    public ResponseEntity<QuestionTypeDTO> save(@RequestBody QuestionTypeDTO questionTypeDTO){
        try {
//            System.out.println(questionTypeDTO.getName());
            return new ResponseEntity<>(questionTypeService.save(questionTypeDTO), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("")
    public ResponseEntity<List<QuestionTypeDTO>> find(@SearchSpec Specification<QuestionType> spec){
        return ResponseEntity.ok(questionTypeService.find(spec));
    }
    @GetMapping("/all")
    public ResponseEntity<List<QuestionTypeDTO>> findAll(){
        return ResponseEntity.ok(questionTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionTypeDTO> findById(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(questionTypeService.findById(id));
        }catch (Exception e){
            return  ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id){
        try {
           return ResponseEntity.ok(questionTypeService.delete(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<QuestionTypeDTO> update(@RequestBody QuestionTypeDTO questionTypeDTO){
        try {
            return ResponseEntity.ok(questionTypeService.update(questionTypeDTO));
        }catch (Exception e){
            return  ResponseEntity.badRequest().build();
        }
    }


}
