package com.cloud_course.demo.amazon.rekognition.service;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import com.cloud_course.demo.model.response.ImageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Profile("amazon")
public class AWSRekognitionService {

    @Value("${amazon.bucket}")
    private String bucket;

    private final AmazonRekognition amazonRekognition;

    public ImageResponse getImageLabels(String imageName) throws AmazonRekognitionException {
        DetectLabelsRequest request = new DetectLabelsRequest()
                .withImage(new Image()
                        .withS3Object(
                                new S3Object()
                                        .withName(imageName)
                                        .withBucket(bucket)))
                .withMaxLabels(15)
                .withMinConfidence(75F);
        DetectLabelsResult result = amazonRekognition.detectLabels(request);
        return ImageResponse.builder()
                .imageLabels(result.getLabels())
                .imageName(imageName)
                .build();
    }
}
