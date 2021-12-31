package com.cloud_course.demo.rekognition;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
@Slf4j
@Profile("amazon")
public class AWSClientConfi {

    private static final String REGION = "eu-west-2";
    private final AWSCredentialsImp awsCredentialsImp;

    @Bean
    public AmazonRekognition amazonRekognition() {
        log.info("Bean with credentials {} created", awsCredentialsImp.toString());
        return AmazonRekognitionClientBuilder
                .standard()
                .withRegion(REGION)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentialsImp))
                .build();

    }
}
