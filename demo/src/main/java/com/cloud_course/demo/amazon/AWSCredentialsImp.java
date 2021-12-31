package com.cloud_course.demo.amazon;

import com.amazonaws.auth.AWSCredentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Profile("amazon")
public class AWSCredentialsImp implements AWSCredentials {

    @Value("${amazon.credentials.accessKey}")
    private String awsKey;

    @Value("${amazon.credentials.secretKey}")
    private String aswSecret;

    @Override
    public String getAWSAccessKeyId() {
        log.info("AWS key {}", awsKey);
        return awsKey;
    }

    @Override
    public String getAWSSecretKey() {
        log.info("AWS secret {}", aswSecret);
        return aswSecret;
    }
}
