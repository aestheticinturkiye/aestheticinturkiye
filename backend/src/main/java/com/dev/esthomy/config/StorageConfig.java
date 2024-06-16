package com.dev.esthomy.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Value("${cloud.aws.credentials.secret-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.access-key}")
    private String accessSecret;

    @Value("${cloud.aws.credentials.region}")
    private String region;

    @Bean
    protected AmazonS3 s3Client(){
        AWSCredentials credentials = new BasicAWSCredentials(accessKey,accessSecret);
        return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region).build();
    }

    @Bean
    protected TransferManager tm(){
        AmazonS3 s3Client = s3Client();
       return  TransferManagerBuilder
                .standard()
                .withS3Client(s3Client)
                .build();
    }
}
