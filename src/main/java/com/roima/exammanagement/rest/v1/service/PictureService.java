//package com.roima.exammanagement.rest.v1.service;
//
//
//import com.roima.exammanagement.model.Picture;
//import com.roima.exammanagement.repository.PictureRepository;
//import com.roima.exammanagement.rest.v1.dto.PictureDTO;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.springframework.web.multipart.MaxUploadSizeExceededException;
//import org.springframework.web.multipart.MultipartFile;
//
//@Service
//@RequiredArgsConstructor
//public class PictureService {
//    private final PictureRepository pictureRepository;
//
//    public PictureDTO savePicture(MultipartFile file) throws Exception{
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        try {
//
//            if(fileName.contains("..")) {
//                throw  new Exception("Filename contains invalid path sequence " + fileName);
//            }
//            if (file.getBytes().length > (1024 * 1024)) {
//                throw new Exception("File size exceeds maximum limit");
//            }
//            Picture attachment = new Picture();
//            return pictureRepository.save(attachment);
//        } catch (MaxUploadSizeExceededException e) {
//            throw new MaxUploadSizeExceededException(file.getSize());
//        } catch (Exception e) {
//            throw new Exception("Could not save File: " + fileName);
//        }
//    }
//}
