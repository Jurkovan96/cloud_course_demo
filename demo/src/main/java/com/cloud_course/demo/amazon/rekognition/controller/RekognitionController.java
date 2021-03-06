package com.cloud_course.demo.amazon.rekognition.controller;

import com.cloud_course.demo.amazon.rekognition.service.AWSRekognitionService;
import com.cloud_course.demo.model.response.ImageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aws")
@RequiredArgsConstructor
@Slf4j
@Profile("amazon")
public class RekognitionController {

    private final AWSRekognitionService awsService;

    @GetMapping("/labels")
    public ResponseEntity<ImageResponse> getImageLabels(@RequestParam(name = "image") String image) {
        return new ResponseEntity<>(awsService.getImageLabels(image), HttpStatus.OK);
    }


}
