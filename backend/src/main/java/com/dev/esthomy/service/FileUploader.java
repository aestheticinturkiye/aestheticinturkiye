package com.dev.esthomy.service;

import com.dev.esthomy.models.Account;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
public class FileUploader {
    private final AccountService accountService;
    private static MinioClient connect() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("https://play.min.io")
                            .credentials("Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG")
                            .build();

            return minioClient;
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
        return null;
    }

    public void uploadToMinio(String email, File file) throws IOException, NoSuchAlgorithmException, InvalidKeyException, ServerException, InsufficientDataException, ErrorResponseException, InvalidResponseException, XmlParserException, InternalException {
        Account account = accountService.getUserByMail(email);
        try {
            MinioClient minioClient = connect();
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(account.getId()).build());
            if (!isExist) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(account.getId()).build());
            }
            minioClient.uploadObject(UploadObjectArgs.builder()
                    .bucket(account.getId())
                    .object(file.getName())
                    .filename(file.getAbsolutePath())
                    .build());
            System.out.println(file.getAbsolutePath() + "uploaded as" + file.getName() + " to bucket" + account.getId());
        }
        catch (MinioException e) {
            System.out.println("Error" + e);
            System.out.printf("Http Trace" + e.httpTrace());
        }
    }

    public void downloadObject(String email){

    }

}