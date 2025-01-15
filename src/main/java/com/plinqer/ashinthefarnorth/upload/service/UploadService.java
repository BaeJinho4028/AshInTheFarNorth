package com.plinqer.ashinthefarnorth.upload.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.plinqer.ashinthefarnorth.upload.dto.UploadFileResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadService {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Value("${aws.s3.domain}")
    private String domain;

    public UploadFileResponse uploadFile(MultipartFile file) {
        String fileUrl = uploadToS3(file);
        return UploadFileResponse.fromFileUrl(fileUrl);
    }

    public List<UploadFileResponse> uploadFiles(List<MultipartFile> files) {
        return files.stream()
            .map(this::uploadToS3)
            .map(UploadFileResponse::fromFileUrl)
            .toList();
    }

    private String uploadToS3(MultipartFile file) {
        String filePath = generateFilePath(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(filePath)
                .contentType(file.getContentType())
                .contentDisposition("inline")
                .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
            return String.format("%s/%s", domain, filePath);
        } catch (Exception e) {
            log.error("S3 파일 업로드 실패 - 파일 경로: {}", filePath, e);
            throw new RuntimeException("S3 파일 업로드 실패: " + e.getMessage());
        }
    }

    private String generateFilePath(String fileNameExt) {
        String currentDate = LocalDateTime.now().toString();
        return String.format("ash-game/images/%s/%s", currentDate, fileNameExt.replaceAll(" ", "_"));
    }
}
