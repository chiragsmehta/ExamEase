package com.roima.exammanagement.rest.v1.controller.admin;


import com.roima.exammanagement.model.QuestionCategory;
import com.roima.exammanagement.model.QuestionType;
import com.roima.exammanagement.rest.v1.dto.QuestionCategoryDTO;
import com.roima.exammanagement.rest.v1.dto.QuestionTypeDTO;
import com.roima.exammanagement.rest.v1.service.QuestionCategoryService;
import com.sipios.springsearch.anotation.SearchSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/question-category")
public class QuestionCategoryController {
    private final QuestionCategoryService questionCategoryService;


    @PostMapping("")
    public ResponseEntity<QuestionCategoryDTO> save(@RequestBody QuestionCategoryDTO questionCategoryDTO){
        try {
            System.out.println(questionCategoryDTO.getName());
            return new ResponseEntity<>(questionCategoryService.save(questionCategoryDTO), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<QuestionCategoryDTO>> find(@SearchSpec Specification<QuestionCategory> spec){
        return ResponseEntity.ok(questionCategoryService.find(spec));
    }
    @GetMapping("/all")
    public ResponseEntity<List<QuestionCategoryDTO>> findAll(){
        return ResponseEntity.ok(questionCategoryService.find(null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionCategoryDTO> findById(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(questionCategoryService.findById(id));
        }catch (Exception e){
            return  ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(questionCategoryService.delete(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<QuestionCategoryDTO> update(@RequestBody QuestionCategoryDTO questionCategoryDTO){
        try {
            return ResponseEntity.ok(questionCategoryService.update(questionCategoryDTO));
        }catch (Exception e){
            return  ResponseEntity.badRequest().build();
        }
    }


}
