package com.cloud_course.demo.amazon;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.policy.Policy;
import com.amazonaws.auth.policy.Principal;
import com.amazonaws.auth.policy.Statement;
import com.amazonaws.auth.policy.actions.S3Actions;
import com.amazonaws.auth.policy.resources.S3ObjectResource;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
@Slf4j
@Profile("amazon")
public class AWSClientConfig {

    @Value("${amazon.region}")
    private String region;

    @Value("${amazon.bucket}")
    private String bucket;

    private final AWSCredentialsImp awsCredentialsImp;

    @Bean
    public AmazonRekognition amazonRekognition() {
        log.info("Bean with credentials {} created", awsCredentialsImp.toString());
        return AmazonRekognitionClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentialsImp))
                .build();

    }

    @Bean
    public AmazonS3 s3Client() {
        log.info("AmazonS3 Client with credentials {} created", awsCredentialsImp.toString());
        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentialsImp))
                .enableForceGlobalBucketAccess()
                .withRegion(region).build();
        amazonS3.setBucketPolicy(bucket, awsPolicy().toJson());
        return amazonS3;
    }

   @Bean
    public Policy awsPolicy() {
        Statement allowPublicReadStatement = new Statement(Statement.Effect.Allow)
                .withPrincipals(Principal.AllUsers)
                .withActions(S3Actions.GetObject)
                .withResources(new S3ObjectResource(bucket, "*"));
        Statement allowRestrictedWriteStatement = new Statement(Statement.Effect.Allow)
                .withPrincipals(
                        new Principal("830127245414"),
                        new Principal(""))
                .withActions(S3Actions.PutObject)
                .withResources(new S3ObjectResource(bucket, "*"));

        return new Policy()
                .withStatements(allowPublicReadStatement, allowRestrictedWriteStatement);
    }

}
