package com.dev.esthomy.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.dev.esthomy.jwt.model.JwtClaims;
import com.dev.esthomy.models.enums.MemberRole;
import com.dev.esthomy.util.UrlBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    private final AmazonS3 s3Client;
    private final UrlBuilder urlBuilder;

    public String uploadFiles(final JwtClaims principal,final List<MultipartFile> files, final String partnerReqId) {
        if (!principal.getRole().equals(MemberRole.CLIENT)) throw new RuntimeException("You can not find partner");
        StringBuilder result = new StringBuilder();
        for (MultipartFile file : files) {
            File fileObj = convertMultiPartFileToFile(file);
            s3Client.putObject(new PutObjectRequest(bucketName, partnerReqId + "/" +fileObj.getName(), fileObj));
            fileObj.delete();
            result.append("File uploaded: ").append(file.getOriginalFilename()).append("\n");
        }
        return result.toString();
    }

    public byte[] downloadFile(String fileName){
        S3Object s3Object = s3Client.getObject(bucketName,fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        }
        catch (IOException err){
            log.error(String.valueOf(err));
        }
        return null;
    }

    public List<String> getImagesUrls(String partnerReqId){
        ListObjectsV2Result uri = s3Client.listObjectsV2(bucketName,partnerReqId);
        return urlBuilder.returnList(uri.getObjectSummaries());
    }

    public String deleteFile(String fileName){
        s3Client.deleteObject(bucketName,fileName);
        return fileName + " removed succesfully";
    }
    private File convertMultiPartFileToFile(MultipartFile file){
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)){
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error converting MultiPartFile to file",e);
        }
        return convertedFile;
    }
}
