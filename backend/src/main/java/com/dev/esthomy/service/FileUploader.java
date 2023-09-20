package com.dev.esthomy.service;

import com.dev.esthomy.models.Account;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Component
public class FileUploader {

    private static MinioClient minioClient;

    static {
        try {
            minioClient = connect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    private static MinioClient connect() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://127.0.0.1:9000")
                            .credentials("ktlPLmjGSXqTYzwXWjdy", "PpCNMaM64ERpsHNK0AchrOOGwALlhsKCBEr2KJSv")
                            .build();

            return minioClient;
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
        return null;
    }


    public void uploadToMinio(Account account, File file) throws IOException, NoSuchAlgorithmException, InvalidKeyException{
        try {
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(account.getId()).build());
            if (!isExist) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(account.getId()).build());
            }
            minioClient.uploadObject(UploadObjectArgs.builder()
                    .bucket(account.getId())
                    .object(file.getName())
                    .filename(file.getAbsolutePath())
                    .build());
            System.out.println(file.getAbsolutePath() + "uploaded as" + file.getName() + " to bucket" + account.getName());
        } catch (MinioException e) {
            System.out.println("Error" + e);
            System.out.printf("Http Trace" + e.httpTrace());
        }
    }

    public List<String> listObjectFiles(Account account) {
        try {
            Iterable<Result<Item>> results = minioClient.listObjects(
                    ListObjectsArgs.builder().bucket(account.getName()).recursive(true).build());

            Iterator<Result<Item>> it = results.iterator();
            List<String> items = new ArrayList<>();
            while (it.hasNext()) {
                Item i = it.next().get();
                items.add("Object: " + i.objectName() + "with size" + i.size());
            }

            return items;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void downloadObject(Account account, String fileName) {
        try {
            //toDo: Make file name format like example : userid/date/file-name; userid/20230919/file-name;
            minioClient.downloadObject(
                    DownloadObjectArgs.builder()
                            .bucket(account.getId())
                            .object(fileName)
                            .filename("D:\\aestheticinturkiye\\backend\\src\\main\\resources\\" + fileName)
                            .build());
            System.out.println(fileName + "is successfully downloaded to D:\\aestheticinturkiye\\backend\\src\\main\\resources");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

}